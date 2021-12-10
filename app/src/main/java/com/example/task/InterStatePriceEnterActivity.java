package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.RideAcceptWithPrice.AcceptRideWithPriceRequest;
import com.example.task.RideAcceptWithPrice.AcceptRideWithPriceResponse;
import com.example.task.UserServiceInterface.ApiClass;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterStatePriceEnterActivity extends AppCompatActivity {
    TextView route,minimum_price_txt,maximum_price_txt,date,time,client_name,client_gender,client_price_txt;
    ImageView client_image;
    String client_id,route_s,mini_price,max_price,date_s,time_s,client_name_s,client_gender_s,client_price_s,ride_id;
    EditText driver_price;
    Context context;
    Button enter_btn;
    private AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_state_price_enter);

        context=this;

        route = findViewById(R.id.route);
        minimum_price_txt = findViewById(R.id.minimum_price_txt);
        maximum_price_txt = findViewById(R.id.maximum_price_txt);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        client_name = findViewById(R.id.client_name);
        client_gender = findViewById(R.id.client_gender);
        client_price_txt = findViewById(R.id.client_price_txt);
        client_image = findViewById(R.id.client_image);
        driver_price = findViewById(R.id.driver_price);
        enter_btn = findViewById(R.id.enter_btn);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);

        Intent intent = getIntent();
        ride_id = intent.getStringExtra("id");
        client_id = intent.getStringExtra("client_id");
        route_s = intent.getStringExtra("route");
        client_name_s = intent.getStringExtra("client_name");
        date_s = intent.getStringExtra("date");
        time_s = intent.getStringExtra("time");
        mini_price = intent.getStringExtra("minimum_price");
        max_price = intent.getStringExtra("maximum_price");
        client_gender_s = intent.getStringExtra("client_gender");
        client_price_s = intent.getStringExtra("client_price");

        stopAnim();

        route.setText(route_s);
        client_name.setText(client_name_s);
        date.setText(date_s);
        time.setText(time_s);
        minimum_price_txt.setText("$"+mini_price);
        maximum_price_txt.setText("$"+max_price);
        client_price_txt.setText("$"+client_price_s);
        client_gender.setText(client_gender_s);


        driver_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    double c_price = Double.parseDouble(String.valueOf(s));
                    if (c_price > Double.parseDouble(mini_price) && c_price < Double.parseDouble(max_price)) {
                        client_price_s = driver_price.getText().toString();
                    } else {
                        driver_price.setError("Price should be greater than minimum and less than maximum price");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceDrive= driver_price.getText().toString();
                if (priceDrive.isEmpty())
                {
                    driver_price.setError("Required");
                }
                else{
                    startAnim();
                    rideaccept(ride_id,priceDrive,client_id);
                }
            }
        });
        

    }


    public void rideaccept(String ride_id,String price,String client_id) {
        AcceptRideWithPriceRequest acceptRideWithPriceRequest = new AcceptRideWithPriceRequest();
        acceptRideWithPriceRequest.setDriver_id(getClientId(context));
        acceptRideWithPriceRequest.setRide_id(ride_id);
        acceptRideWithPriceRequest.setPrice(price);
        acceptRideWithPriceRequest.setClient_id(client_id);


        Call<AcceptRideWithPriceResponse> signUpResponseCall = ApiClass.getUserServiceAPI().userAddDriverAddedPrice(acceptRideWithPriceRequest);
        signUpResponseCall.enqueue(new Callback<AcceptRideWithPriceResponse>() {
            @Override
            public void onResponse(Call<AcceptRideWithPriceResponse> call, Response<AcceptRideWithPriceResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(RideConfimDriverAddAmount.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if(response.body().message.equals("Success"))
                    {
                        stopAnim();

                        startActivity(new Intent(InterStatePriceEnterActivity.this,HomeOffline.class));
                        finish();
                    }

                } else {
                    stopAnim();
//                    Toast.makeText(SignUp.this, "API not Hit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcceptRideWithPriceResponse> call, Throwable t) {
                stopAnim();
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(InterStatePriceEnterActivity.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void startAnim() {
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim() {
        avi.hide();
        // or avi.smoothToHide();
    }
}