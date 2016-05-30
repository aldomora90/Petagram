package com.aldomora.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.R;

import java.util.ArrayList;

/**
 * Created by root on 29/05/16.
 */
public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<Mascota> getData(){
        BaseDatos db = new BaseDatos(context);
        insertMascotas(db);
        return db.getAllMascotas();
    }

    public ArrayList<Mascota> getFavData(){
        BaseDatos db = new BaseDatos(context);
        insertMascotas(db);
        return db.getFavMascotas();
    }


    public void insertMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Dobby");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.beagle);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
            contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Jumpy");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.corgi);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Woody");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.doberman);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Marshall");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.grandanes);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Rocko");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.labrador);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Dólar");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.pitbull);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Rufo");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.pastoraleman);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Tachis");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.puddle);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Corcho");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.schnauzer);

        db.insertMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PETS_NOMBRE, "Kenta");
        contentValues.put(ConstantesDB.TABLE_PETS_FOTO, R.drawable.shiba);

        db.insertMascota(contentValues);
    }
    public void giveLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_LIKES_PET_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesDB.TABLE_LIKES_PET_LIKES, LIKE);
        db.insertLikeMascota(contentValues);
    }

    public int getLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.getLikes(mascota);
    }
}

