package com.aldomora.petagram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.aldomora.petagram.restAPI.ConstantesRestAPI;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.adapter.RestAPIadapter;
import com.aldomora.petagram.restAPI.model.DBResponse;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.restAPI.model.SearchResponse;
import com.aldomora.petagram.restAPI.model.StatusResponse;
import com.aldomora.petagram.restAPI.model.UsuarioResponse;
import com.google.gson.Gson;
import com.sun.mail.imap.protocol.Status;
import com.sun.mail.util.UUDecoderStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aldom on 17/07/2016.
 */
public class FollowUnfollow extends BroadcastReceiver{

    String status;
    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTION_KEY = "FOLLOWUNFOLLOW";
        String action = intent.getAction();
        if(ACTION_KEY.equals(action)){
            followUnfollow();
        }
    }

    public void followUnfollow(){
        String nextStatus = "";
        if(userID.statusFriend.equals("none")){
            nextStatus = "follow";
        }
        else if(userID.statusFriend.equals("follows")){
            nextStatus = "unfollow";
        }
        RestAPIadapter restAPIadapter = new RestAPIadapter();
        Gson gsonStatus = restAPIadapter.buildGsonDeserStatus();
        IEndPointsAPI endPoints = restAPIadapter.establishConnectionAPIinstagram(gsonStatus);
        Call<StatusResponse> statusResponseCall = endPoints.cambiarRelacion(userID.idFriend,ConstantesRestAPI.ACCESS_TOKEN,nextStatus);

        statusResponseCall.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                StatusResponse statusResponse = response.body();
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
        getStatus();
    }

    public void getStatus(){
        RestAPIadapter restAPIadapter = new RestAPIadapter();
        Gson gsonStatus = restAPIadapter.buildGsonDeserStatus();
        IEndPointsAPI endPoints = restAPIadapter.establishConnectionAPIinstagram(gsonStatus);
        Call<StatusResponse> statusResponseCall = endPoints.obtenerRelacion(userID.idFriend,ConstantesRestAPI.ACCESS_TOKEN);

        statusResponseCall.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                StatusResponse statusResponse = response.body();
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
    }
}
