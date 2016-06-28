package com.aldomora.petagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aldomora.petagram.Pojo.User;
import com.aldomora.petagram.Presentador.petProfilePresenter;
import com.aldomora.petagram.restAPI.ConstantesRestAPI;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.adapter.RestAPIadapter;
import com.aldomora.petagram.restAPI.model.SearchResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigCuenta extends AppCompatActivity {

    private ArrayList<User> usersfound;
    private String newUser;
    private Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextInputLayout userName = (TextInputLayout) findViewById(R.id.userForm);
        Button save = (Button) findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser = userName.getEditText().getText().toString();
                RestAPIadapter restAPIadapter = new RestAPIadapter();
                Gson gsonSearch = restAPIadapter.buildGsonDeserSearch();
                IEndPointsAPI endPointsAPI = restAPIadapter.establishConnectionAPIinstagram(gsonSearch);
                Call<SearchResponse> searchResponseCall = endPointsAPI.getSearch(newUser, ConstantesRestAPI.ACCESS_TOKEN);

                searchResponseCall.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        SearchResponse searchResponse = response.body();
                        usersfound = searchResponse.getUsers();
                        replaceUser();
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Toast.makeText(ConfigCuenta.this, "Falló conexión, intente de nuevo", Toast.LENGTH_LONG).show();
                        Log.e("Connection failed",t.toString());
                    }
                });
                finish();
                Intent i = new Intent(activity,MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void replaceUser(){
        Boolean flag = false;
        for (int i = 0; i < usersfound.size(); i++) {
            if(usersfound.get(i).getName().equals(newUser)){
                userID.id = usersfound.get(i).getId();
                flag = true;
            }
        }
        if(flag){
            Toast.makeText(ConfigCuenta.this, "Bienvenido " + newUser, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ConfigCuenta.this, "No se encontró " + newUser, Toast.LENGTH_LONG).show();
        }
    }
}
