package com.unibratec.misael_junior.projetofilmes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unibratec.misael_junior.projetofilmes.model.Filme;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by misael-junior on 23/04/16.
 */
public class FilmesAdapter extends ArrayAdapter<Filme> {

    public FilmesAdapter(Context context, List<Filme> filmes) {
        super(context, 0, filmes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Filme filme = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_filme, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(getContext()).load(filme.getCapa()).into(viewHolder.imageView);
        viewHolder.txtNome.setText(filme.getNome());
        viewHolder.txtDiretor.setText(filme.getDiretor());

        return convertView;
    }

    static class ViewHolder{
        @Bind(R.id.image_capa)
        ImageView imageView;
        @Bind(R.id.text_nome)
        TextView txtNome;
        @Bind(R.id.text_diretor)
        TextView txtDiretor;

        public ViewHolder(View parent){
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }

}
