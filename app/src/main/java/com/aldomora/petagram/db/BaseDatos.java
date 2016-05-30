package com.aldomora.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.aldomora.petagram.Pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by root on 28/05/16.
 */
public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(Context context){
        super(context, ConstantesDB.DATABASE_NAME, null, ConstantesDB.DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db){
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesDB.TABLE_PETS + "(" +
                ConstantesDB.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLE_PETS_NOMBRE + " TEXT, " +
                ConstantesDB.TABLE_PETS_FOTO + " INTEGER " +
                ")";
        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesDB.TABLE_LIKES_PET + "(" +
                ConstantesDB.TABLE_LIKES_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLE_LIKES_PET_ID_MASCOTA + " INTEGER, " +
                ConstantesDB.TABLE_LIKES_PET_LIKES + " INTEGER, " +
                " FOREIGN KEY (" + ConstantesDB.TABLE_LIKES_PET_ID_MASCOTA + ")" +
                " REFERENCES "   + ConstantesDB.TABLE_PETS + "(" + ConstantesDB.TABLE_PETS_ID + ")" +
                ")";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesDB.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesDB.TABLE_LIKES_PET);
        onCreate(db);
    }

    public ArrayList<Mascota> getAllMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesDB.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            Mascota actual = new Mascota();
            actual.setId(registros.getInt(0));
            actual.setNombre(registros.getString(1));
            actual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesDB.TABLE_LIKES_PET_LIKES + ") as likes" +
                    " FROM "    + ConstantesDB.TABLE_LIKES_PET +
                    " WHERE "   + ConstantesDB.TABLE_LIKES_PET_ID_MASCOTA + "=" + actual.getId();
            Cursor registroLikes = db.rawQuery(queryLikes,null);
            if(registroLikes.moveToNext()){
                actual.setRating(registroLikes.getInt(0));
            }else{
                actual.setRating(0);
            }
            mascotas.add(actual);
        }
        db.close();
        return mascotas;
    }

    public ArrayList<Mascota> getFavMascotas(){
        ArrayList<Integer> fav = new ArrayList<>();
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String queryLiked = "SELECT * FROM " + ConstantesDB.TABLE_LIKES_PET;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosLiked = db.rawQuery(queryLiked,null);
        registrosLiked.moveToLast();
        for (int i = 0; i<5 ; i++) {
            fav.add((Integer) registrosLiked.getInt(1));
            if(!registrosLiked.moveToPrevious()) {
                break;
            }
        }
        for(int i = 0; i<fav.size(); i++){
            String query = "SELECT * " +
                    " FROM "    + ConstantesDB.TABLE_PETS +
                    " WHERE "   + ConstantesDB.TABLE_PETS_ID + "=" + fav.get(i).intValue();
            Cursor registro = db.rawQuery(query,null);
            if(registro.moveToNext()) {
                Mascota actual = new Mascota();
                actual.setId(registro.getInt(0));
                actual.setNombre(registro.getString(1));
                actual.setFoto(registro.getInt(2));
                String queryLikes = "SELECT COUNT(" + ConstantesDB.TABLE_LIKES_PET_LIKES + ") as likes" +
                        " FROM "    + ConstantesDB.TABLE_LIKES_PET +
                        " WHERE "   + ConstantesDB.TABLE_LIKES_PET_ID_MASCOTA + "=" + actual.getId();
                Cursor registroLikes = db.rawQuery(queryLikes,null);
                if(registroLikes.moveToNext()){
                    actual.setRating(registroLikes.getInt(0));
                }else{
                    actual.setRating(0);
                }
                mascotas.add(actual);
            }
        }
        db.close();
        return mascotas;
    }

//    public ArrayList<Mascota> getFavMascotas(){
//        ArrayList<Mascota> mascotas = new ArrayList<>();
//        String query = "SELECT * FROM " + ConstantesDB.TABLE_PETS;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor registros = db.rawQuery(query,null);
//        for(int i = 0; i<2; i++){
//            registros.moveToPosition(i);
//            Mascota actual = new Mascota();
//            actual.setId(registros.getInt(0));
//            actual.setNombre(registros.getString(1));
//            actual.setFoto(registros.getInt(2));
//            mascotas.add(actual);
//        }
//        db.close();
//        return mascotas;
//    }

    public void insertMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLE_PETS,null,contentValues);
        db.close();
    }

    public void insertLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLE_LIKES_PET,null,contentValues);
        db.close();
    }

    public int getLikes(Mascota mascota){
        int likes = 0;
        String query = "SELECT COUNT(" + ConstantesDB.TABLE_LIKES_PET_LIKES + ")" +
                " FROM " + ConstantesDB.TABLE_LIKES_PET +
                " WHERE " + ConstantesDB.TABLE_LIKES_PET_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros =  db.rawQuery(query, null);

        if(registros.moveToNext()){
            likes = registros.getInt(0);

        }
        db.close();
        return likes;
    }
}
