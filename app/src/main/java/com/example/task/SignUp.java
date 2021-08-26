package com.example.task;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task.API.ApiClass;
import com.example.task.FilesSignUp.RequestSignUp;
import com.example.task.FilesSignUp.ResponseSignUp;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    TextView city_name;
    String name;
    Context context;
    EditText first_name,last_name,email,number,password,invite_code;
    String sfirst_name,slast_name,semail,snumber,spassword,scity,sinvite_code;
    private List<Place.Field> fields;
    final int place_picker_req_code =1;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context=this;




        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        number=findViewById(R.id.number);
        city_name=findViewById(R.id.city_name);
        invite_code=findViewById(R.id.invite_code);
        ccp=findViewById(R.id.ccp);





        fields = Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG);
        Places.initialize(getApplicationContext(), "AIzaSyAd8q-fqcHslANRJ3WZxR5cMYY1CgtBe9I");




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case place_picker_req_code:
                Place place = Autocomplete.getPlaceFromIntent(data);
                name = place.getName();
                city_name.setText(name);

                break;

        }
    }


    public void sign_up(View view) {
//        Intent intent = new Intent(SignUp.this,HomeOffline.class);
//        startActivity(intent);
        dataget();
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

    public void city_get(View view) {
        Intent i = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).build(SignUp.this);
        startActivityForResult(i,place_picker_req_code);
    }




    public void dataget(){
        sfirst_name = first_name.getText().toString();
        slast_name = last_name.getText().toString();
        semail = email.getText().toString();
        snumber = ccp.getDefaultCountryCodeWithPlus()+number.getText().toString();
        spassword = password.getText().toString();
        scity = city_name.getText().toString();
        sinvite_code = invite_code.getText().toString();

        if (sfirst_name.isEmpty() && slast_name.isEmpty() && semail.isEmpty() && snumber.isEmpty() && spassword.isEmpty() && scity.isEmpty() && sinvite_code.isEmpty())
        {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
        else{
//            Toast.makeText(this, ""+snumber, Toast.LENGTH_SHORT).show();
            signupf(sfirst_name,slast_name,semail,snumber,spassword,scity,sinvite_code);
        }
    }




    public void signupf(String first_name,String last_name,String email,String number,String password,String city,String invite_code) {
        RequestSignUp requestSignUp = new RequestSignUp();
        requestSignUp.setF_name(first_name);
        requestSignUp.setL_name(last_name);
        requestSignUp.setEmail(email);
        requestSignUp.setNumber(number);
        requestSignUp.setPassword(password);
        requestSignUp.setCity(city);
//        signUpRequest.setI_code(invite_code);


        Call<ResponseSignUp> signUpResponseCall = ApiClass.getUserServiceSignUp().userLogin(requestSignUp);
        signUpResponseCall.enqueue(new Callback<ResponseSignUp>() {
            @Override
            public void onResponse(Call<ResponseSignUp> call, Response<ResponseSignUp> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUp.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("Driver Successfully Stored"))
                    {
//                        Toast.makeText(SignUp.this, ""+response.body().data.verification_code, Toast.LENGTH_LONG).show();
//                        Toast.makeText(SignUp.this, ""+response.body().signUpData.id, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
//                    Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), PhoneVerification.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(SignUp.this, "Register Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSignUp> call, Throwable t) {
                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }

}