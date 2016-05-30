package com.aldomora.petagram.Fragments;

import com.aldomora.petagram.Adapters.MascotaAdaptador;
import com.aldomora.petagram.Pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by root on 29/05/16.
 */
public interface IPetFeed {

    public void generateLinearLayoutVertical();

    public MascotaAdaptador createAdapter(ArrayList<Mascota> mascotas);

    public void startAdapter(MascotaAdaptador adaptador);
}
