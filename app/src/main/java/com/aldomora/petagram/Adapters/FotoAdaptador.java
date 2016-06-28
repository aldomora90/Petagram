package com.aldomora.petagram.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 15/05/16.
 */
public class FotoAdaptador extends RecyclerView.Adapter<FotoAdaptador.FotoViewHolder>{

    ArrayList<Foto> fotos;
    Activity activity;

    public FotoAdaptador(ArrayList<Foto> fotos, Activity activity){
        this.fotos = fotos;
        this.activity = activity;
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
    }

    @Override
    public int getItemCount() {//Cantidad de elementos lista
        return fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgfotoP;
        private TextView tvRateP;

        public FotoViewHolder(View itemView) {
            super(itemView);
            imgfotoP         = (ImageView) itemView.findViewById(R.id.imgFotoP);
            tvRateP        = (TextView) itemView.findViewById(R.id.tvRateP);
        }
    }

}
