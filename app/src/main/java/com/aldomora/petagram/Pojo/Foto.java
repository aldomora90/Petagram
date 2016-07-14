package com.aldomora.petagram.Pojo;

/**
 * Created by root on 21/05/16.
 */
public class Foto {
    private String id;
    private String mediaId;
    private String nombreComp;
    private String urlFoto;
    private String urlProf;
    private int likes = 0;

    public Foto(String nombreComp, String urlFoto, String urlProf, int likes) {
        this.nombreComp = nombreComp;
        this.urlFoto = urlFoto;
        this.urlProf = urlProf;
        this.likes = likes;
    }

    public Foto() {

    }

    public String getUrlProf() {
        return urlProf;
    }

    public void setUrlProf(String urlProf) {
        this.urlProf = urlProf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreComp() {
        return nombreComp;
    }

    public void setNombreComp(String nombreComp) {
        this.nombreComp = nombreComp;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}

