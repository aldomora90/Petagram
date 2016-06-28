package com.aldomora.petagram.restAPI.adapter;

import com.aldomora.petagram.Pojo.User;
import com.aldomora.petagram.restAPI.ConstantesRestAPI;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.deser.FotoDeser;
import com.aldomora.petagram.restAPI.deser.SearchDeser;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.restAPI.model.SearchResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 24/06/16.
 */
public class RestAPIadapter {
    public IEndPointsAPI establishConnectionAPIinstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IEndPointsAPI.class);
    }

    public Gson buildGsonDeserMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FotoResponse.class,new FotoDeser());
        return gsonBuilder.create();
    }


    public Gson buildGsonDeserSearch(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SearchResponse.class,new SearchDeser());
        return gsonBuilder.create();
    }
}