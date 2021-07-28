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

public class InviteFiendListAdapter extends RecyclerView.Adapter<InviteFiendListAdapter.MyViewHolder> {
    ArrayList friends_name;
    ArrayList mutual_friend_count;
    Context context;

    public InviteFiendListAdapter(Context context,ArrayList friends_name,ArrayList mutual_friend_count)
    {
        this.context=context;
        this.friends_name=friends_name;
        this.mutual_friend_count=mutual_friend_count;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public InviteFiendListAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_list_item,parent,false);
        InviteFiendListAdapter.MyViewHolder viewHolder = new InviteFiendListAdapter.MyViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(InviteFiendListAdapter.MyViewHolder holder, final int position) {
        holder.username.setText(friends_name.get(position).toString());
        holder.friendlist.setText(mutual_friend_count.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return friends_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView friendlist;
        public MyViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.user_name);
            friendlist = itemView.findViewById(R.id.friend_list);
        }
    }
}
