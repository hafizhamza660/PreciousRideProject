package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getEmail;
import static com.example.task.Session.SaveSharedPreference.getFirstName;
import static com.example.task.Session.SaveSharedPreference.getLastName;
import static com.example.task.Session.SaveSharedPreference.getMobileNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyAccount extends AppCompatActivity {
    TextView first_name,fullname,email,number;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        context=this;

        first_name = findViewById(R.id.first_name);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);


        first_name.setText(getFirstName(context));
        fullname.setText(getFirstName(context)+" "+getLastName(context));
        email.setText(getEmail(context));
        number.setText(getMobileNumber(context));
    }

    public void edit_profile(View view) {
        startActivity(new Intent(MyAccount.this,EditInformation.class));
    }

    public void back_button(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        first_name.setText("");
        fullname.setText("");
        email.setText("");
        number.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        informationset();
    }

    public void informationset()
    {
        first_name.setText(getFirstName(context));
        fullname.setText(getFirstName(context)+" "+getLastName(context));
        email.setText(getEmail(context));
        number.setText(getMobileNumber(context));
    }
}