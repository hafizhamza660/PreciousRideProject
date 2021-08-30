package com.example.task;

import static com.example.task.Session.SaveSharedPreference.clearClientId;
import static com.example.task.Session.SaveSharedPreference.getCity;
import static com.example.task.Session.SaveSharedPreference.getClientId;
import static com.example.task.Session.SaveSharedPreference.getEmail;
import static com.example.task.Session.SaveSharedPreference.getFirstName;
import static com.example.task.Session.SaveSharedPreference.getLastName;
import static com.example.task.Session.SaveSharedPreference.getMobileNumber;
import static com.example.task.Session.SaveSharedPreference.setCity;
import static com.example.task.Session.SaveSharedPreference.setClientId;
import static com.example.task.Session.SaveSharedPreference.setEmail;
import static com.example.task.Session.SaveSharedPreference.setFirstName;
import static com.example.task.Session.SaveSharedPreference.setLastName;
import static com.example.task.Session.SaveSharedPreference.setMobileNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.API.ApiClass;
import com.example.task.FilesSignUp.RequestSignUp;
import com.example.task.FilesSignUp.ResponseSignUp;
import com.example.task.UpdateFiles.RequestUpdate;
import com.example.task.UpdateFiles.ResponseUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInformation extends AppCompatActivity {
    EditText firstname,lastname;
    TextView number,email;
    Context context;
    String sfirstname,slastname;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        context=this;


        firstname= (EditText) findViewById(R.id.first_name_edt);
        lastname= (EditText) findViewById(R.id.last_name_edt);
        number= findViewById(R.id.number);
        email= findViewById(R.id.email);
        done= findViewById(R.id.done);
        sfirstname = getFirstName(context);
        slastname = getLastName(context);

        firstname.setText(sfirstname);
        lastname.setText(slastname);
        number.setText(getMobileNumber(context));
        email.setText(getEmail(context));


        sfirstname = firstname.getText().toString();
        slastname = lastname.getText().toString();


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateinfo(firstname.getText().toString(),lastname.getText().toString());
//                Toast.makeText(EditInformation.this, ""+firstname.getText().toString()+"\n"+lastname.getText().toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(EditInformation.this, ""+sfirstname+"\n"+slastname, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void back_button(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void updateinfo(String first_name,String last_name) {
//        Toast.makeText(this, ""+first_name+"\n"+last_name, Toast.LENGTH_SHORT).show();
        RequestUpdate requestUpdate = new RequestUpdate();
        requestUpdate.setId(getClientId(context));
        requestUpdate.setF_name(first_name);
        requestUpdate.setL_name(last_name);



        Call<ResponseUpdate> signUpResponseCall = ApiClass.getUserServiceUpdate().userLogin(requestUpdate);
        signUpResponseCall.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditInformation.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("Successfully Updated"))
                    {
                        String idClient = response.body().data.id;
                        String firstName = response.body().data.f_name;
                        String lastname = response.body().data.l_name;
                        String city = response.body().data.city;
                        String email = response.body().data.email;
                        String number = response.body().data.mobile_number;
                        clearClientId(context);

                        setClientId(context,idClient);
                        setFirstName(context,firstName);
                        setLastName(context,lastname);
                        setMobileNumber(context,number);
                        setEmail(context,email);
                        setCity(context,city);

//                        Toast.makeText(EditInformation.this, ""+"\n"+getFirstName(context)+"\n"+getCity(context)+"\n"+getLastName(context)+"\n"+getEmail(context)+"\n"+getClientId(context)+"\n"+getMobileNumber(context), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(EditInformation.this, "Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
//                Toast.makeText(EditInformation.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}