package com.example.task.adapters;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.API.ApiClass;

import com.example.task.InterCityNegotiate.RequestRideNegotiateInterCity;
import com.example.task.InterCityNegotiate.ResponseRideNegotiateInterCity;
import com.example.task.InterCityRequest.Data;
import com.example.task.InterCityRequestAccept.RequestRideAcceptInterCity;
import com.example.task.InterCityRequestAccept.ResponseRideAcceptInterCity;
import com.example.task.R;


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

        double picklat = Double.parseDouble(data.start_lat);
        double picklng = Double.parseDouble(data.start_long);
        String pickup = getStringAddres(picklat,picklng);

        double droplat = Double.parseDouble(data.end_lat);
        double droplng = Double.parseDouble(data.end_long);
        String dropoff = getStringAddres(droplat,droplng);

        holder.pickup_location.setText(pickup);
        holder.dropoff_location.setText(dropoff);
        holder.price.setText(data.price);
        holder.time.setText(data.time);
        holder.date.setText(data.date);
        holder.description.setText(data.description);

        if(data.negotiated_price==null)
        {

        }
        else{
            holder.nego_price.setText(data.negotiated_price);
            holder.nego_price.setVisibility(View.VISIBLE);
        }

        holder.card_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.nego_layout.getVisibility()==View.VISIBLE) {
                    holder.nego_layout.setVisibility(View.GONE);
                }
                else
                {
                    holder.nego_layout.setVisibility(View.VISIBLE);
                }

            }
        });

        holder.accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getClientId(context);
                rideaccept(id,data.id);
            }
        });

        holder.negotiate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    holder.nego_layout.setVisibility(View.GONE);
                    holder.nego_enter_layout.setVisibility(View.VISIBLE);


            }
        });

        holder.enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getClientId(context);
                ridenegotiate(id,data.id,holder.negotiate_edt.getText().toString());
                holder.nego_layout.setVisibility(View.VISIBLE);
                holder.nego_enter_layout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pickup_location,dropoff_location,price,time,date,description,nego_price;
        LinearLayout nego_layout,nego_enter_layout;
        Button negotiate_btn,accept_btn,enter_btn;
        CardView card_offer;
        EditText negotiate_edt;
        public MyViewHolder(View itemView) {
            super(itemView);
            pickup_location = itemView.findViewById(R.id.pickup_location);
            dropoff_location = itemView.findViewById(R.id.dropoff_location);
            price = itemView.findViewById(R.id.price);
            nego_layout = itemView.findViewById(R.id.nego_layout);
            card_offer = itemView.findViewById(R.id.card_offer);
            accept_btn = itemView.findViewById(R.id.accept_btn);
            negotiate_btn = itemView.findViewById(R.id.negotiate_btn);
            nego_enter_layout = itemView.findViewById(R.id.nego_enter_layout);
            enter_btn = itemView.findViewById(R.id.enter_btn);
            negotiate_edt = itemView.findViewById(R.id.negotiate_edt);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.desciption);
            nego_price = itemView.findViewById(R.id.nego_price);
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


        Call<ResponseRideAcceptInterCity> signUpResponseCall = ApiClass.getUserServiceRideAcceptInterCity().userLogin(requestRideAcceptInterCity);
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
                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


    public void ridenegotiate(String driver_id,String ride_id,String price) {
        RequestRideNegotiateInterCity requestRideNegotiate = new RequestRideNegotiateInterCity();
        requestRideNegotiate.setDriver_id(driver_id);
        requestRideNegotiate.setRide_id(ride_id);
        requestRideNegotiate.setNegotiate_price(price);


        Call<ResponseRideNegotiateInterCity> responseRideNegotiateCall = ApiClass.getUserServiceRideNegotiateInterCity().userLogin(requestRideNegotiate);
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
                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}