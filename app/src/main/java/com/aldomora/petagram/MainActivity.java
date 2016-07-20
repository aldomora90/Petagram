package com.aldomora.petagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aldomora.petagram.Adapters.PageAdapter;
import com.aldomora.petagram.Fragments.petFeed;
import com.aldomora.petagram.Fragments.petProfile;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.adapter.RestAPIadapter;
import com.aldomora.petagram.restAPI.model.UsuarioResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        ImageButton fav5 = (ImageButton) findViewById(R.id.btnFav);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

        fav5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FavMascota.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContact:
                Intent intent = new Intent(this,Contacto.class);
                startActivity(intent);
                break;
            case R.id.mConfig:
                Intent intent1 = new Intent(this,ConfigCuenta.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.mNotif:
                String token = FirebaseInstanceId.getInstance().getToken();
                sendTokenReg(token,userID.username);
                break;
            case R.id.mAbout:
                Intent intent3 = new Intent(this,AcercaDe.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> addFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new petFeed());
        fragments.add(new petProfile());
        return fragments;
    }

    private void setUpViewPager(){
        extras = getIntent().getExtras();
        String toOpen = "";
        if(extras!= null) {
            toOpen = extras.getString("toOpen");
        }
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),addFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_feed);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile);

        if(toOpen.equals("fragment 1")){
            tabLayout.getTabAt(1).select();
        }
    }

    public void sendTokenReg(String token, String user){
        Log.d("TOKEN",token);
        RestAPIadapter restAPIadapter = new RestAPIadapter();
        IEndPointsAPI endpoints = restAPIadapter.establishConnectionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token, user);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("DEVICES_FIREBASE",usuarioResponse.getIdDevice());
                Log.d("USER_FIREBASE",usuarioResponse.getIdUser());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.e("RETROFIT","Connection Failed");
            }
        });
    }
}
