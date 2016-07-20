package com.aldomora.petagram;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

import com.aldomora.petagram.Pojo.User;
import com.aldomora.petagram.restAPI.ConstantesRestAPI;
import com.aldomora.petagram.restAPI.IEndPointsAPI;
import com.aldomora.petagram.restAPI.adapter.RestAPIadapter;
import com.aldomora.petagram.restAPI.model.SearchResponse;
import com.aldomora.petagram.restAPI.model.StatusResponse;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 2/07/16.
 */
public class NotificationService extends FirebaseMessagingService{

    private ArrayList<User> usersfound;
    private String[] user;
    private String id = "";
    private String nextStatus = "";
    private int statusImg = R.drawable.followunfollow;
    private Intent i1;
    private Intent i2;
    private Intent i3;

    public static final String TAG = "FIREBASE";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        user = remoteMessage.getNotification().getBody().split(" dio like a una foto tuya");
        Log.d(TAG, user[0]);

        i1 = new Intent(this,MainActivity.class);
        i1.putExtra("toOpen", "fragment 1");

        i2 = new Intent();
        i2.setAction("FOLLOWUNFOLLOW");

        i3 = new Intent(this,friendFeed.class);

        getUserId();

        PendingIntent pendI = PendingIntent.getActivity(this,0,i1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action1 = new NotificationCompat.Action.Builder(R.drawable.ic_profile,     getString(R.string.tu_perfil),pendI).build();

        pendI = PendingIntent.getBroadcast(this,0,i2,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action2 = new NotificationCompat.Action.Builder(R.drawable.followunfollow, getString(R.string.seguir),pendI).build();

        pendI = PendingIntent.getActivity(this,0,i3,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action3 = new NotificationCompat.Action.Builder(R.drawable.viewprofile,    getString(R.string.su_perfil),pendI).build();

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.dogback))
                .setGravity(Gravity.CENTER_VERTICAL);

        wearableExtender.addAction(action1);
        wearableExtender.addAction(action2);
        wearableExtender.addAction(action3);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.megaphone)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sound)
                .setContentIntent(pendI)
                .extend(wearableExtender);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(001,notification.build());
    }

    public void getUserId(){
        RestAPIadapter restAPIadapter = new RestAPIadapter();
        Gson gsonSearch = restAPIadapter.buildGsonDeserSearch();
        IEndPointsAPI endPointsAPI = restAPIadapter.establishConnectionAPIinstagram(gsonSearch);
        Call<SearchResponse> searchResponseCall = endPointsAPI.getSearch(user[0], ConstantesRestAPI.ACCESS_TOKEN);

        Log.d("LIKER_USER", user[0]);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                usersfound = searchResponse.getUsers();
                for (int i = 0; i < usersfound.size(); i++) {
                    if(usersfound.get(i).getName().equals(user[0])){
                        id = usersfound.get(i).getId();
                        userID.idFriend = id;
                        Log.d("LIKER_ID", userID.idFriend);
                        getStatus();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("GET ID","ERROR");
            }
        });
    }

    public void getStatus(){
        RestAPIadapter restAPIadapter = new RestAPIadapter();
        Gson gsonStatus = restAPIadapter.buildGsonDeserStatus();
        IEndPointsAPI endPoints = restAPIadapter.establishConnectionAPIinstagram(gsonStatus);
        Call<StatusResponse> statusResponseCall = endPoints.obtenerRelacion(id,ConstantesRestAPI.ACCESS_TOKEN);

        statusResponseCall.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                StatusResponse statusResponse = response.body();
                userID.statusFriend = statusResponse.getOutgoing_status();
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
    }
}
