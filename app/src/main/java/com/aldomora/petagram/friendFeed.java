package com.aldomora.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.aldomora.petagram.Adapters.FotoAdaptador;
import com.aldomora.petagram.Fragments.IPetProfile;
import com.aldomora.petagram.Pojo.Foto;
import com.aldomora.petagram.Presentador.friendProfilePresenter;
import com.aldomora.petagram.Presentador.petProfilePresenter;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class friendFeed extends AppCompatActivity implements IPetProfile{

    private RecyclerView listaFotos;
    private friendProfilePresenter presenter;
    private TextView tvUser;
    private CircularImageView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_feed);
        listaFotos = (RecyclerView) findViewById(R.id.rvFriend);
        tvUser = (TextView) findViewById(R.id.tvUserFriend);
        profilePic = (CircularImageView) findViewById(R.id.profilePicFriend);
        presenter = new friendProfilePresenter(this);
    }

    @Override
    public void generateGridLayoutVertical() {
        GridLayoutManager glm = new GridLayoutManager(this,3);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        listaFotos.setLayoutManager(glm);
    }

    @Override
    public FotoAdaptador createAdapter(ArrayList<Foto> fotos) {
        FotoAdaptador adaptador = new FotoAdaptador(fotos,this);
        return adaptador;
    }

    @Override
    public void startAdapter(FotoAdaptador adaptador) {
        listaFotos.setAdapter(adaptador);
    }

    @Override
    public void updatePicnName(String url, String name) {
        tvUser.setText(name);
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.dog_head)
                .into(profilePic);
    }
}
