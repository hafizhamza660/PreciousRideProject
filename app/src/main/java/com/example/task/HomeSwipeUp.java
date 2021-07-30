package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.task.adapters.StackAdapter;

import in.arjsna.swipecardlib.SwipeCardView;

public class HomeSwipeUp extends AppCompatActivity {
    Button accept_btn_1,accept_btn_2,accept_btn_3,accept_btn_4,accept_btn_5,accept_btn_6;
    CardView card_offer_1,card_offer_2,card_offer_3,card_offer_4,card_offer_5,card_offer_6;
    Switch switchbtn;
    public static final String TAG ="HomeONline";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_swipe_up);

        card_offer_1 = findViewById(R.id.card_offer_1);
        card_offer_2 = findViewById(R.id.card_offer_2);
        card_offer_3 = findViewById(R.id.card_offer_3);
        card_offer_4 = findViewById(R.id.card_offer_4);
        card_offer_5 = findViewById(R.id.card_offer_5);
        card_offer_6 = findViewById(R.id.card_offer_6);
        accept_btn_1 = findViewById(R.id.accept_btn_1);
        accept_btn_2 = findViewById(R.id.accept_btn_2);
        accept_btn_3 = findViewById(R.id.accept_btn_3);
        accept_btn_4 = findViewById(R.id.accept_btn_4);
        accept_btn_5 = findViewById(R.id.accept_btn_5);
        accept_btn_6 = findViewById(R.id.accept_btn_6);
        switchbtn = findViewById(R.id.switchbtn);
        card_offer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.VISIBLE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.VISIBLE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.VISIBLE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.VISIBLE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.VISIBLE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.VISIBLE);
            }
        });



        accept_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });


        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
                    startActivity(new Intent(HomeSwipeUp.this, HomeOffline.class));
                }
            }
        });
    }
}