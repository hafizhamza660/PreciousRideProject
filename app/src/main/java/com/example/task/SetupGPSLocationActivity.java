package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.Floating.FloatingViewService;

public class SetupGPSLocationActivity extends AppCompatActivity {

    Button btn_GetStarted;
    TextView text_Skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_gpslocation);

        btn_GetStarted = findViewById(R.id.btn_GetStarted);
        text_Skip = findViewById(R.id.text_Skip);

        btn_GetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupGPSLocationActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        text_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupGPSLocationActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }


}