package com.aldomora.petagram.Pojo;

/**
 * Created by root on 15/05/16.
 */
public class Mascota {
    private int id;
    private int foto;
    private String nombre;
    private int rating;

    public Mascota(int id, int foto, String nombre, int rating) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.rating = rating;
    }

    public Mascota() {

    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
