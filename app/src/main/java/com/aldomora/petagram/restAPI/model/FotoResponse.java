package com.aldomora.petagram.restAPI.model;

import com.aldomora.petagram.Pojo.Foto;

import java.util.ArrayList;

/**
 * Created by root on 23/06/16.
 */
public class FotoResponse {
    ArrayList<Foto> fotos;

    public ArrayList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<Foto> fotos) {
        this.fotos = fotos;
    }
}
