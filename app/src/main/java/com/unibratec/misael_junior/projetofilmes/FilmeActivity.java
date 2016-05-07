package com.unibratec.misael_junior.projetofilmes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.unibratec.misael_junior.projetofilmes.model.Filme;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilmeActivity extends AppCompatActivity
    implements CliqueNoFilmeListener{

    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mViewPager.setAdapter(new FilmePager(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    class FilmePager extends FragmentPagerAdapter {

        public FilmePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) return new ListaFilmeFragment();
            return new ListaFavoritoFragment();
        }

        @Override
        public CharSequence getPageTitle(int position){
            if (position == 0) return getString(R.string.aba_web);
            return getString(R.string.aba_favorito);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public void filmeFoiClicado(Filme filme) {
        if (getResources().getBoolean(R.bool.tablet)) {
            DetalheFilmeFragment dlf = DetalheFilmeFragment.newInstance(filme);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detalhe, dlf, "detalhe")
                    .commit();
        } else {
            Intent it = new Intent(this, DetalheFilmeActivity.class);
            Parcelable p = Parcels.wrap(filme);
            it.putExtra(DetalheFilmeActivity.EXTRA_FILME, p);
            startActivity(it);
        }
    }
}
