package com.example.task.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;

import java.util.ArrayList;

public class VehicleManagementAdapter extends RecyclerView.Adapter<VehicleManagementAdapter.MyViewHolder> {
    ArrayList vehicle_name;
    ArrayList plate;
    Context context;

    public VehicleManagementAdapter(Context context, ArrayList vehicle_name, ArrayList plate)
    {
        this.context=context;
        this.vehicle_name=vehicle_name;
        this.plate=plate;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public VehicleManagementAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_management_item,parent,false);
        VehicleManagementAdapter.MyViewHolder viewHolder = new VehicleManagementAdapter.MyViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(VehicleManagementAdapter.MyViewHolder holder, final int position) {
        holder.vehiclename.setText(vehicle_name.get(position).toString());
        holder.plateno.setText(plate.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return vehicle_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView vehiclename;
        TextView plateno;
        public MyViewHolder(View itemView) {
            super(itemView);
            vehiclename = itemView.findViewById(R.id.vehicle_name);
            plateno = itemView.findViewById(R.id.plate);
        }
    }
}
