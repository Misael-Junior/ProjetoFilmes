package com.unibratec.misael_junior.projetofilmes.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by misael-junior on 19/04/16.
 */
public class Locadora {

    @SerializedName("locadora")
    List<Genero> generos;

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
