package com.example.task.Dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.task.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CurrencySelection extends Dialog {
    Activity activity;
    Spinner spinner_currency;
    public CurrencySelection(Activity activity) {
        super(activity);
        this.activity = activity;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_currency_selection);

//        FloatingActionButton floatingActionButton2 = findViewById(R.id.floatingActionButton2);

        addItemsOnSpinner2();
    }

    public void addItemsOnSpinner2() {

        spinner_currency = (Spinner) findViewById(R.id.spinner_currency);
        List<String> list = new ArrayList<String>();
        list.add("USD");
        list.add("AUD");
        list.add("CAD");
        list.add("INR");
        list.add("PKR");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_currency.setAdapter(dataAdapter);
    }

//    public void addListenerOnSpinnerItemSelection() {
//        spinner1 = (Spinner) findViewById(R.id.spinner1);
//        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//    }

    // get the selected dropdown list value
//    public void addListenerOnButton() {
//
////        spinner1 = (Spinner) findViewById(R.id.spinner1);
//        spinner2 = (Spinner) findViewById(R.id.spinner2);
//        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//
//        btnSubmit.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MyAndroidAppActivity.this,
//                        "OnClickListener : " +
//                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
//                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }
}