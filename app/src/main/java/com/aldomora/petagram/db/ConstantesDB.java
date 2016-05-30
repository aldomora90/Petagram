package com.aldomora.petagram.db;

/**
 * Created by root on 29/05/16.
 */
public final class ConstantesDB {
    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PETS = "mascota";
    public static final String TABLE_PETS_ID = "id";
    public static final String TABLE_PETS_NOMBRE = "nombre";
    public static final String TABLE_PETS_FOTO = "foto";

    public static final String TABLE_LIKES_PET = "mascota_rating";
    public static final String TABLE_LIKES_PET_ID = "id";
    public static final String TABLE_LIKES_PET_ID_MASCOTA = "id_mascota";
    public static final String TABLE_LIKES_PET_LIKES = "rating";
}

