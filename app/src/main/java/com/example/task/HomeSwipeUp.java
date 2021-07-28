package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeSwipeUp extends AppCompatActivity {
    Button accept_btn;
    CardView card_offer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_swipe_up);

        card_offer = findViewById(R.id.card_offer);
        accept_btn = findViewById(R.id.accept_btn);

        card_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn.setVisibility(View.VISIBLE);
            }
        });

        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this,HomeOnlineBookingDetails.class));
            }
        });
    }
}