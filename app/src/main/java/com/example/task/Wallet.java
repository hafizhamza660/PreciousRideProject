package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.task.adapters.PaymentHistoryAdapter;
import com.example.task.adapters.VehicleManagementAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class Wallet extends AppCompatActivity {
    ArrayList payment_person_name = new ArrayList<>(Arrays.asList("Elva Barnett", "Isaiah Francis","Lula Briggs","Ray Young","Betty Palmer"));
    ArrayList payment_number = new ArrayList<>(Arrays.asList("#740136","#539642","#123146","#521936","#129936"));
    ArrayList payment_amount = new ArrayList<>(Arrays.asList("$25.00","$12.00","$34.00","$33.00","$15.00"));
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        recyclerView = findViewById(R.id.recycler_view_payment);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        PaymentHistoryAdapter inviteFiendListAdapter = new PaymentHistoryAdapter(Wallet.this, payment_person_name,payment_number,payment_amount);
        recyclerView.setAdapter(inviteFiendListAdapter);
    }

    public void payment_method(View view) {
        startActivity(new Intent(Wallet.this,PaymentMethod.class));
    }
}