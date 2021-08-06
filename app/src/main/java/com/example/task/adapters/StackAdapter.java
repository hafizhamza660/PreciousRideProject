package com.example.task.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
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

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class StackAdapter extends ArrayAdapter {
    List<String> numberword;
    Context c;
    int itemLayout;
    int size;
    CardView card_stack;
    Button accept;
    TextView timer;

    public StackAdapter(List<String> number,Context context,int resource)
    {
        super(context,resource,number);
        numberword=number;
        c =context;
        itemLayout =resource;
        size = numberword.size();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return numberword.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        }

        card_stack= convertView.findViewById(R.id.card_stack);
         accept = convertView.findViewById(R.id.accept);
         timer = convertView.findViewById(R.id.timer_1q);

        reverseTimer(30,timer);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(c, HomeOnlineBookingDetails.class));
            }
        });


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
}
