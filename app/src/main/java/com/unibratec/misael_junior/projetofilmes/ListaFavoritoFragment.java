package com.unibratec.misael_junior.projetofilmes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.unibratec.misael_junior.projetofilmes.database.FilmeDAO;
import com.unibratec.misael_junior.projetofilmes.model.Filme;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListaFavoritoFragment extends Fragment {

    @Bind(R.id.list_filme)
    ListView mListView;

    List<Filme> mFilmes;
    ArrayAdapter<Filme> mAdapter;
    FilmeDAO mDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setReenterTransition(true);
        mDao = new FilmeDAO(getActivity());
        mFilmes = mDao.lista();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_favorito, container, false);
        ButterKnife.bind(this, layout);

        mAdapter = new FilmesAdapter(getContext(), mFilmes);
        mListView.setAdapter(mAdapter);
        return  layout;
    }

    @OnItemClick(R.id.list_filme)
        void onItemSelected(int position){
        Filme filme = mFilmes.get(position);
        if (getActivity() instanceof CliqueNoFilmeListener){
            CliqueNoFilmeListener listener = (CliqueNoFilmeListener)getActivity();
            listener.filmeFoiClicado(filme);
        }
    }
}
