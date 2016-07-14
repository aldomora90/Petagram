package com.aldomora.petagram.restAPI;

import com.aldomora.petagram.restAPI.model.DBResponse;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.restAPI.model.SearchResponse;
import com.aldomora.petagram.restAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 23/06/16.
 */
public interface IEndPointsAPI {

    @GET(ConstantesRestAPI.URL_GET_RECENT)
    Call<FotoResponse> getRecentMedia(@Path("id") String userId, @Query("access_token") String token);

    @POST(ConstantesRestAPI.URL_GIVE_LIKE)
    Call<FotoResponse> giveLikeInstagram(@Path("idMedia") String mediaId, @Query("access_token") String token);

    @GET(ConstantesRestAPI.URL_GET_SEARCH)
    Call<SearchResponse> getSearch(@Query("q") String search, @Query("access_token") String token);

    @FormUrlEncoded
    @POST(ConstantesRestAPI.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("idDevice") String token,@Field("idUser") String user);

    @FormUrlEncoded
    @POST(ConstantesRestAPI.KEY_POST_LIKE)
    Call<DBResponse> registrarLike(@Field("idMedia") String idMedia, @Field("id") String id, @Field("idUser") String idUser);

    @GET(ConstantesRestAPI.KEY_GET_LIKE_NOTIF)
    Call<DBResponse> likeNotif(@Path("id") String id, @Path("user") String user);
}
