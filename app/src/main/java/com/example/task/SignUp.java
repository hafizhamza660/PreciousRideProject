package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    TextView sign_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sign_txt=findViewById(R.id.sign_txt);



        sign_txt.setText(getText(R.string.sign_up_text));
    }

    public void sign_up(View view) {
        Intent intent = new Intent(SignUp.this,HomeOffline.class);
        startActivity(intent);
    }

    public void sign_in(View view) {
        Intent intent = new Intent(SignUp.this,MainActivity.class);
        startActivity(intent);
    }
}