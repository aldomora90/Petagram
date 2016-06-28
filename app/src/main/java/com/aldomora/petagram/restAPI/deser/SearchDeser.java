package com.aldomora.petagram.restAPI.deser;

import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.Pojo.User;
import com.aldomora.petagram.restAPI.JsonKeys;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.restAPI.model.SearchResponse;
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
public class SearchDeser implements JsonDeserializer<SearchResponse>{

    @Override
    public SearchResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        SearchResponse searchResponse = gson.fromJson(json, SearchResponse.class);
        JsonArray searchResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        searchResponse.setUsers(deserSearchJson(searchResponseData));
        return searchResponse;
    }

    private ArrayList<User> deserSearchJson(JsonArray searchResponseData){
        ArrayList<User> search = new ArrayList<>();
        for (int i = 0; i < searchResponseData.size() ; i++) {
            JsonObject searchResponseDataObject = searchResponseData.get(i).getAsJsonObject();

            String username = searchResponseDataObject.get(JsonKeys.USER_NAME).getAsString();
            String id = searchResponseDataObject.get(JsonKeys.USER_ID).getAsString();

            User searchActual = new User();
            searchActual.setId(id);
            searchActual.setName(username);
            search.add(searchActual);
        }
        return search;
    }
}
