package com.unibratec.misael_junior.projetofilmes;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.unibratec.misael_junior.projetofilmes.model.Filme;
import com.unibratec.misael_junior.projetofilmes.model.Genero;
import com.unibratec.misael_junior.projetofilmes.model.Locadora;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListaFilmeFragment extends Fragment {

    @Bind(R.id.list_filme)
    ListView mListView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    List<Filme> mFilmes;

    ArrayAdapter<Filme> mAdapter;
    FilmesTask mTask;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setReenterTransition(true);
        mFilmes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_filme, container, false);
        ButterKnife.bind(this, layout);

        mAdapter = new FilmesAdapter(getContext(), mFilmes);
        mListView.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTask = new FilmesTask();
                mTask.execute();
            }
        });
        return  layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mFilmes.size() == 0 && mTask == null) {
            mTask = new FilmesTask();
            mTask.execute();
        } else if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            mSwipe.setRefreshing(true);
        }
    }

    private void showProgress(){
        mSwipe.post(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(true);
            }
        });
    }

    @OnItemClick(R.id.list_filme)
        void onItemSelected(int position){
        Filme filme = mFilmes.get(position);
        if (getActivity() instanceof CliqueNoFilmeListener){
            CliqueNoFilmeListener listener = (CliqueNoFilmeListener)getActivity();
            listener.filmeFoiClicado(filme);
        }
    }

    class FilmesTask extends AsyncTask<Void, Void, Locadora>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected Locadora doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://dl.dropboxusercontent.com/s/ag77wrafv7kry11/Filmes.json")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String jsonString = response.body().string();
                    Log.d("NGVL", jsonString);
                    Gson gson = new Gson();
                    Locadora locadora = gson.fromJson(jsonString, Locadora.class);
                    return locadora;

                } catch (Exception e){
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(Locadora locadora) {
            super.onPostExecute(locadora);

            if(locadora != null){
                mFilmes.clear();
                for (Genero genero : locadora.getGeneros()){
                    mFilmes.addAll(genero.getFilmes());
                }

                mAdapter.notifyDataSetChanged();
            }

            mSwipe.setRefreshing(false);
        }
    }
}
