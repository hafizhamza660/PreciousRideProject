package com.example.task.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.task.AllNotificiationFiles.Data;
import com.example.task.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private List<Data> data;
    Context context;

    public NotificationAdapter(Context context,List<Data> data)
    {
        this.context=context;
        this.data=data;

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
        final Data datalist = data.get(position);

        if (datalist.status.equals("0"))
        {
            holder.unread_layout.setVisibility(View.VISIBLE);
            holder.read_layout.setVisibility(View.GONE);
            holder.unread_noti_name.setText(datalist.text);
        }
        else if (datalist.status.equals("1"))
        {
            holder.unread_layout.setVisibility(View.GONE);
            holder.read_layout.setVisibility(View.VISIBLE);
            holder.read_noti_name.setText(datalist.text);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView unread_layout,read_layout;
        TextView unread_noti_name,unread_noti_text,read_noti_name,read_noti_text;
        public MyViewHolder(View itemView) {
            super(itemView);
            unread_layout = itemView.findViewById(R.id.unread_layout);
            read_layout = itemView.findViewById(R.id.read_layout);
            unread_noti_name = itemView.findViewById(R.id.unread_noti_name);
            unread_noti_text = itemView.findViewById(R.id.unread_noti_text);
            read_noti_name = itemView.findViewById(R.id.read_noti_name);
            read_noti_text = itemView.findViewById(R.id.read_noti_text);
        }
    }
}
