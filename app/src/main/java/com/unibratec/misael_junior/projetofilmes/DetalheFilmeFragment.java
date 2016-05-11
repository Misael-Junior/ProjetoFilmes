package com.unibratec.misael_junior.projetofilmes;


import android.os.Bundle;
import android.os.Parcelable;
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

        mTextNome.setText(mFilme.getNome());
        mTextDiretor.setText(mFilme.getDiretor());
        mTextRoteiro.setText(mFilme.getRoteiro());
        mTextAno.setText(String.valueOf(mFilme.getAno()));
        mTextDuracao.setText(mFilme.getDuracao());
        mTextClassificacao.setText(mFilme.getClassificacao());
        mTextSinopse.setText(mFilme.getSinopse());
        Glide.with(getActivity()).load(mFilme.getCapa()).into(mImageCapa);
        return view;
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

        ((FilmeApp)getActivity().getApplication()).getEventBus().post(mFilme);

    }

}
