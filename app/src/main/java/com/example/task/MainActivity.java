package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView login_txt;
    EditText number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_txt= findViewById(R.id.login_txt);
        number= findViewById(R.id.number);

        login_txt.setText(getText(R.string.login_text));
    }

    public void sign_in(View view) {
        Intent intent = new Intent(MainActivity.this,HomeOffline.class);
        startActivity(intent);
    }

    public void clear(View view) {
        number.setText("");
    }
}