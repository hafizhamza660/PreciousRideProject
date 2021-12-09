package com.example.task;

import static com.example.task.Session.SaveSharedPreference.setCity;
import static com.example.task.Session.SaveSharedPreference.setClientId;
import static com.example.task.Session.SaveSharedPreference.setEmail;
import static com.example.task.Session.SaveSharedPreference.setFirstName;
import static com.example.task.Session.SaveSharedPreference.setLastName;
import static com.example.task.Session.SaveSharedPreference.setMobileNumber;
import static com.example.task.Session.SaveSharedPreference.setStatus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.CountryNameFiles.CountryNameResponse;
import com.example.task.FilesSignUp.RequestSignUp;
import com.example.task.FilesSignUp.ResponseSignUp;
import com.example.task.LoginValues.RequestLoginValues;
import com.example.task.LoginValues.ResponseLoginValues;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    TextView city_name;
    String name;
    Context context;
    EditText first_name, last_name, email, number, password, invite_code, confim_password;
    String sfirst_name, slast_name, semail, snumber, spassword, scity, sinvite_code, sconfim_password;
    private List<Place.Field> fields;
    final int place_picker_req_code = 1;
    CountryCodePicker ccp;
    CheckBox terms_and_conditions;
    String countrycode_s;
    Spinner countrycode;
    ConstraintLayout parentLayout;
     ProgressBar simpleProgressBar;
    List<String> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = this;

        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);

        parentLayout = findViewById(R.id.parentLayout);
        // Add ProgressBar to LinearLayout

        first_name = findViewById(R.id.first_name);
        countrycode = findViewById(R.id.countrycode);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        number = findViewById(R.id.number);
        city_name = findViewById(R.id.city_name);
        invite_code = findViewById(R.id.invite_code);
//        ccp = findViewById(R.id.ccp);
        terms_and_conditions = findViewById(R.id.terms_and_conditions);
        confim_password = findViewById(R.id.confim_password);
        countrycodeget();

        fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        Places.initialize(getApplicationContext(), "AIzaSyAd8q-fqcHslANRJ3WZxR5cMYY1CgtBe9I");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case place_picker_req_code:
                Place place;
                if (resultCode == 0) {

                } else {
                    place = Autocomplete.getPlaceFromIntent(data);
                    name = place.getName();
                    city_name.setText(name);
                }
                break;

        }
    }


    public void sign_up(View view) {
//        Intent intent = new Intent(SignUp.this,HomeOffline.class);
//        startActivity(intent);
//        dataget();

        Intent intent = new Intent(SignUp.this, PersonalInformationScreen.class);
        startActivity(intent);
        finish();
    }

    public void sign_in(View view) {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
    }

    public void google_sign_in(View view) {
        startNewActivity(context, "com.google.android.googlequicksearchbox");
    }


    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        }
    }

    public void facebook_sign_in(View view) {
        startNewActivity(context, "com.facebook.katana");
    }

    public void worldgrin_sign_in(View view) {
        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {
        number.setText("");
    }

    public void city_get(View view) {
        Intent i = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(SignUp.this);
        startActivityForResult(i, place_picker_req_code);
    }


    public void dataget() {
//        sfirst_name = first_name.getText().toString();
//        slast_name = last_name.getText().toString();
//        semail = email.getText().toString();
//        String numbercheck = number.getText().toString();
//
//        snumber = countrycode.getSelectedItem().toString()+ number.getText().toString();
//        countrycode_s = countrycode.getSelectedItem().toString();
//        setCountryCode(context,countrycode_s);
//        spassword = password.getText().toString();
//        scity = city_name.getText().toString();
////        sinvite_code = invite_code.getText().toString();
//        sconfim_password = confim_password.getText().toString();
//        Log.d("Data", "\n" + sfirst_name + "\n" + slast_name + "\n" + semail + "\n" + snumber + "\n" + spassword + "\n" + scity + "\n" + sinvite_code);
////        if(sfirst_name.isEmpty())
////        {
////            first_name.setError("Fill this field");
////            first_name.requestFocus();
////        }
////        else if(slast_name.isEmpty())
////        {
////            last_name.setError("Fill this field");
////            last_name.requestFocus();
////        }
//        if (semail.isEmpty()) {
//            email.setError("Fill this field");
//            email.requestFocus();
//        } else if (numbercheck.isEmpty()) {
//            number.setError("Enter the valid number");
//            number.requestFocus();
//        } else if (numbercheck.length() > 10) {
//            number.setError("Enter the valid number");
//            number.requestFocus();
//        } else if (spassword.isEmpty()) {
//            password.setError("Fill this field");
//            password.requestFocus();
//        } else if (!(sconfim_password.equals(spassword))) {
//            confim_password.setError("Password does not match");
//            confim_password.requestFocus();
//        } else if (scity.isEmpty()) {
//            city_name.setError("Fill this field");
//            city_name.requestFocus();
//        } else if (!(terms_and_conditions.isChecked())) {
//            terms_and_conditions.setError("Check");
//        } else {
//
//            simpleProgressBar.setVisibility(View.VISIBLE);
//            signupf(sfirst_name, countrycode_s, semail, snumber, spassword, scity, sinvite_code);
//        }

        Intent intent = new Intent(SignUp.this, PersonalInformationScreen.class);
        startActivity(intent);
        finish();
    }


    public void signupf(String first_name, String codecountry, String email, String number, String password, String city, String invite_code) {
        RequestSignUp requestSignUp = new RequestSignUp();
//        requestSignUp.setF_name(first_name);
//        requestSignUp.setL_name(last_name);
        requestSignUp.setCodecountry(codecountry);
        requestSignUp.setEmail(email);
        requestSignUp.setNumber(number);
        requestSignUp.setPassword(password);
        requestSignUp.setCity(city);
//        signUpRequest.setI_code(invite_code);


        Call<ResponseSignUp> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverSignUp(requestSignUp);
        signUpResponseCall.enqueue(new Callback<ResponseSignUp>() {
            @Override
            public void onResponse(Call<ResponseSignUp> call, Response<ResponseSignUp> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(SignUp.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("Driver Successfully Stored")) {
                        simpleProgressBar.setVisibility(View.GONE);
                            String idClient = response.body().data.id;
                            setClientId(context, idClient);
                            if (response.body().data.f_name != null) {


                                String firstName = response.body().data.f_name;
                                String lastname = response.body().data.l_name;
                                String city = response.body().data.city;
                                String email = response.body().data.email;
                                String number = response.body().data.mobile_number;
//                                String imageurl = response.body().data.


                                setFirstName(context, firstName);
                                setLastName(context, lastname);
                                setMobileNumber(context, number);
                                setEmail(context, email);
                                setCity(context, city);
                                setStatus(context, "0");


                                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
                                Date currentLocalTime = cal.getTime();
                                DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
                                date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

                                String localTime = date.format(currentLocalTime);


                                loginvalues(idClient, number, localTime);

//                        setClientId(context,idClient,firstName,lastname,email,number,password,city);
//                        Toast.makeText(MainActivity.this, ""+response.body().data.id, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUp.this, SetupGPSLocationActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else {
                                Intent intent = new Intent(SignUp.this, PersonalInformationScreen.class);
                                intent.putExtra("countrycode", countrycode_s);
                                startActivity(intent);
                                finish();
                            }

                    }
                    else{
                        simpleProgressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this, "" + response.body().message, Toast.LENGTH_LONG).show();
                    }

//                    Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), PhoneVerification.class);
//                    startActivity(intent);
                } else {
//                    Toast.makeText(SignUp.this, "API not Hit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSignUp> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(SignUp.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void loginvalues(String driverId, String mobile, String logged_in) {
        RequestLoginValues requestLoginValues = new RequestLoginValues();
        requestLoginValues.setDriver_id(driverId);
        requestLoginValues.setMobile(mobile);
        requestLoginValues.setLogged_in(logged_in);

//        signUpRequest.setI_code(invite_code);


        Call<ResponseLoginValues> responseLoginValuesCall = ApiClass.getUserServiceAPI().userDriverLoginStatus(requestLoginValues);
        responseLoginValuesCall.enqueue(new Callback<ResponseLoginValues>() {
            @Override
            public void onResponse(Call<ResponseLoginValues> call, Response<ResponseLoginValues> response) {
                if (response.isSuccessful()) {


                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseLoginValues> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(SignUp.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back_button(View view) {
        onBackPressed();
    }

    public void countrycodeget() {


        Call<CountryNameResponse> countryNameResponseCall = ApiClass.getUserServiceAPI().userAllCountryCodes();
        countryNameResponseCall.enqueue(new Callback<CountryNameResponse>() {
            @Override
            public void onResponse(Call<CountryNameResponse> call, Response<CountryNameResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getActivity(), ""+response.body().message, Toast.LENGTH_SHORT).show();
//                    code = response.body().data.number;
                    categories = new ArrayList<String>();
                    for(int i=0;i<response.body().data.size();i++) {

                        categories.add(response.body().data.get(i).number);
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_spinner_item, categories);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                    countrycode.setAdapter(dataAdapter);
                } else {
//                    Toast.makeText(getActivity(), "Login Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountryNameResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(SignUp.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}