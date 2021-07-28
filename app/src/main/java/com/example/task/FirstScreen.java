package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    public void sreen_1st(View view) {
        Intent intent = new Intent(FirstScreen.this,MainActivity.class);
        startActivity(intent);
    }

    public void sreen_2nd(View view) {
        Intent intent = new Intent(FirstScreen.this,PhoneVerification.class);
        startActivity(intent);
    }

    public void sreen_3rd(View view) {
        Intent intent = new Intent(FirstScreen.this,HomeOffline.class);
        startActivity(intent);
    }

    public void sreen_4th(View view) {
        Intent intent = new Intent(FirstScreen.this,SignUp.class);
        startActivity(intent);
    }

    public void sreen_5th(View view) {
        Intent intent = new Intent(FirstScreen.this,Notifications.class);
        startActivity(intent);
    }

    public void sreen_6th(View view) {
        Intent intent = new Intent(FirstScreen.this,Recipt.class);
        startActivity(intent);
    }

    public void sreen_7th(View view) {
        Intent intent = new Intent(FirstScreen.this,InviteFriends.class);
        startActivity(intent);
    }
}