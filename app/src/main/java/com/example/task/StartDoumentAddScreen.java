package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getCountryCode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.task.API.ApiClass;
import com.example.task.DocumentDataFiles.RequestDocumentCountrywise;
import com.example.task.DocumentDataFiles.ResponseDocumentCountrywise;
import com.example.task.FilesSignUp.RequestSignUp;
import com.example.task.FilesSignUp.ResponseSignUp;
import com.example.task.adapters.DocumentsgetListAdapter;
import com.example.task.adapters.RideStepsListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartDoumentAddScreen extends AppCompatActivity {
    String countrycode;
    RecyclerView recyclerViewdocument;
    LinearLayoutManager linearLayoutManager;
    DocumentsgetListAdapter rideStepsListAdapter;
    Activity activity;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_doument_add_screen);
        activity=this;
        context=this;
        Intent intent = getIntent();
        countrycode = intent.getStringExtra("countrycode");

        dataDocumentget(getCountryCode(context));
        recyclerViewdocument = findViewById(R.id.recycler_view_documents);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewdocument.setLayoutManager(linearLayoutManager);

    }

    public void dataDocumentget(String countrycode) {
        RequestDocumentCountrywise requestSignUp = new RequestDocumentCountrywise();
//
        requestSignUp.setNumber(countrycode);
//        signUpRequest.setI_code(invite_code);


        Call<ResponseDocumentCountrywise> signUpResponseCall = ApiClass.getUserServiceDocumentCountrywise().userLogin(requestSignUp);
        signUpResponseCall.enqueue(new Callback<ResponseDocumentCountrywise>() {
            @Override
            public void onResponse(Call<ResponseDocumentCountrywise> call, Response<ResponseDocumentCountrywise> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Success")) {
                        rideStepsListAdapter = new DocumentsgetListAdapter(activity, context, response.body().data);
                        recyclerViewdocument.setAdapter(rideStepsListAdapter);
                    }
                } else {
//                    Toast.makeText(SignUp.this, "API not Hit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDocumentCountrywise> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}