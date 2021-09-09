package com.example.task.PhoneVerificationFiles;

import com.example.task.ForgetPasswordFiles.RequestForgetPassword;
import com.example.task.ForgetPasswordFiles.ResponseForgetPassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServicePhoneVerification {

    @POST("confirm_otp")
    Call<ResponsePhoneVerification> userLogin(@Body RequestPhoneVerfication requestPhoneVerfication);
}
