package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.AllDocumentFiles.RequestAllDocument;
import com.example.task.AllDocumentFiles.ResponseAllDocument;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentManagement extends AppCompatActivity {

    ImageView id_card_img,id_card_demo,licence_card_img,licence_card_demo;
    Context context;
    TextView id_name,mobile_number,id_number,id_expiry,licence_name,licence_mobile_number,licence_number,licence_expiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_management);
        context=this;

        id_card_demo= findViewById(R.id.id_card_demo);
        id_card_img= findViewById(R.id.id_card_img);
        licence_card_img= findViewById(R.id.licence_card_img);
        licence_card_demo= findViewById(R.id.licence_card_demo);
        id_name= findViewById(R.id.id_name);
        mobile_number= findViewById(R.id.mobile_number);
        id_number= findViewById(R.id.id_number);
        id_expiry= findViewById(R.id.id_expiry);
        licence_name= findViewById(R.id.licence_name);
        licence_mobile_number= findViewById(R.id.licence_mobile_number);
        licence_number= findViewById(R.id.licence_number);
        licence_expiry= findViewById(R.id.licence_expiry);

//        String imageurl= getImageUrl(context);
//        if (imageurl!=null){
//            id_card_img.setVisibility(View.VISIBLE);
//            id_card_demo.setVisibility(View.GONE);
//            String url= "http://precious-ride.ragzon.com/"+imageurl;
//            Log.d("ImageTag","ImageUrl : "+url);
//            Picasso.get().load(url).into(id_card_img);
//        }
        alldocument();

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
        intent.putExtra("docName","Identification Card");
        startActivity(intent);
    }

    public void driving_btn(View view) {
        Intent intent= new Intent(DocumentManagement.this,AddNewDocument.class);
        intent.putExtra("docName","Driving license");
        startActivity(intent);
    }

    public void alldocument() {
        RequestAllDocument requestAllDocument = new RequestAllDocument();
        requestAllDocument.setDriver_id(getClientId(context));




        Call<ResponseAllDocument> responseDocumentUploadCall = ApiClass.getUserServiceAPI().userDriverGetAllDocuments(requestAllDocument);
        responseDocumentUploadCall.enqueue(new Callback<ResponseAllDocument>() {
            @Override
            public void onResponse(Call<ResponseAllDocument> call, Response<ResponseAllDocument> response) {
                if (response.isSuccessful()) {
                   if (response.body().data.card_file_front!=null)
                   {
                       String imageurl= response.body().data.card_file_front;
                       id_card_img.setVisibility(View.VISIBLE);
                       id_card_demo.setVisibility(View.GONE);
                       String url= "http://precious-ride.ragzon.com/"+imageurl;
                       Log.d("ImageTag","ImageUrl : "+url);
                       Picasso.get().load(url).into(id_card_img);
                   }

                    if (response.body().data.license_front!=null)
                    {
                        String imageurl2= response.body().data.license_front;
                        licence_card_img.setVisibility(View.VISIBLE);
                        licence_card_demo.setVisibility(View.GONE);
                        String url= "http://precious-ride.ragzon.com/"+imageurl2;
                        Log.d("ImageTag","ImageUrl : "+url);
                        Picasso.get().load(url).into(licence_card_img);
                    }

                    if (response.body().data.card_number!=null&&response.body().data.expiry_card!=null){
                        id_name.setText(response.body().data.f_name);
                        mobile_number.setText(response.body().data.mobile_number);
                        id_number.setText(response.body().data.card_number);
                        id_expiry.setText(response.body().data.expiry_card);
                    }

                    if (response.body().data.license_number!=null&&response.body().data.expiry_license!=null){
                        licence_name.setText(response.body().data.f_name);
                        licence_mobile_number.setText(response.body().data.mobile_number);
                        licence_number.setText(response.body().data.license_number);
                        licence_expiry.setText(response.body().data.expiry_license);
                    }


                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseAllDocument> call, Throwable t) {
                Log.d("TAG", "Error " + t);
            }
        });
    }
}