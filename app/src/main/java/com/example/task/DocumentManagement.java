package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DocumentManagement extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_management);



    }



    public void back_button(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    public void identification_btn(View view) {
        Intent intent= new Intent(DocumentManagement.this,AddNewDocument.class);
        intent.putExtra("docName","Identification cards");
        startActivity(intent);
    }

    public void driving_btn(View view) {
        Intent intent= new Intent(DocumentManagement.this,AddNewDocument.class);
        intent.putExtra("docName","Driving license");
        startActivity(intent);
    }
}