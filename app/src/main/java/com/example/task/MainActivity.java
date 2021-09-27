package com.example.task;

import static com.example.task.Session.SaveSharedPreference.setCity;
import static com.example.task.Session.SaveSharedPreference.setClientId;
import static com.example.task.Session.SaveSharedPreference.setEmail;
import static com.example.task.Session.SaveSharedPreference.setFirstName;
import static com.example.task.Session.SaveSharedPreference.setLastName;
import static com.example.task.Session.SaveSharedPreference.setMobileNumber;
import static com.example.task.Session.SaveSharedPreference.setStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.API.ApiClass;
import com.example.task.FilesLogin.RequestLogin;
import com.example.task.FilesLogin.ResponseLogin;
import com.example.task.LoginValues.RequestLoginValues;
import com.example.task.LoginValues.ResponseLoginValues;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView login_txt;
    EditText email, password;
    Context context;
    String countrycode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Intent intent= getIntent();
        countrycode= intent.getStringExtra("countrycode");
        login_txt = findViewById(R.id.login_txt);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login_txt.setText(getText(R.string.login_text));


    }

    public void sign_in(View view) {
//        Intent intent = new Intent(MainActivity.this,HomeOffline.class);
//        startActivity(intent);

        String semail, spassword;
        semail = email.getText().toString();
        spassword = password.getText().toString();
        if (semail.isEmpty()) {
            email.setError("Required");
        } else if (spassword.isEmpty()) {
            password.setError("Required");
        } else {

            login(semail, spassword);
        }
    }

//    public void clear(View view) {
//        number.setText("");
//    }


    public void login(String email, String password) {
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setEmail(email);
        requestLogin.setPassword(password);

//        signUpRequest.setI_code(invite_code);


        Call<ResponseLogin> signUpResponseCall = ApiClass.getUserServiceLogin().userLogin(requestLogin);
        signUpResponseCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("Login Successfully")) {
                        String idClient = response.body().data.id;
                        setClientId(context, idClient);
                        if (response.body().data.f_name != null) {


                            String firstName = response.body().data.f_name;
                            String lastname = response.body().data.l_name;
                            String city = response.body().data.city;
                            String email = response.body().data.email;
                            String number = response.body().data.mobile_number;


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
                            Intent intent = new Intent(MainActivity.this, SetupGPSLocationActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(MainActivity.this, PersonalInformationScreen.class);
                            intent.putExtra("countrycode", countrycode);
                            startActivity(intent);
                            finish();
                        }
                    }

                } else {
//                    Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


    public void loginvalues(String driverId, String mobile, String logged_in) {
        RequestLoginValues requestLoginValues = new RequestLoginValues();
        requestLoginValues.setDriver_id(driverId);
        requestLoginValues.setMobile(mobile);
        requestLoginValues.setLogged_in(logged_in);

//        signUpRequest.setI_code(invite_code);


        Call<ResponseLoginValues> responseLoginValuesCall = ApiClass.getUserServiceLoginValues().userLogin(requestLoginValues);
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
            }
        });
    }

    public void forget_password(View view) {
        startActivity(new Intent(MainActivity.this, ForgetPassword.class));
    }

    public void back_button(View view) {
        onBackPressed();
    }
}