package com.musement.luisarturo.musement;

/**
 * Created by scastro81 on 7/04/17.
 */

public class ModelTeam {

    private String titulo;
    private int idRandom;

    public ModelTeam(){

    }

    public ModelTeam(String titulo, String descripcion){
        this.titulo = titulo;
        this.idRandom = idRandom;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return idRandom;
    }

    public void setId(int idRandom) {
        this.idRandom = idRandom;
    }
}
