package com.example.task.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.task.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    ArrayList notification_names;
    ArrayList notification_text;
    Context context;

    public NotificationAdapter(Context context,ArrayList notification_names,ArrayList notification_text)
    {
        this.context=context;
        this.notification_names=notification_names;
        this.notification_text=notification_text;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.noti_name.setText(notification_names.get(position).toString());
        holder.noti_text.setText(notification_text.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return notification_names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView noti_name;
        TextView noti_text;
        public MyViewHolder(View itemView) {
            super(itemView);
            noti_name = itemView.findViewById(R.id.noti_name);
            noti_text = itemView.findViewById(R.id.noti_text);
        }
    }
}
