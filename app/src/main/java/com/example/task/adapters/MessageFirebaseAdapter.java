package com.example.task.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.Models.Chat;
import com.example.task.R;

import java.util.List;


public class MessageFirebaseAdapter extends RecyclerView.Adapter<MessageFirebaseAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;
//    public String imageurl;
    public String driverId;


    public MessageFirebaseAdapter(Context context, List<Chat> mChat,String driverId)
    {
        this.mChat = mChat;
        this.mContext = context;
//        this.imageurl = imageurl;
        this.driverId = driverId;
    }




    @NonNull
    @Override
    public MessageFirebaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_right, parent, false);
            return new MessageFirebaseAdapter.ViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_incoming, parent, false);
            return new MessageFirebaseAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageFirebaseAdapter.ViewHolder holder, int position) {

        Chat chat = mChat.get(position);

        Log.d("TagMessages","Message : "+chat.getMessage());
        holder.show_message.setText(chat.getMessage());
//
//        if (imageurl.equals("default")){
//            holder.imageView.setImageResource(R.mipmap.ic_launcher);
//        }
//        else
//        {
//            Glide.with(mContext).load(imageurl).into(holder.imageView);
//        }
//
//        if (position == mChat.size()-1)
//        {
//            if (chat.isIsseen())
//            {
//                holder.txt_seen.setText("seen");
//            }
//            else {
//                holder.txt_seen.setText("Delivered");
//            }
//        }
//        else {
//            holder.txt_seen.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
//        public CircleImageView imageView;
//        public TextView txt_seen;

        public ViewHolder(View itemView){
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
//            imageView = itemView.findViewById(R.id.profile_image);
//            txt_seen = itemView.findViewById(R.id.txt_seen);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mChat.get(position).getSender().equals(driverId))
        {
            return MSG_TYPE_RIGHT;
        }
        else {
            return MSG_TYPE_LEFT;
        }
    }
}