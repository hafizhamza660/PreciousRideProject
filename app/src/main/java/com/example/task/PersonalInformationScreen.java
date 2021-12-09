package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;
import static com.example.task.Session.SaveSharedPreference.setFirstName;
import static com.example.task.Session.SaveSharedPreference.setLastName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.PersonalInformationFiles.RequestPersonalInformation;
import com.example.task.PersonalInformationFiles.ResponsePersonalInformation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalInformationScreen extends AppCompatActivity {
    EditText first_name,last_name,invite_code,license_plate;
    Button next_btn;
    Spinner vehicle_manufacture,vehicle_model,vehicle_color;
    TextView vehicle_year;
    String[] arry1,arry2,arry3,arry4;
    CheckBox i_have_vehicle_check;
    LinearLayout vehicle_info_layout;
    Context context;
    String countrycode;
    String f_name,l_name,v_year,v_manu,v_color,v_model,check,v_plate,ref_code;
    Calendar myCalendar;
    ProgressBar simpleProgressBar;
    ConstraintLayout rootContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_screen);
        context=this;

        Intent intent= getIntent();
        countrycode= intent.getStringExtra("countrycode");
//        rootContainer=findViewById(R.id.rootContainer);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        vehicle_manufacture=findViewById(R.id.vehicle_manufacture);
        vehicle_model=findViewById(R.id.vehicle_model);
        vehicle_year=findViewById(R.id.vehicle_year);
        vehicle_color=findViewById(R.id.vehicle_color);
        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        invite_code=findViewById(R.id.invite_code);
        license_plate=findViewById(R.id.license_plate);
        next_btn=findViewById(R.id.next_btn);
        i_have_vehicle_check=findViewById(R.id.i_have_vehicle_check);
        vehicle_info_layout=findViewById(R.id.vehicle_info_layout);


        arry1=getResources().getStringArray(R.array.vehicle_manu_arrays);
        arry2=getResources().getStringArray(R.array.vehicle_model_arrays);

        arry4=getResources().getStringArray(R.array.vehicle_color);

        spinnerArrayset(vehicle_manufacture,arry1);
        spinnerArrayset(vehicle_model,arry2);
        spinnerArrayset(vehicle_color,arry4);

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        vehicle_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PersonalInformationScreen.this, date ,myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 f_name = first_name.getText().toString();
//                 l_name = last_name.getText().toString();
//                 v_year = vehicle_year.getText().toString();
//                v_plate = license_plate.getText().toString();
//                ref_code = invite_code.getText().toString();
//                if (i_have_vehicle_check.isChecked())
//                {
//                     v_manu = vehicle_manufacture.getSelectedItem().toString();
//                     v_color = vehicle_color.getSelectedItem().toString();
//                     v_model = vehicle_model.getSelectedItem().toString();
//                     check = "1";
//                }
//                else{
//                    check = "0";
//                }
//
//                if (f_name.isEmpty())
//                {
//                    first_name.setError("Fill this field");
//                    first_name.setFocusable(true);
//                }
//                else if (l_name.isEmpty())
//                {
//                    last_name.setError("Fill this field");
//                    last_name.setFocusable(true);
//                }
////                else if (ref_code.isEmpty())
////                {
////                    invite_code.setError("Fill this field");
////                    invite_code.setFocusable(true);
////                }
////                else if (f_name.isEmpty())
////                {
////                    first_name.setError("Fill this field");
////                    first_name.setFocusable(true);
////                }
//                else{
//                    next_btn.setEnabled(false);
////                    rootContainer.setEnabled(false);
//                    simpleProgressBar.setVisibility(View.VISIBLE);
//                    personalInfoAdd(f_name,l_name,check,v_manu,v_model,v_year,v_plate,ref_code);

                Intent intent= new Intent(PersonalInformationScreen.this,DrivingLicenceScreen.class);
                    startActivity(intent);
                    finish();
//                }

            }
        });




    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        vehicle_year.setText(sdf.format(myCalendar.getTime()));
    }

    void spinnerArrayset(Spinner spinner,String[] arry){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arry);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void agree_check(View view) {
        if (i_have_vehicle_check.isChecked())
        {
            vehicle_info_layout.setVisibility(View.VISIBLE);
        }
        else
        {
            vehicle_info_layout.setVisibility(View.GONE);
        }
    }



    public void personalInfoAdd(String first_name,String last_name,String checkvalue,String vehicle_manu,String vehicle_model,String vehicle_year,String vehicle_plate,String referral_code) {
        RequestPersonalInformation requestPersonalInformation = new RequestPersonalInformation();
        requestPersonalInformation.setDriver_id(getClientId(context));
        requestPersonalInformation.setF_name(first_name);
        requestPersonalInformation.setL_name(last_name);
        requestPersonalInformation.setCheck(checkvalue);
        requestPersonalInformation.setVehicle_manufacturer(vehicle_manu);
        requestPersonalInformation.setVehicle_model(vehicle_model);
        requestPersonalInformation.setVehicle_year(vehicle_year);
        requestPersonalInformation.setVehicle_plate(vehicle_plate);
        requestPersonalInformation.setReferral_code(referral_code);


        Call<ResponsePersonalInformation> responsePersonalInformationCall = ApiClass.getUserServiceAPI().userAddDriverDetails(requestPersonalInformation);
        responsePersonalInformationCall.enqueue(new Callback<ResponsePersonalInformation>() {
            @Override
            public void onResponse(Call<ResponsePersonalInformation> call, Response<ResponsePersonalInformation> response) {
                if (response.isSuccessful()) {

                    if (response.body().message.equals("Success"))
                    {
                        next_btn.setEnabled(true);
                        String firstName = first_name;
                        String lastname = last_name;



                        setFirstName(context, firstName);
                        setLastName(context, lastname);

                        simpleProgressBar.setVisibility(View.GONE);
                        Intent intent= new Intent(PersonalInformationScreen.this,DrivingLicenceScreen.class);
                        intent.putExtra("countrycode", countrycode);
                        startActivity(intent);
                    }
                    else {
                        next_btn.setEnabled(true);
                        simpleProgressBar.setVisibility(View.GONE);
                        Toast.makeText(PersonalInformationScreen.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponsePersonalInformation> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(PersonalInformationScreen.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2021, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            vehicle_year.setText(datePicker.getYear());
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }


}