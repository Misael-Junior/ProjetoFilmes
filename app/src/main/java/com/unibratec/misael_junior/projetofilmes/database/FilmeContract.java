package com.unibratec.misael_junior.projetofilmes.database;

import android.provider.BaseColumns;

/**
 * Created by misael-junior on 02/05/16.
 */
public interface FilmeContract extends BaseColumns {

    String TABLE_NAME    = "filmes";

    String NOME          = "nome";
    String DIRETOR       = "diretor";
    String ROTEIRO       = "roteiro";
    String ANO           = "ano";
    String DURACAO       = "duracao";
    String CLASSIFICACAO = "classificacao";
    String SINOPSE       = "sinopse";
    String CAPA          = "capa";

}
