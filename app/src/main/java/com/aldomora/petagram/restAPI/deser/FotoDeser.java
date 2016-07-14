package com.aldomora.petagram.restAPI.deser;

import android.util.Log;

import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.restAPI.JsonKeys;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by root on 24/06/16.
 */
public class FotoDeser implements JsonDeserializer<FotoResponse>{

    @Override
    public FotoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FotoResponse fotoResponse = gson.fromJson(json, FotoResponse.class);
        JsonArray fotoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        fotoResponse.setFotos(deserFotoJson(fotoResponseData));
        return fotoResponse;
    }

    private ArrayList<Foto> deserFotoJson(JsonArray fotoResponseData){
        ArrayList<Foto> fotos = new ArrayList<>();
        for (int i = 0; i < fotoResponseData.size() ; i++) {
            JsonObject fotoResponseDataObject = fotoResponseData.get(i).getAsJsonObject();

            JsonObject userJson = fotoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id       = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombComp = userJson.get(JsonKeys.USER_FULL_NAME).getAsString();
            String urlProf = userJson.get(JsonKeys.USER_PROFIL_PIC).getAsString();

            JsonObject imageJson = fotoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdRes    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RES);
            String urlFoto  = stdRes.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = fotoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            String mediaId = fotoResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString();

            Foto fotoActual = new Foto();
            fotoActual.setId(id);
            fotoActual.setMediaId(mediaId);
            fotoActual.setNombreComp(nombComp);
            fotoActual.setUrlProf(urlProf);
            fotoActual.setUrlFoto(urlFoto);
            fotoActual.setLikes(likes);
            fotos.add(fotoActual);
        }
        return fotos;
    }
}
