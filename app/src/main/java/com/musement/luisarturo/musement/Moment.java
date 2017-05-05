package com.musement.luisarturo.musement;

/**
 * Created by scastro81 on 7/04/17.
 */

public class Moment {

    private String titulo;
    private String descripcion;
    private String time;

    public Moment(){

    }

    public Moment(String titulo, String descripcion, String time){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.time = time;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
