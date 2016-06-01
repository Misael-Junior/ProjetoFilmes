package com.unibratec.misael_junior.projetofilmes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by misael-junior on 02/05/16.
 */

//DbHelper responsável por criar o banco de dados.
public class FilmeDbHelper extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "filmes_db";

    public FilmeDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Reponsavél por criar a tabela do banco de dados.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ FilmeContract.TABLE_NAME +" (" +
                FilmeContract._ID           + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FilmeContract.NOME          + " TEXT NOT NULL, " +
                FilmeContract.DIRETOR       + " TEXT NOT NULL, " +
                FilmeContract.ROTEIRO       + " TEXT NOT NULL, " +
                FilmeContract.ANO           + " INTEGER NOT NULL, " +
                FilmeContract.DURACAO       + " TEXT NOT NULL, " +
                FilmeContract.CLASSIFICACAO + " TEXT NOT NULL, " +
                FilmeContract.SINOPSE       + " TEXT NOT NULL, " +
                FilmeContract.CAPA          + " TEXT NOT NULL)");

    }

    //Chamado só se tiver alteração no banco de dados e tabela.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
