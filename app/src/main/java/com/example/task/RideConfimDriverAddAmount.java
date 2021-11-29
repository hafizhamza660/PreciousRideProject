package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;
import static com.example.task.Session.SaveSharedPreference.setRideId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.RideAcceptWithPrice.AcceptRideWithPriceRequest;
import com.example.task.RideAcceptWithPrice.AcceptRideWithPriceResponse;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideConfimDriverAddAmount extends AppCompatActivity {
    TextView pickup_location,dropofflocation,price,client_price,client_name,price_top,rideId,name_client;
    String s_id,s_start_lat,s_start_long,s_end_lat,s_end_long,s_price,s_client_price,s_negotiated_price,s_distance,s_status,s_client_id,s_driver_id,s_name_client,s_client_image;
    Context context;
    ImageView client_image_img;
    ConstraintLayout gotopickupLayout;
    EditText driver_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_confim_driver_add_amount);

        context=this;

        pickup_location=findViewById(R.id.pickup_location);
        dropofflocation=findViewById(R.id.dropoff_location);
        price=findViewById(R.id.price);
        client_price=findViewById(R.id.client_price);
        name_client=findViewById(R.id.name_client);
        client_image_img=findViewById(R.id.client_image);
        gotopickupLayout=findViewById(R.id.gotopickupLayout);
        driver_price=findViewById(R.id.driver_price);

        client_name=findViewById(R.id.client_name);
        price_top=findViewById(R.id.price_top);
        rideId=findViewById(R.id.rideId);

        Intent intent= getIntent();
        s_id= intent.getStringExtra("id");
        s_start_lat= intent.getStringExtra("start_lat");
        s_start_long= intent.getStringExtra("start_long");
        s_end_lat= intent.getStringExtra("end_lat");
        s_end_long= intent.getStringExtra("end_long");
        s_price= intent.getStringExtra("price");
        s_client_price= intent.getStringExtra("client_price");
        s_negotiated_price= intent.getStringExtra("negotiated_price");
        s_client_image= intent.getStringExtra("image_url");
        s_distance= intent.getStringExtra("distance");
        s_status= intent.getStringExtra("status");
        s_client_id= intent.getStringExtra("client_id");
        s_driver_id= intent.getStringExtra("driver_id");
        s_name_client= intent.getStringExtra("name_client");


//        clientDetails(s_id);


        double picklat = Double.parseDouble(s_start_lat);
        double picklng = Double.parseDouble(s_start_long);
        String pickup = getStringAddres(picklat,picklng);

        double droplat = Double.parseDouble(s_end_lat);
        double droplng = Double.parseDouble(s_end_long);
        String dropoff = getStringAddres(droplat,droplng);

        rideId.setText("#"+s_id);
        pickup_location.setText(pickup);
        dropofflocation.setText(dropoff);
        price.setText("$"+s_price);
        client_price.setText("$"+s_client_price);
        price_top.setText(s_price);
        name_client.setText(s_name_client);
//        String url= "http://precious-ride.ragzon.com/"+s_client_image;
        Picasso.get().load(s_client_image).into(client_image_img);

        gotopickupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String driver_price_s = driver_price.getText().toString();
                if (driver_price_s.isEmpty())
                {
                    driver_price.setError("Required");
                }
                else{
                    setRideId(context,s_id);
                    rideaccept(s_driver_id,s_id,driver_price_s);
                }
            }
        });



    }

    public String getStringAddres(Double lat, Double lng) {
        String address = "";
        String city = "";
        Geocoder geocoder;
        List<Address> addresses;

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address + "  " + city;
    }


    public void rideaccept(String driver_id,String ride_id,String price) {
        AcceptRideWithPriceRequest acceptRideWithPriceRequest = new AcceptRideWithPriceRequest();
        acceptRideWithPriceRequest.setDriver_id(getClientId(context));
        acceptRideWithPriceRequest.setRide_id(ride_id);
        acceptRideWithPriceRequest.setPrice(price);


        Call<AcceptRideWithPriceResponse> signUpResponseCall = ApiClass.getUserServiceAPI().userAddDriverAddedPrice(acceptRideWithPriceRequest);
        signUpResponseCall.enqueue(new Callback<AcceptRideWithPriceResponse>() {
            @Override
            public void onResponse(Call<AcceptRideWithPriceResponse> call, Response<AcceptRideWithPriceResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(RideConfimDriverAddAmount.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if(response.body().message.equals("Success"))
                    {

                        startActivity(new Intent(RideConfimDriverAddAmount.this,WaitingScreenActivity.class));
                        finish();
                    }

                } else {
//                    Toast.makeText(SignUp.this, "API not Hit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcceptRideWithPriceResponse> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


}