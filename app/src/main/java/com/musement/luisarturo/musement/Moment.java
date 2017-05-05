package com.musement.luisarturo.musement;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", titulo);
        result.put("author", descripcion);
        result.put("title", time);

        return result;
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
