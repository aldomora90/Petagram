package com.aldomora.petagram.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldomora.petagram.Adapters.FotoAdaptador;
import com.aldomora.petagram.Adapters.MascotaAdaptador;
import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class petProfile extends Fragment {

    ArrayList<Foto> fotos;
    private RecyclerView listaFotos;
    
    public petProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pet_profile, container, false);

        fotos = new ArrayList<Foto>();

        listaFotos = (RecyclerView) v.findViewById(R.id.rvFotos);

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        glm.setOrientation(GridLayoutManager.VERTICAL);

        listaFotos.setLayoutManager(glm);
        iniciarListaFotos();
        iniciarAdaptador();

        return v;
    }

    public void iniciarAdaptador(){
        FotoAdaptador adaptador = new FotoAdaptador(fotos,getActivity());
        listaFotos.setAdapter(adaptador);
    }

    public void iniciarListaFotos(){
        fotos.add(new Foto(R.drawable.beagle,1));
        fotos.add(new Foto(R.drawable.corgi,4));
        fotos.add(new Foto(R.drawable.doberman,3));
        fotos.add(new Foto(R.drawable.grandanes,2));
        fotos.add(new Foto(R.drawable.labrador,2));
        fotos.add(new Foto(R.drawable.pastoraleman,3));
        fotos.add(new Foto(R.drawable.pitbull,4));
        fotos.add(new Foto(R.drawable.puddle,3));
        fotos.add(new Foto(R.drawable.schnauzer,5));
    }
}

