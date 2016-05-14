package com.unibratec.misael_junior.projetofilmes;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

    private void toggleFavorito(){
        mFabFavorito.setImageResource(mDAO.isFavorito(mFilme) ? R.drawable.ic_remove : R.drawable.ic_check);
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

        toggleFavorito();

        ((FilmeApp)getActivity().getApplication()).getEventBus().post(mFilme);

    }

}
