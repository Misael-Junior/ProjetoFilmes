package com.unibratec.misael_junior.projetofilmes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.unibratec.misael_junior.projetofilmes.model.Filme;

import org.parceler.Parcels;

public class DetalheFilmeActivity extends AppCompatActivity {

    public static final String EXTRA_FILME = "filme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);

        Filme filme = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_FILME));

        DetalheFilmeFragment dlf = DetalheFilmeFragment.newInstance(filme);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detalhe, dlf, "detalhe")
                .commit();
    }
}
