package com.aldomora.petagram.restAPI.model;

/**
 * Created by aldom on 12/07/2016.
 */
public class UsuarioResponse {
    private String id;
    private String idDevice;
    private String idUser;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String idDevice, String id) {
        this.idDevice = idDevice;
        this.id = id;
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
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
}
