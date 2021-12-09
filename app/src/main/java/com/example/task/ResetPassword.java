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
import com.example.task.ResetPasswordFiles.RequestResetPassword;
import com.example.task.ResetPasswordFiles.ResponseResetPassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity {
    EditText password,confirm_password;
    Button submit_btn;
    String s_password,s_confirm_password,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        password= findViewById(R.id.password);
        confirm_password= findViewById(R.id.confirm_password);
        submit_btn= findViewById(R.id.submit_btn);


        Intent intent=getIntent();

        number= intent.getStringExtra("number");

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_password= password.getText().toString();
                s_confirm_password= confirm_password.getText().toString();

                if (s_password.equals(null))
                {
                    password.setError("Enter New Password!");
                    password.setFocusable(true);
                }
                else if (s_confirm_password.equals(null))
                {
                    confirm_password.setError("Enter New Password!");
                    confirm_password.setFocusable(true);
                }

                else if (!(s_password.equals(s_confirm_password)))
                {
                    confirm_password.setError("Password Does Not Match");
                    confirm_password.setFocusable(true);
                }
                else
                {
                    resetpassword(s_password,number);
                }
            }
        });



    }

    public void resetpassword(String s_password,String number) {
        RequestResetPassword requestResetPassword = new RequestResetPassword();
        requestResetPassword.setPassword(s_password);
        requestResetPassword.setNumber(number);

//        signUpRequest.setI_code(invite_code);


        Call<ResponseResetPassword> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverResetPw(requestResetPassword);
        signUpResponseCall.enqueue(new Callback<ResponseResetPassword>() {
            @Override
            public void onResponse(Call<ResponseResetPassword> call, Response<ResponseResetPassword> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(PhoneVerification.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("Password reset successfully"))
                    {

                        Intent intent = new Intent(ResetPassword.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseResetPassword> call, Throwable t) {
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(ResetPassword.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

}