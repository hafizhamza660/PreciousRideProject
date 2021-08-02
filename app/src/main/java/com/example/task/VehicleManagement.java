package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.task.adapters.InviteFiendListAdapter;
import com.example.task.adapters.VehicleManagementAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class VehicleManagement extends AppCompatActivity {
    ArrayList vehicle_name = new ArrayList<>(Arrays.asList("Madza", "Mitsubishi Outlander","Corolla","Toyota","BMW","Mercedes","Range Rover","Prado"));
    ArrayList plate = new ArrayList<>(Arrays.asList("43A 235.70","43A 125.84","43A 565.86","43A 745.75","43A 444.45","43A 555.98","43A 777.84","43A 154.84"));
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_management);

        recyclerView = findViewById(R.id.recycler_view_vehicle);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        VehicleManagementAdapter inviteFiendListAdapter = new VehicleManagementAdapter(VehicleManagement.this, vehicle_name,plate);
        recyclerView.setAdapter(inviteFiendListAdapter);
    }

    public void back_button(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void add_vehicle_document(View view) {
        startActivity(new Intent(VehicleManagement.this,AddVehicleManagement.class));
    }
}