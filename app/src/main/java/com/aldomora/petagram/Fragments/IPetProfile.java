package com.aldomora.petagram.Fragments;

import com.aldomora.petagram.Adapters.FotoAdaptador;
import com.aldomora.petagram.Adapters.MascotaAdaptador;
import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.Pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by root on 29/05/16.
 */
public interface IPetProfile {

    public void generateGridLayoutVertical();

    public FotoAdaptador createAdapter(ArrayList<Foto> fotos);

    public void startAdapter(FotoAdaptador adaptador);

    public void updatePicnName(String url, String name);
}
