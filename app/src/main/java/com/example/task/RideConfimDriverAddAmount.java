package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RideConfimDriverAddAmount extends AppCompatActivity {
    TextView pickup_location,dropofflocation,price,client_price,client_name,price_top,rideId;
    String s_id,s_start_lat,s_start_long,s_end_lat,s_end_long,s_price,s_client_price,s_negotiated_price,s_distance,s_status,s_client_id,s_driver_id;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_confim_driver_add_amount);

        context=this;

        pickup_location=findViewById(R.id.pickup_location);
        dropofflocation=findViewById(R.id.dropoff_location);
        price=findViewById(R.id.price);
        client_price=findViewById(R.id.client_price);

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
        s_distance= intent.getStringExtra("distance");
        s_status= intent.getStringExtra("status");
        s_client_id= intent.getStringExtra("client_id");
        s_driver_id= intent.getStringExtra("driver_id");

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
}