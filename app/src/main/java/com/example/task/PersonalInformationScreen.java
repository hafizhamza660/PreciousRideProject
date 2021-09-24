package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PersonalInformationScreen extends AppCompatActivity {
    Spinner vehicle_manufacture,vehicle_model,vehicle_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_screen);

        vehicle_manufacture=findViewById(R.id.vehicle_manufacture);
        vehicle_model=findViewById(R.id.vehicle_model);
        vehicle_year=findViewById(R.id.vehicle_year);

//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,
//                        spinnerArray); //selected item will look like a spinner set from XML
//        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
//                .simple_spinner_dropdown_item);
//        vehicle_manufacture.setAdapter(spinnerArrayAdapter);
    }
}