package com.aldomora.petagram;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by root on 2/07/16.
 */
public class NotificationIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";
    @Override
    public void onTokenRefresh() {
//        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        sendTokenReg(token);
    }

    private void sendTokenReg(String token) {
        Log.d(TAG,token);
    }
}
