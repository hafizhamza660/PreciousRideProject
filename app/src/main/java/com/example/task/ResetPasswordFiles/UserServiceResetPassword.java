package com.example.task.ResetPasswordFiles;

import com.example.task.PhoneVerificationFiles.RequestPhoneVerfication;
import com.example.task.PhoneVerificationFiles.ResponsePhoneVerification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceResetPassword {

    @POST("reset_pw")
    Call<ResponseResetPassword> userLogin(@Body RequestResetPassword requestResetPassword);
}
