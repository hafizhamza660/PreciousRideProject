package com.example.task.adapters;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.task.DocumentDataFiles.Data;
import com.example.task.HomeOnlineBookingDetailsGotopickup;
import com.example.task.R;
import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;
import com.example.task.RideCancel.RideCancelRequest;
import com.example.task.RideCancel.RideCancelResponse;
import com.example.task.RideNegotiate.RequestRideNegotiate;
import com.example.task.RideNegotiate.ResponseRideNegotiate;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentsgetListAdapter extends RecyclerView.Adapter<DocumentsgetListAdapter.MyViewHolder> {
    private List<Data> dataList;
    Context context;
    Activity activity;
    public DocumentsgetListAdapter(Activity activity, Context context, List<Data> dataList)
    {
        this.activity=activity;
        this.context=context;
        this.dataList=dataList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public DocumentsgetListAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.documents_all_item,parent,false);
        return new DocumentsgetListAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DocumentsgetListAdapter.MyViewHolder holder, final int position) {
        final Data data = dataList.get(position);
        holder.name_licenses.setText(data.name);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_licenses;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_licenses= itemView.findViewById(R.id.name_licenses);

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
