package com.aldomora.petagram.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.Adapters.MascotaAdaptador;
import com.aldomora.petagram.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class petFeed extends Fragment {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    public petFeed() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pet_feed, container, false);

        mascotas = new ArrayList<Mascota>();

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        iniciarListaMascotas();
        iniciarAdaptador();

        return v;
    }

    public void iniciarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void iniciarListaMascotas(){
        mascotas.add(new Mascota(R.drawable.beagle,"Dobby",6));
        mascotas.add(new Mascota(R.drawable.corgi,"Jumpy",5));
        mascotas.add(new Mascota(R.drawable.doberman,"Woody",7));
        mascotas.add(new Mascota(R.drawable.grandanes,"Marshall",2));
        mascotas.add(new Mascota(R.drawable.labrador,"Rocko",2));
        mascotas.add(new Mascota(R.drawable.pastoraleman,"Dólar",4));
        mascotas.add(new Mascota(R.drawable.pitbull,"Rufo",3));
        mascotas.add(new Mascota(R.drawable.puddle,"Tachis",7));
        mascotas.add(new Mascota(R.drawable.schnauzer,"Corcho",9));
        mascotas.add(new Mascota(R.drawable.shiba,"Kenta",11));
    }
}

