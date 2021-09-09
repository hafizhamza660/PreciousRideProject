package com.example.task.adapters;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.Html;
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
import com.example.task.DataSendFiles.NameValuePairs;
import com.example.task.DataSendFiles.Steps;
import com.example.task.R;
import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;
import com.example.task.RideCancel.RideCancelRequest;
import com.example.task.RideCancel.RideCancelResponse;
import com.example.task.RideNegotiate.RequestRideNegotiate;
import com.example.task.RideNegotiate.ResponseRideNegotiate;
import com.example.task.RideRequestFiles.Data;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideStepsListAdapter extends RecyclerView.Adapter<RideStepsListAdapter.MyViewHolder> {
    private List<Steps> steps;
    Context context;

    public RideStepsListAdapter(Context context, List<Steps> steps)
    {
        this.context=context;
        this.steps=steps;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public RideStepsListAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mapstepsitem,parent,false);
        return new RideStepsListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RideStepsListAdapter.MyViewHolder holder, final int position) {
        final Steps stepslist = steps.get(position);

        holder.route.setText(Html.fromHtml(stepslist.nameValuePairs.html_instructions));
        holder.distance_route.setText(stepslist.nameValuePairs.distance.nameValuePairs.text);

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView route,distance_route;
        public MyViewHolder(View itemView) {
            super(itemView);
            route = itemView.findViewById(R.id.route);
            distance_route = itemView.findViewById(R.id.distance_route);


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




}
