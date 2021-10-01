package com.example.task.API;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.content.Context;

import androidx.annotation.NonNull;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseIdServices extends FirebaseMessagingService {

Context context;
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        context=this;
        String driverId= getClientId(context);
        String refershToken = FirebaseInstanceId.getInstance().getToken();
        if (driverId != null)
        {
            updateToken(driverId,refershToken);
        }
    }

    private void updateToken(String driverId, String refreshToken) {


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Tokens");
        Token token = new Token(refreshToken);
        reference.child(driverId).setValue(token);
    }
}
