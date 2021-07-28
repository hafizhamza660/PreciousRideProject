package com.example.task.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.task.HomeSwipeUp;
import com.example.task.R;

import java.util.List;

public class StackAdapter extends ArrayAdapter {
    List<String> numberword;
    Context c;
    int itemLayout;

    public StackAdapter(List<String> number,Context context,int resource)
    {
        super(context,resource,number);
        numberword=number;
        c =context;
        itemLayout =resource;
    }

    @Override
    public int getCount() {
        return numberword.size();
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

        CardView card_stack = convertView.findViewById(R.id.card_stack);

        card_stack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(getContext(), HomeSwipeUp.class));
            }
        });

        return convertView;
    }
}
