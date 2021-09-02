package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.API.ApiClass;
import com.example.task.RideCancel.RideCancelRequest;
import com.example.task.RideCancel.RideCancelResponse;
import com.example.task.RideNegotiate.RequestRideNegotiate;
import com.example.task.RideNegotiate.ResponseRideNegotiate;
import com.example.task.RideRequestedHistory.Data;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeOnlineBookingDetails extends AppCompatActivity {
    TextView pickup_location,dropofflocation,price;
    String s_id,s_start_lat,s_start_long,s_end_lat,s_end_long,s_price,s_negotiated_price,s_distance,s_status,s_client_id,s_driver_id;
    CardView negotiate_btn,cancel_btn;
    LinearLayout nego_enter_layout;
    Button enter_btn;
    Context context;
    EditText negotiate_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_online_booking_details);

        context=this;

        pickup_location=findViewById(R.id.pickup_location);
        dropofflocation=findViewById(R.id.dropoff_location);
        price=findViewById(R.id.price);
        negotiate_btn=findViewById(R.id.negotiate_btn);
        nego_enter_layout=findViewById(R.id.nego_enter_layout);
        enter_btn=findViewById(R.id.enter_btn);
        negotiate_edt=findViewById(R.id.negotiate_edt);
        cancel_btn=findViewById(R.id.cancel_btn);

        Intent intent= getIntent();
        s_id= intent.getStringExtra("id");
        s_start_lat= intent.getStringExtra("start_lat");
        s_start_long= intent.getStringExtra("start_long");
        s_end_lat= intent.getStringExtra("end_lat");
        s_end_long= intent.getStringExtra("end_long");
        s_price= intent.getStringExtra("price");
        s_negotiated_price= intent.getStringExtra("negotiated_price");
        s_distance= intent.getStringExtra("distance");
        s_status= intent.getStringExtra("status");
        s_client_id= intent.getStringExtra("client_id");
        s_driver_id= intent.getStringExtra("driver_id");


        double picklat = Double.parseDouble(s_start_lat);
        double picklng = Double.parseDouble(s_start_long);
        String pickup = getStringAddres(picklat,picklng);

        double droplat = Double.parseDouble(s_end_lat);
        double droplng = Double.parseDouble(s_end_long);
        String dropoff = getStringAddres(droplat,droplng);

        pickup_location.setText(pickup);
        dropofflocation.setText(dropoff);
        price.setText(s_price);



        negotiate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                negotiate_btn.setVisibility(View.GONE);
                nego_enter_layout.setVisibility(View.VISIBLE);


            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String client_id = getClientId(context);
                ridecancel(client_id,s_id);


            }
        });

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String client_id = getClientId(context);
                ridenegotiate(client_id,s_id,negotiate_edt.getText().toString());
                negotiate_btn.setVisibility(View.VISIBLE);
                nego_enter_layout.setVisibility(View.GONE);
            }
        });


    }

    public void back_button(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void gotopickup(View view) {
        startActivity(new Intent(HomeOnlineBookingDetails.this,HomeOnlineBookingDetailsGotopickup.class));
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


    public void ridenegotiate(String driver_id,String ride_id,String price) {
        RequestRideNegotiate requestRideNegotiate = new RequestRideNegotiate();
        requestRideNegotiate.setDriver_id(driver_id);
        requestRideNegotiate.setRide_id(ride_id);
        requestRideNegotiate.setNegotiate_price(price);


        Call<ResponseRideNegotiate> responseRideNegotiateCall = ApiClass.getUserServiceRideNegotiate().userLogin(requestRideNegotiate);
        responseRideNegotiateCall.enqueue(new Callback<ResponseRideNegotiate>() {
            @Override
            public void onResponse(Call<ResponseRideNegotiate> call, Response<ResponseRideNegotiate> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, ""+response.body().message, Toast.LENGTH_SHORT).show();
//                    if (response.body().message.equals("Ride confirmed successfully"))
//                    {
//                        Toast.makeText(SignUp.this, ""+response.body().data.verification_code, Toast.LENGTH_LONG).show();
//                        Toast.makeText(SignUp.this, ""+response.body().signUpData.id, Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(SignUp.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), PhoneVerification.class);
//                    startActivity(intent);
                } else {
//                    Toast.makeText(context, "Not", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRideNegotiate> call, Throwable t) {
//                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


    public void ridecancel(String driver_id,String ride_id) {
        RideCancelRequest rideCancelRequest = new RideCancelRequest();
        rideCancelRequest.setDriver_id(driver_id);
        rideCancelRequest.setRide_id(ride_id);


        Call<RideCancelResponse> responseRideNegotiateCall = ApiClass.getUserServiceRideCancel().userLogin(rideCancelRequest);
        responseRideNegotiateCall.enqueue(new Callback<RideCancelResponse>() {
            @Override
            public void onResponse(Call<RideCancelResponse> call, Response<RideCancelResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, ""+response.body().message, Toast.LENGTH_SHORT).show();
//                    if (response.body().message.equals("Ride confirmed successfully"))
//                    {
//                        Toast.makeText(SignUp.this, ""+response.body().data.verification_code, Toast.LENGTH_LONG).show();
//                        Toast.makeText(SignUp.this, ""+response.body().signUpData.id, Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(SignUp.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), PhoneVerification.class);
//                    startActivity(intent);
                } else {
//                    Toast.makeText(context, "Not", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RideCancelResponse> call, Throwable t) {
//                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}