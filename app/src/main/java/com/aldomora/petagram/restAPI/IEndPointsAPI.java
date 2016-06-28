package com.aldomora.petagram.restAPI;

import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.restAPI.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 23/06/16.
 */
public interface IEndPointsAPI {

    @GET(ConstantesRestAPI.URL_GET_RECENT)
    Call<FotoResponse> getRecentMedia(@Path("id") String userId, @Query("access_token") String token);

    @GET(ConstantesRestAPI.URL_GET_SEARCH)
    Call<SearchResponse> getSearch(@Query("q") String search, @Query("access_token") String token);
}
