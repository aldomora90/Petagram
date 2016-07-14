package com.aldomora.petagram.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldomora.petagram.MainActivity;
import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.R;
import com.aldomora.petagram.restAPI.ConstantesRestAPI;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.adapter.RestAPIadapter;
import com.aldomora.petagram.restAPI.model.DBResponse;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.aldomora.petagram.userID;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 15/05/16.
 */
public class FotoAdaptador extends RecyclerView.Adapter<FotoAdaptador.FotoViewHolder>{

    ArrayList<Foto> fotos;
    Activity activity;
    Context context;

    public FotoAdaptador(ArrayList<Foto> fotos, Activity activity){
        this.fotos = fotos;
        this.activity = activity;
        this.context = context;
    }
    @Override
    //Inflar layout pasando al viewholder para obtener views
    public FotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil,parent,false);
        return new FotoViewHolder(v);
    }
    //Asociar cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(FotoViewHolder fotoViewHolder, int position) {
        final Foto foto = fotos.get(position);
        Picasso.with(activity)
                .load(foto.getUrlFoto())
                .placeholder(R.drawable.dog_head)
                .into(fotoViewHolder.imgfotoP);
        fotoViewHolder.tvRateP.setText(String.valueOf(foto.getLikes()));

        fotoViewHolder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                RestAPIadapter restAPIadapter = new RestAPIadapter();
                IEndPointsAPI endPoints = restAPIadapter.establishConnectionAPIinstagramLike();
                Call<FotoResponse> fotoResponseCall = endPoints.giveLikeInstagram(foto.getMediaId() , ConstantesRestAPI.ACCESS_TOKEN);

                fotoResponseCall.enqueue(new Callback<FotoResponse>() {
                    @Override
                    public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                        Toast.makeText(v.getContext(), "Diste un like", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<FotoResponse> call, Throwable t) {
                    }
                });

                DBResponse dbResponse = new DBResponse("-KMbKFiME12Twqc4dPrm","Nexus",foto.getMediaId(), userID.username);
                restAPIadapter = new RestAPIadapter();
                endPoints = restAPIadapter.establishConnectionRestAPI();
                Call<DBResponse> dbResponseCall = endPoints.registrarLike(dbResponse.getIdMedia(),dbResponse.getId(),dbResponse.getIdUser());
                dbResponseCall.enqueue(new Callback<DBResponse>() {
                    @Override
                    public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {
                        DBResponse dbResponse = response.body();
                        Log.d("ID_FIREBASE", dbResponse.getId());
                        Log.d("USER_FIREBASE",dbResponse.getIdMedia());
                        Log.d("DEVICE_FIREBASE",dbResponse.getIdDevice());
                        Log.d("USER_FIREBASE",dbResponse.getIdUser());
                    }

                    @Override
                    public void onFailure(Call<DBResponse> call, Throwable t) {
                        Log.e("RETROFIT","Connection Failed");
                    }
                });

                endPoints = restAPIadapter.establishConnectionRestAPI();
                dbResponseCall = endPoints.likeNotif(dbResponse.getId(),dbResponse.getIdUser());
                dbResponseCall.enqueue(new Callback<DBResponse>() {
                    @Override
                    public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {
                        DBResponse dbResponse = response.body();
                        Log.d("ID_NOTIF", dbResponse.getId());
                        Log.d("USER_NOTIF",dbResponse.getIdUser());
                        Intent intent = new Intent(v.getContext(),MainActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<DBResponse> call, Throwable t) {
                        Log.e("RETROFIT","Connection Failed");
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {//Cantidad de elementos lista
        return fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgfotoP;
        private TextView tvRateP;
        private ImageView imgLike;

        public FotoViewHolder(View itemView) {
            super(itemView);
            imgfotoP    = (ImageView) itemView.findViewById(R.id.imgFotoP);
            tvRateP     = (TextView)  itemView.findViewById(R.id.tvRateP);
            imgLike     = (ImageView) itemView.findViewById(R.id.imgLike);
        }
    }

}
