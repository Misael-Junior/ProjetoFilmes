package com.unibratec.misael_junior.projetofilmes;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.unibratec.misael_junior.projetofilmes.database.FilmeDAO;
import com.unibratec.misael_junior.projetofilmes.model.Filme;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetalheFilmeFragment extends Fragment {
    private static final String EXTRA_FILME = "param1";

    @Bind(R.id.text_nome)
    TextView mTextNome;
    @Bind(R.id.text_diretor)
    TextView mTextDiretor;
    @Bind(R.id.text_roteiro)
    TextView mTextRoteiro;
    @Bind(R.id.text_ano)
    TextView mTextAno;
    @Bind(R.id.text_duracao)
    TextView mTextDuracao;
    @Bind(R.id.text_classificacao)
    TextView mTextClassificacao;
    @Bind(R.id.text_sinopse)
    TextView mTextSinopse;
    @Bind(R.id.image_capa)
    ImageView mImageCapa;
    @Bind(R.id.fab_favorito)
    FloatingActionButton mFabFavorito;

    private ShareActionProvider mShareActionProvider;

    FilmeDAO mDAO;

    private Filme mFilme;

  public static DetalheFilmeFragment newInstance(Filme filme) {
        DetalheFilmeFragment fragment = new DetalheFilmeFragment();
        Bundle args = new Bundle();
        Parcelable p = Parcels.wrap(filme);
        args.putParcelable(EXTRA_FILME, p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mDAO = new FilmeDAO(getActivity());
        if (getArguments() != null) {
            Parcelable p = getArguments().getParcelable(EXTRA_FILME);
            mFilme =  Parcels.unwrap(p);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detalhe_filme, container, false);
        ButterKnife.bind(this, view);

        mTextNome.setText(getString(R.string.formato_nome,mFilme.getNome()));
        mTextDiretor.setText(getString(R.string.formato_diretor,mFilme.getDiretor()));
        mTextRoteiro.setText(getString(R.string.formato_roteiro,mFilme.getRoteiro()));
        mTextAno.setText(String.valueOf(getString(R.string.formato_ano,mFilme.getAno())));
        mTextDuracao.setText(getString(R.string.formato_duracao,mFilme.getDuracao()));
        mTextClassificacao.setText(getString(R.string.formato_classificacao,mFilme.getClassificacao()));
        mTextSinopse.setText(getString(R.string.formato_sinopse,mFilme.getSinopse()));
        Glide.with(getActivity()).load(mFilme.getCapa()).into(mImageCapa);
        toggleFavorito();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detalhe, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent it = new Intent(Intent.ACTION_SEND);
        it.putExtra(Intent.EXTRA_TEXT, mFilme.getNome());
        it.setType("text/plain");
        mShareActionProvider.setShareIntent(it);
    }

    private void toggleFavorito(){
        boolean favorito = mDAO.isFavorito(mFilme);

        mFabFavorito.setImageResource(
                favorito ? R.drawable.ic_remove : R.drawable.ic_check);
        mFabFavorito.setBackgroundTintList(
        favorito ? ColorStateList.valueOf(Color.RED) : ColorStateList.valueOf(Color.BLUE));
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.meuBotao)
    public void meubotao(){
        Toast.makeText(getContext(), "Abrindo Página do Trailer", Toast.LENGTH_SHORT).show();;
       // Toast.makeText(mT)
    }

    @OnClick(R.id.fab_favorito)
    public void favoritoClick(){
        if (mDAO.isFavorito(mFilme)){
            mDAO.excluir(mFilme);
        } else {
            mDAO.inserir(mFilme);
        }
        mFabFavorito.animate()
                .scaleX(0)
                .scaleY(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                toggleFavorito();
                mFabFavorito.animate().scaleX(1).scaleY(1).setListener(null);
            }
        });
        ((FilmeApp)getActivity().getApplication()).getEventBus().post(mFilme);

    }

}
