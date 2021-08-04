package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    TextView sign_txt;
    Context context;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sign_txt=findViewById(R.id.sign_txt);
        number=findViewById(R.id.number);
        context=this;


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

    public void google_sign_in(View view) {
        startNewActivity(context,"com.google.android.googlequicksearchbox");
    }


    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        }
    }

    public void facebook_sign_in(View view) {
        startNewActivity(context,"com.facebook.katana");
    }

    public void worldgrin_sign_in(View view) {
        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {
        number.setText("");
    }
}