package com.example.task.adapters;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.task.HomeOnlineBookingDetails;
import com.example.task.HomeSwipeUp;
import com.example.task.R;
import com.example.task.RideRequestedHistory.Data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class StackAdapter extends ArrayAdapter {
    private List<Data> dataList;
    Context c;
    int itemLayout;
    int size;
    CardView card_stack;
    Button accept,ignore_btn;
    TextView timer,pickup_location,dropoff_location,price;

    public StackAdapter(List<Data> dataList,Context context,int resource)
    {
        super(context,resource,dataList);
        this.dataList=dataList;
        c =context;
        itemLayout =resource;
        size = dataList.size();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        }

        final Data data = dataList.get(position);

        double picklat = Double.parseDouble(data.start_lat);
        double picklng = Double.parseDouble(data.start_long);
        String pickup = getStringAddres(picklat,picklng);

        double droplat = Double.parseDouble(data.end_lat);
        double droplng = Double.parseDouble(data.end_long);
        String dropoff = getStringAddres(droplat,droplng);



        card_stack= convertView.findViewById(R.id.card_stack);
         accept = convertView.findViewById(R.id.accept);
        ignore_btn = convertView.findViewById(R.id.ignore_btn);
         timer = convertView.findViewById(R.id.timer_1q);
        pickup_location = convertView.findViewById(R.id.pickup_location);
        dropoff_location = convertView.findViewById(R.id.dropoff_location);
        price = convertView.findViewById(R.id.price);
        timer.setVisibility(View.GONE);

//        reverseTimer(30,timer);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(c, HomeOnlineBookingDetails.class);
                intent.putExtra("id",data.id);
                intent.putExtra("start_lat",data.start_lat);
                intent.putExtra("start_long",data.start_long);
                intent.putExtra("end_lat",data.end_lat);
                intent.putExtra("end_long",data.end_long);
                intent.putExtra("price",data.price);
                intent.putExtra("negotiated_price",data.negotiated_price);
                intent.putExtra("distance",data.distance);
                intent.putExtra("status",data.status);
                intent.putExtra("client_id",data.client_id);
                intent.putExtra("driver_id",data.driver_id);
                c.startActivity(intent);

            }
        });
        ignore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(c, HomeOnlineBookingDetails.class));
            }
        });

        pickup_location.setText(pickup);
        dropoff_location.setText(dropoff);
        price.setText(data.price);

        return convertView;
    }


    public void reverseTimer(int Seconds,final TextView tv){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
//                size = size -1;
                card_stack.setVisibility(View.GONE);
//                tv.setText("Completed");
            }
        }.start();
    }


    public String getStringAddres(Double lat, Double lng) {
        String address = "";
        String city = "";
        Geocoder geocoder;
        List<Address> addresses;

        geocoder = new Geocoder(getContext(), Locale.getDefault());

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
