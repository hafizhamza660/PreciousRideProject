package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.PhoneVerificationFiles.RequestPhoneVerfication;
import com.example.task.PhoneVerificationFiles.ResponsePhoneVerification;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneVerification extends AppCompatActivity {
    String number;
    OtpView otp_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        otp_view= findViewById(R.id.otp_view);

        Intent intent= getIntent();

        number=intent.getStringExtra("number");
    }

    public void verify(View view) {
        String otp = otp_view.getText().toString();
        if (otp.equals(null))
        {
            otp_view.setError("Enter OTP");
            otp_view.setFocusable(true);
        }
        else{
            phoneverify(otp,number);
        }

    }


    public void phoneverify(String otp,String number) {
        RequestPhoneVerfication requestPhoneVerfication = new RequestPhoneVerfication();
        requestPhoneVerfication.setOtp(otp);
        requestPhoneVerfication.setNumber(number);

//        signUpRequest.setI_code(invite_code);


        Call<ResponsePhoneVerification> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverConfirmOtp(requestPhoneVerfication);
        signUpResponseCall.enqueue(new Callback<ResponsePhoneVerification>() {
            @Override
            public void onResponse(Call<ResponsePhoneVerification> call, Response<ResponsePhoneVerification> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(PhoneVerification.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("Success"))
                    {

                        Intent intent = new Intent(PhoneVerification.this, ResetPassword.class);
                        intent.putExtra("number",number);
                        startActivity(intent);
                        finish();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponsePhoneVerification> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}