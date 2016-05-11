package com.unibratec.misael_junior.projetofilmes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.unibratec.misael_junior.projetofilmes.model.Filme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misael-junior on 02/05/16.
 */
public class FilmeDAO {

    private Context mContext;

    public FilmeDAO(Context context) {
        this.mContext = context;
    }

    public long inserir(Filme filme){
        FilmeDbHelper helper = new FilmeDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromFilme(filme);

        long id = db.insert(FilmeContract.TABLE_NAME, null, values);

        filme.setId(id);
        db.close();

        return id;
    }

    public int atualizar(Filme filme){
        FilmeDbHelper helper = new FilmeDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromFilme(filme);

        int rowsAffected = db.update(FilmeContract.TABLE_NAME, values,
                FilmeContract._ID + " = ?",
                new String[]{ String.valueOf(filme.getId())});

        db.close();

        return rowsAffected;
    }

    public int excluir(Filme filme){
        FilmeDbHelper helper = new FilmeDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        int rowsAffected = db.delete(FilmeContract.TABLE_NAME, FilmeContract._ID + " = ?",
                new String[]{ String.valueOf(filme.getId()) });

        db.close();

        return rowsAffected;

    }

    public List<Filme> lista(){
        FilmeDbHelper helper = new FilmeDbHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FilmeContract.TABLE_NAME, null);
        List<Filme> filmes = new ArrayList<>();

        if (cursor.getCount() > 0) {
            int idxId            = cursor.getColumnIndex(FilmeContract._ID);
            int idxNome          = cursor.getColumnIndex(FilmeContract.NOME);
            int idxDiretor       = cursor.getColumnIndex(FilmeContract.DIRETOR);
            int idxRoteiro       = cursor.getColumnIndex(FilmeContract.ROTEIRO);
            int idxAno           = cursor.getColumnIndex(FilmeContract.ANO);
            int idxDuracao       = cursor.getColumnIndex(FilmeContract.DURACAO);
            int idxClassificacao = cursor.getColumnIndex(FilmeContract.CLASSIFICACAO);
            int idxSinopse       = cursor.getColumnIndex(FilmeContract.SINOPSE);
            int idxCapa          = cursor.getColumnIndex(FilmeContract.CAPA);

            while (cursor.moveToNext()) {
                Filme filme = new Filme();
                filme.setId(cursor.getLong(idxId));
                filme.setNome(cursor.getString(idxNome));
                filme.setDiretor(cursor.getString(idxDiretor));
                filme.setRoteiro(cursor.getString(idxRoteiro));
                filme.setAno(cursor.getInt(idxAno));
                filme.setDuracao(cursor.getString(idxDuracao));
                filme.setClassificacao(cursor.getString(idxClassificacao));
                filme.setSinopse(cursor.getString(idxSinopse));
                filme.setCapa(cursor.getString(idxCapa));

                filmes.add(filme);
            }

            cursor.close();
        }

        db.close();

        return filmes;
    }

    public boolean isFavorito(Filme filme){

        FilmeDbHelper helper = new FilmeDbHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT count(*) FROM " + FilmeContract.TABLE_NAME +
                " WHERE " + FilmeContract.NOME + " = ?",
                new String []{ filme.getNome() });

        boolean existe = false;
        if (cursor != null){
            cursor.moveToNext();
            existe = cursor.getInt(0) > 0;
            cursor.close();
        }
        db.close();
        return existe;
    }

    private ContentValues valuesFromFilme(Filme filme){
        ContentValues values = new ContentValues();
        values.put(FilmeContract.NOME, filme.getNome());
        values.put(FilmeContract.DIRETOR, filme.getDiretor());
        values.put(FilmeContract.ROTEIRO, filme.getRoteiro());
        values.put(FilmeContract.ANO, filme.getAno());
        values.put(FilmeContract.DURACAO, filme.getDuracao());
        values.put(FilmeContract.CLASSIFICACAO, filme.getClassificacao());
        values.put(FilmeContract.SINOPSE, filme.getSinopse());
        values.put(FilmeContract.CAPA, filme.getCapa());

        return values;
    }
}
