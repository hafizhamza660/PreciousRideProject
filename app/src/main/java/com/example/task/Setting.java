package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Setting extends AppCompatActivity {
RelativeLayout container_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        container_2 = findViewById(R.id.container_2);
        container_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,MyAccount.class));
            }
        });
    }

    public void vehicle_click(View view) {
        startActivity(new Intent(Setting.this,VehicleManagement.class));
    }

    public void document(View view) {
        startActivity(new Intent(Setting.this,DocumentManagement.class));
    }
}