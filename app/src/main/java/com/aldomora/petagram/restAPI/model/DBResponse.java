package com.aldomora.petagram.restAPI.model;

/**
 * Created by aldom on 13/07/2016.
 */
public class DBResponse {
    private String id;
    private String idDevice;
    private String idMedia;
    private String idUser;

    public DBResponse() {
    }

    public DBResponse(String id, String idDevice, String idMedia, String idUser) {
        this.id = id;
        this.idDevice = idDevice;
        this.idMedia = idMedia;
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(String idMedia) {
        this.idMedia = idMedia;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
