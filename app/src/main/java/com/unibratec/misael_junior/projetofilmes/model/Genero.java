package com.unibratec.misael_junior.projetofilmes.model;

import java.util.List;

/**
 * Created by misael-junior on 18/04/16.
 */
public class Genero {
    String nome;
    List<Filme> filmes;

    public String getNome(){
        return  nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public List<Filme> getFilmes(){
        return filmes;
    }

    public void setFilmes(List<Filme> filmes){
        this.filmes = filmes;
    }

}
