package com.example.task.adapters;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.CheckRideStatusFiles.CheckRideStatusRequest;
import com.example.task.CheckRideStatusFiles.CheckRideStatusResponse;
import com.example.task.CheckStatusFiles.CheckStatusRequest;
import com.example.task.CheckStatusFiles.CheckStatusRespone;
import com.example.task.InterCityRequest.InterCityRideRequest;
import com.example.task.InterCityRequest.InterCityRideRequestResponse;
import com.example.task.InterCityRequests;
import com.example.task.InterStatePriceEnterActivity;
import com.example.task.TravelRequest;
import com.example.task.UserServiceInterface.ApiClass;

import com.example.task.InterCityNegotiate.RequestRideNegotiateInterCity;
import com.example.task.InterCityNegotiate.ResponseRideNegotiateInterCity;
import com.example.task.InterCityRequest.Data;
import com.example.task.InterCityRequestAccept.RequestRideAcceptInterCity;
import com.example.task.InterCityRequestAccept.ResponseRideAcceptInterCity;
import com.example.task.R;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterCityRideRequestListAdapter extends RecyclerView.Adapter<InterCityRideRequestListAdapter.MyViewHolder> {
    private List<Data> dataList;
    Context context;
    Activity activity;
    String r;
    public InterCityRideRequestListAdapter(Activity activity, Context context, List<Data> dataList)
    {
        this.activity=activity;
        this.context=context;
        this.dataList=dataList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public InterCityRideRequestListAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inter_city_request_item,parent,false);
        return new InterCityRideRequestListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(InterCityRideRequestListAdapter.MyViewHolder holder, final int position) {
        final Data data = dataList.get(position);

        if (data.client_image==null)
        {

        }
        else
        {
            String url= "http://precious-ride.ragzon.com/"+data.client_image;
            Picasso.get().load(url).into(holder.client_image);
        }

        holder.route.setText(data.route_name);
        holder.client_name.setText(data.client_name);
        holder.date.setText(data.date);
        holder.time.setText(data.time);
        holder.minimum_price_txt.setText("$"+data.minimum_price);
        holder.maximum_price_txt.setText("$"+data.maximum_price);
        holder.client_price_txt.setText("$"+data.client_price);
        holder.client_gender.setText(data.client_gender);

        holder.setpriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checStatusRide(data.id,data.client_id,data.route_name,data.client_name,data.date,data.time,data.minimum_price,data.maximum_price,data.client_gender,data.client_price);
//                String re = r;
//                if (re.equals("2")) {
//                    Intent intent = new Intent(activity, InterStatePriceEnterActivity.class);
//                    intent.putExtra("id", data.id);
//                    intent.putExtra("client_id", data.client_id);
//                    intent.putExtra("route", data.route_name);
//                    intent.putExtra("client_name", data.client_name);
//                    intent.putExtra("date", data.date);
//                    intent.putExtra("time", data.time);
//                    intent.putExtra("minimum_price", data.minimum_price);
//                    intent.putExtra("maximum_price", data.maximum_price);
//                    intent.putExtra("client_gender", data.client_gender);
//                    intent.putExtra("client_price", data.client_price);
//                    activity.startActivity(intent);
//                }
//                else{
//
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView route,minimum_price_txt,maximum_price_txt,date,time,client_name,client_gender,client_price_txt;

        Button setpriceBtn;

        ImageView client_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            route = itemView.findViewById(R.id.route);
            minimum_price_txt = itemView.findViewById(R.id.minimum_price_txt);
            maximum_price_txt = itemView.findViewById(R.id.maximum_price_txt);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            client_name = itemView.findViewById(R.id.client_name);
            client_gender = itemView.findViewById(R.id.client_gender);
            client_price_txt = itemView.findViewById(R.id.client_price_txt);
            client_image = itemView.findViewById(R.id.client_image);
            setpriceBtn = itemView.findViewById(R.id.setpriceBtn);

        }
    }

    public String getStringAddres(Double lat, Double lng) {
        String address = "";
        String city = "";
        Geocoder geocoder;
        List<Address> addresses;

        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address + "  " + city;
    }




    public void rideaccept(String driver_id,String ride_id) {
        RequestRideAcceptInterCity requestRideAcceptInterCity = new RequestRideAcceptInterCity();
       requestRideAcceptInterCity.setDriver_id(driver_id);
       requestRideAcceptInterCity.setRide_id(ride_id);


        Call<ResponseRideAcceptInterCity> signUpResponseCall = ApiClass.getUserServiceAPI().userInterCityDriverAccepts(requestRideAcceptInterCity);
        signUpResponseCall.enqueue(new Callback<ResponseRideAcceptInterCity>() {
            @Override
            public void onResponse(Call<ResponseRideAcceptInterCity> call, Response<ResponseRideAcceptInterCity> response) {
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
                    Toast.makeText(context, "Not", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRideAcceptInterCity> call, Throwable t) {
//                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(context, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checStatusRide(String id,String client_id,String route, String client_name,String date,String time, String minimum_price,String maximum_price,String client_gender,String client_price) {

        CheckStatusRequest checkRideStatusRequest = new CheckStatusRequest();
        checkRideStatusRequest.setDriver_id(getClientId(context));
        checkRideStatusRequest.setRide_id(id);


        Call<CheckStatusRespone> signUpResponseCall = ApiClass.getUserServiceAPI().userCheckStatusRide(checkRideStatusRequest);
        signUpResponseCall.enqueue(new Callback<CheckStatusRespone>() {
            @Override
            public void onResponse(Call<CheckStatusRespone> call, Response<CheckStatusRespone> response) {
                if (response.isSuccessful()) {

                    if (response.body().status.equals("0"))
                    {
                        Toast.makeText(context, "The ride is canceled or deleted", Toast.LENGTH_SHORT).show();
                        r="0";
                    }
                    else if(response.body().status.equals("1"))
                    {
                        Toast.makeText(context, "You already requested this ride", Toast.LENGTH_SHORT).show();
                        r="1";
                    }
                    else if (response.body().status.equals("2")){
//                        Toast.makeText(context, "The ride is canceled or deleted", Toast.LENGTH_SHORT).show();
                        r="2";

                        Intent intent = new Intent(activity, InterStatePriceEnterActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("client_id", client_id);
                        intent.putExtra("route", route);
                        intent.putExtra("client_name", client_name);
                        intent.putExtra("date", date);
                        intent.putExtra("time", time);
                        intent.putExtra("minimum_price", minimum_price);
                        intent.putExtra("maximum_price", maximum_price);
                        intent.putExtra("client_gender", client_gender);
                        intent.putExtra("client_price", client_price);
                        activity.startActivity(intent);
                    }
//
                } else {
//                    Toast.makeText(InterCityRequests.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckStatusRespone> call, Throwable t) {
//                Toast.makeText(InterCityRequests.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(context, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
//        return r;
    }


    public void ridenegotiate(String driver_id,String ride_id,String price) {
        RequestRideNegotiateInterCity requestRideNegotiate = new RequestRideNegotiateInterCity();
        requestRideNegotiate.setDriver_id(driver_id);
        requestRideNegotiate.setRide_id(ride_id);
        requestRideNegotiate.setNegotiate_price(price);


        Call<ResponseRideNegotiateInterCity> responseRideNegotiateCall = ApiClass.getUserServiceAPI().userInterCityDriverNegotitate(requestRideNegotiate);
        responseRideNegotiateCall.enqueue(new Callback<ResponseRideNegotiateInterCity>() {
            @Override
            public void onResponse(Call<ResponseRideNegotiateInterCity> call, Response<ResponseRideNegotiateInterCity> response) {
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
                    Toast.makeText(context, "Not", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRideNegotiateInterCity> call, Throwable t) {
//                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}
