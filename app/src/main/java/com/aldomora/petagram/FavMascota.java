package com.aldomora.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aldomora.petagram.Fragments.IPetFeed;
import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.Adapters.MascotaAdaptador;
import com.aldomora.petagram.Presentador.IPetFavPresenter;
import com.aldomora.petagram.Presentador.petFavPresenter;

import java.util.ArrayList;

public class FavMascota extends AppCompatActivity  implements IPetFeed {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IPetFavPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_mascota);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mascotas = new ArrayList<Mascota>();

        listaMascotas = (RecyclerView) findViewById(R.id.rvFav);
        presenter = new petFavPresenter(this,getBaseContext());
    }

    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        //glm.setOrientation(GridLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void startAdapter(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}

