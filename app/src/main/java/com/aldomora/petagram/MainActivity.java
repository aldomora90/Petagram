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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                break;
            case R.id.mAbout:
                Intent intent2 = new Intent(this,AcercaDe.class);
                startActivity(intent2);
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
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),addFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_feed);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile);
    }

}
