package com.example.task.LoginValues;

import com.example.task.LogoutStatusFiles.RequestLogoutStatus;
import com.example.task.LogoutStatusFiles.ResponseLogoutStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceLoginValues {

    @POST("driver-login_status")
    Call<ResponseLoginValues> userLogin(@Body RequestLoginValues requestLoginValues);
}
