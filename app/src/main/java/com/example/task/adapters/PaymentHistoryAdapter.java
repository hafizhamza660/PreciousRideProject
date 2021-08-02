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

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.MyViewHolder> {
    ArrayList payment_person_name;
    ArrayList payment_number;
    ArrayList payment_amount;
    Context context;

    public PaymentHistoryAdapter(Context context, ArrayList payment_person_name, ArrayList payment_number,ArrayList payment_amount)
    {
        this.context=context;
        this.payment_person_name=payment_person_name;
        this.payment_number=payment_number;
        this.payment_amount=payment_amount;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public PaymentHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item,parent,false);
        PaymentHistoryAdapter.MyViewHolder viewHolder = new PaymentHistoryAdapter.MyViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(PaymentHistoryAdapter.MyViewHolder holder, final int position) {
        holder.paymentpersonname.setText(payment_person_name.get(position).toString());
        holder.paymentnumber.setText(payment_number.get(position).toString());
        holder.paymentamount.setText(payment_amount.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return payment_person_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView paymentpersonname;
        TextView paymentnumber;
        TextView paymentamount;
        public MyViewHolder(View itemView) {
            super(itemView);
            paymentpersonname = itemView.findViewById(R.id.payment_person_name);
            paymentnumber = itemView.findViewById(R.id.payment_number);
            paymentamount = itemView.findViewById(R.id.payment_amount);
        }
    }
}
