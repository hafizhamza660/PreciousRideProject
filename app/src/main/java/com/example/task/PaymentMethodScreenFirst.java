package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.AccountDetailsFiles.RequestAccountDetails;
import com.example.task.AccountDetailsFiles.ResponseAccountDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodScreenFirst extends AppCompatActivity {
    Spinner billing_type_spinner,bank_name_spinner;
    String[] arry1,arry2;
    CheckBox vat_liability_check;
    String check_agree;
    EditText vat_number,bank_account_holder_name,nuban_account_number;
    Button finish_btn;
    ProgressBar simpleProgressBar;
    ConstraintLayout rootContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_screen_first);

        rootContainer=findViewById(R.id.rootContainer);
        billing_type_spinner=findViewById(R.id.billing_type_spinner);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        bank_name_spinner=findViewById(R.id.bank_name_spinner);
        vat_liability_check=findViewById(R.id.vat_liability_check);
        vat_number=findViewById(R.id.vat_number);
        bank_account_holder_name=findViewById(R.id.bank_account_holder_name);
        nuban_account_number=findViewById(R.id.nuban_account_number);
        finish_btn=findViewById(R.id.finish_btn);


        arry1=getResources().getStringArray(R.array.account_type_arrays);
        arry2=getResources().getStringArray(R.array.bank_names_arrays);

        spinnerArrayset(billing_type_spinner,arry1);
        spinnerArrayset(bank_name_spinner,arry2);

        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validationCheck())
                {
                    finish_btn.setEnabled(false);
//                    rootContainer.setEnabled(false);
                    simpleProgressBar.setVisibility(View.VISIBLE);
                    String billing_type,vat_liability_status,vat_number_s,holder_name,Nuban_account_number,bank_name;
                    billing_type= billing_type_spinner.getSelectedItem().toString();
                    vat_liability_status= check_agree;
                    vat_number_s= vat_number.getText().toString();
                    holder_name= bank_account_holder_name.getText().toString();
                    Nuban_account_number =nuban_account_number.getText().toString();
                    bank_name = bank_name_spinner.getSelectedItem().toString();
                    accountdetails(billing_type,vat_liability_status,vat_number_s,holder_name,Nuban_account_number,bank_name);
                }
            }
        });

    }

    void spinnerArrayset(Spinner spinner,String[] arry){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arry);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    boolean validationCheck()
    {
        String vatnumber= vat_number.getText().toString();
        String holdername= bank_account_holder_name.getText().toString();
        String nubanaccountnumber= nuban_account_number.getText().toString();
        if (billing_type_spinner.getSelectedItemPosition()==0){
            Toast.makeText(this, "Set account type!", Toast.LENGTH_SHORT).show();
        }
        else if (vatnumber.isEmpty())
        {
            vat_number.setError("Required");
            vat_number.setFocusable(true);
        }
        else if (holdername.isEmpty())
        {
            bank_account_holder_name.setError("Required");
            bank_account_holder_name.setFocusable(true);
        }
        else if (nubanaccountnumber.isEmpty())
        {
            nuban_account_number.setError("Required");
            nuban_account_number.setFocusable(true);
        }
        else if (bank_name_spinner.getSelectedItemPosition()==0){
            Toast.makeText(this, "Set Bank Name!", Toast.LENGTH_SHORT).show();
        }
        else{
            return true;
        }
        return false;
    }


    public void accountdetails(String billing_type,String vat_liability_status,String vat_number,String holder_name,String Nuban_account_number,String bank_name) {
        RequestAccountDetails requestAccountDetails = new RequestAccountDetails();
        requestAccountDetails.setBilling_type(billing_type);
        requestAccountDetails.setVat_liability_status(vat_liability_status);
        requestAccountDetails.setVat_number(vat_number);
        requestAccountDetails.setHolder_name(holder_name);
        requestAccountDetails.setNuban_account_number(Nuban_account_number);
        requestAccountDetails.setBank_name(bank_name);



        Call<ResponseAccountDetails> responseDocumentUploadCall = ApiClass.getUserServiceAPI().userAddDriverBankingDetails(requestAccountDetails);
        responseDocumentUploadCall.enqueue(new Callback<ResponseAccountDetails>() {
            @Override
            public void onResponse(Call<ResponseAccountDetails> call, Response<ResponseAccountDetails> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Success")) {
                        simpleProgressBar.setVisibility(View.GONE);
                        startActivity(new Intent(PaymentMethodScreenFirst.this, SetupGPSLocationActivity.class));
                        finish();
                    }
                    else{
                        finish_btn.setEnabled(true);
                        simpleProgressBar.setVisibility(View.GONE);
                        Toast.makeText(PaymentMethodScreenFirst.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseAccountDetails> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }

    public void agree_check(View view) {
        if (vat_liability_check.isChecked())
        {
            check_agree="1";
        }
        else{
            check_agree="0";
        }
    }
}