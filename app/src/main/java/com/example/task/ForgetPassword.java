package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.ForgetPasswordFiles.RequestForgetPassword;
import com.example.task.ForgetPasswordFiles.ResponseForgetPassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {
    EditText email_edt;
    Button submit_btn;
    String emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email_edt=findViewById(R.id.email_edt);
        submit_btn=findViewById(R.id.submit_btn);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emails= email_edt.getText().toString();
                if (emails.equals(null))
                {
                    email_edt.setError("Enter Email!");
                    email_edt.setFocusable(true);

                }
                else{
                    forgetpassword(emails);
                }
            }
        });

    }

    public void back_btn(View view) {
        onBackPressed();
    }

    public void forgetpassword(String email) {
        RequestForgetPassword requestForgetPassword = new RequestForgetPassword();
        requestForgetPassword.setEmail(email);

//        signUpRequest.setI_code(invite_code);


        Call<ResponseForgetPassword> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverForgetPWOtp(requestForgetPassword);
        signUpResponseCall.enqueue(new Callback<ResponseForgetPassword>() {
            @Override
            public void onResponse(Call<ResponseForgetPassword> call, Response<ResponseForgetPassword> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ForgetPassword.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("OTP sent"))
                    {

                        Intent intent = new Intent(ForgetPassword.this, PhoneVerification.class);
                        intent.putExtra("number",response.body().data);
                        startActivity(intent);
                        finish();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseForgetPassword> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}