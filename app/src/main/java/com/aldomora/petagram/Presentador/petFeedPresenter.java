package com.aldomora.petagram.Presentador;

import android.content.Context;

import com.aldomora.petagram.Fragments.IPetFeed;
import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.db.ConstructorMascotas;

import java.util.ArrayList;

/**
 * Created by root on 29/05/16.
 */
public class petFeedPresenter implements IPetFeedPresenter{

    private IPetFeed iPetFeed;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public petFeedPresenter(IPetFeed iPetFeed, Context context){
        this.iPetFeed = iPetFeed;
        this.context = context;
        getMascotas();
    }

    @Override
    public void getMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.getData();
        showMascotasRV();
    }

    @Override
    public void showMascotasRV() {
        iPetFeed.startAdapter(iPetFeed.createAdapter(mascotas));
        iPetFeed.generateLinearLayoutVertical();
    }
}
