package com.unibratec.misael_junior.projetofilmes.model;

import org.parceler.Parcel;

/**
 * Created by misael-junior on 14/04/16.
 */
@Parcel
public class Filme {

     long id;
     String nome;
     String diretor;
     String roteiro;
     int ano;
     String duracao;
     String classificacao;
     String sinopse;
     String capa;
     String triller;


    public Filme() {

    }

    public Filme(String nome, String diretor){
        this.setNome(nome);
        this.setDiretor(diretor);
    }

    @Override
    public String toString() {
        return "Filme : " + getNome() + "\n" + "Diretor : "+ getDiretor();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(String roteiro) {
        this.roteiro = roteiro;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTriller() {
        return triller;
    }

    public void setTriller(String triller) {
        this.triller = triller;
    }
}
