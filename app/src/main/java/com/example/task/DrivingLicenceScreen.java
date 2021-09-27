package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class DrivingLicenceScreen extends AppCompatActivity {
    String countrycode;
    CheckBox i_dont_have_taxi;
    LinearLayout taxi_info_layout;
    Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_licence_screen);

        Intent intent= getIntent();
        countrycode= intent.getStringExtra("countrycode");

        i_dont_have_taxi= findViewById(R.id.i_dont_have_taxi);
        taxi_info_layout= findViewById(R.id.taxi_info_layout);
        next_btn= findViewById(R.id.next_btn);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DrivingLicenceScreen.this,StartDoumentAddScreen.class);
                intent.putExtra("countrycode", countrycode);
                startActivity(intent);
            }
        });


    }

    public void agree_check(View view) {
        if (i_dont_have_taxi.isChecked())
        {
            taxi_info_layout.setVisibility(View.VISIBLE);
        }
        else{
            taxi_info_layout.setVisibility(View.GONE);
        }
    }
}