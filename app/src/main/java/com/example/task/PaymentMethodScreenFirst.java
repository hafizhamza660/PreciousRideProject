package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PaymentMethodScreenFirst extends AppCompatActivity {
    Spinner billing_type_spinner,bank_name_spinner;
    String[] arry1,arry2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_screen_first);

        billing_type_spinner=findViewById(R.id.billing_type_spinner);
        bank_name_spinner=findViewById(R.id.bank_name_spinner);


        arry1=getResources().getStringArray(R.array.account_type_arrays);
        arry2=getResources().getStringArray(R.array.bank_names_arrays);

        spinnerArrayset(billing_type_spinner,arry1);
        spinnerArrayset(bank_name_spinner,arry2);

    }

    void spinnerArrayset(Spinner spinner,String[] arry){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arry);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}