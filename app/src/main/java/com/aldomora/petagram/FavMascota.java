package com.aldomora.petagram;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.Adapters.MascotaAdaptador;

import java.util.ArrayList;

public class FavMascota extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_mascota);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorTitle), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        mascotas = new ArrayList<Mascota>();

        listaMascotas = (RecyclerView) findViewById(R.id.rvFav);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        iniciarListaMascotas();
        iniciarAdaptador();
    }

    public void iniciarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }

    public void iniciarListaMascotas(){
        mascotas.add(new Mascota(R.drawable.shiba,"Kenta",11));
        mascotas.add(new Mascota(R.drawable.schnauzer,"Corcho",9));
        mascotas.add(new Mascota(R.drawable.doberman,"Woody",7));
        mascotas.add(new Mascota(R.drawable.puddle,"Tachis",7));
        mascotas.add(new Mascota(R.drawable.beagle,"Dobby",6));
    }
}
