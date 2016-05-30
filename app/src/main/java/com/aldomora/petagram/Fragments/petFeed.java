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
import com.aldomora.petagram.Presentador.IPetFeedPresenter;
import com.aldomora.petagram.Presentador.petFeedPresenter;
import com.aldomora.petagram.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class petFeed extends Fragment implements IPetFeed {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IPetFeedPresenter presenter;

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
        presenter = new petFeedPresenter(this,getContext());

        return v;
    }

    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        //glm.setOrientation(GridLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void startAdapter(MascotaAdaptador adaptador){
        listaMascotas.setAdapter(adaptador);
    }

}

