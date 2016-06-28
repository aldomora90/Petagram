package com.aldomora.petagram.Presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aldomora.petagram.Fragments.IPetProfile;
import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.restAPI.ConstantesRestAPI;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.adapter.RestAPIadapter;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.userID;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 29/05/16.
 */
public class petProfilePresenter implements IPetProfilePresenter{

    private IPetProfile iPetProfile;
    private Context context;
    private ArrayList<Foto> fotos;

    public petProfilePresenter(IPetProfile iPetProfile, Context context){
        this.iPetProfile = iPetProfile;
        this.context = context;
        getRecentMedia();
    }

    @Override
    public void getRecentMedia() {
        RestAPIadapter restAPIadapter = new RestAPIadapter();
        Gson gsonMediaRecent = restAPIadapter.buildGsonDeserMediaRecent();
        IEndPointsAPI endPointsAPI = restAPIadapter.establishConnectionAPIinstagram(gsonMediaRecent);
        Call<FotoResponse> fotoResponseCall = endPointsAPI.getRecentMedia(userID.id, ConstantesRestAPI.ACCESS_TOKEN);

        fotoResponseCall.enqueue(new Callback<FotoResponse>() {
            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                fotos = fotoResponse.getFotos();
                showFotosRV();
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                Toast.makeText(context, "Falló conexión, intente de nuevo", Toast.LENGTH_LONG).show();
                Log.e("Connection failed",t.toString());
            }
        });
    }

    @Override
    public void showFotosRV() {
        iPetProfile.updatePicnName(fotos.get(0).getUrlProf(),fotos.get(0).getNombreComp());
        iPetProfile.startAdapter(iPetProfile.createAdapter(fotos));
        iPetProfile.generateGridLayoutVertical();
    }

}
