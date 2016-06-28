package com.aldomora.petagram.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aldomora.petagram.Adapters.FotoAdaptador;
import com.aldomora.petagram.Adapters.MascotaAdaptador;
import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.Presentador.IPetProfilePresenter;
import com.aldomora.petagram.Presentador.petProfilePresenter;
import com.aldomora.petagram.R;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.adapter.RestAPIadapter;
import com.aldomora.petagram.restAPI.model.FotoResponse;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class petProfile extends Fragment implements IPetProfile{

    private RecyclerView listaFotos;
    private petProfilePresenter presenter;
    private TextView tvUser;
    private CircularImageView profilePic;
    
    public petProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pet_profile, container, false);

        listaFotos = (RecyclerView) v.findViewById(R.id.rvFotos);
        tvUser = (TextView) v.findViewById(R.id.tvUser);
        profilePic = (CircularImageView) v.findViewById(R.id.profilePic);
        presenter = new petProfilePresenter(this,getContext());
        return v;
    }

    @Override
    public void generateGridLayoutVertical() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        listaFotos.setLayoutManager(glm);
    }

    @Override
    public FotoAdaptador createAdapter(ArrayList<Foto> fotos) {
        FotoAdaptador adaptador = new FotoAdaptador(fotos,getActivity());
        return adaptador;
    }

    @Override
    public void startAdapter(FotoAdaptador adaptador) {
        listaFotos.setAdapter(adaptador);
    }

    @Override
    public void updatePicnName(String url, String name) {
        tvUser.setText(name);
        Picasso.with(getActivity())
                .load(url)
                .placeholder(R.drawable.dog_head)
                .into(profilePic);
    }
//    public void iniciarListaFotos(){
//        fotos.add(new Foto(R.drawable.beagle,1));
//        fotos.add(new Foto(R.drawable.corgi,4));
//        fotos.add(new Foto(R.drawable.doberman,3));
//        fotos.add(new Foto(R.drawable.grandanes,2));
//        fotos.add(new Foto(R.drawable.labrador,2));
//        fotos.add(new Foto(R.drawable.pastoraleman,3));
//        fotos.add(new Foto(R.drawable.pitbull,4));
//        fotos.add(new Foto(R.drawable.puddle,3));
//        fotos.add(new Foto(R.drawable.schnauzer,5));
//    }
}

