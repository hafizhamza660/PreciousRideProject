package com.example.task.ForgetPasswordFiles;

import com.example.task.LoginValues.RequestLoginValues;
import com.example.task.LoginValues.ResponseLoginValues;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceForgetPassword {

    @POST("forgot_pw_otp")
    Call<ResponseForgetPassword> userLogin(@Body RequestForgetPassword requestForgetPassword);
}
