package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void sign_in(View view) {
        startActivity(new Intent(WelcomeScreen.this,MainActivity.class));
    }

    public void sign_up(View view) {
        startActivity(new Intent(WelcomeScreen.this,SignUp.class));
    }
}