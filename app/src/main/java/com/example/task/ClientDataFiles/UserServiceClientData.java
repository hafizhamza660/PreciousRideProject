package com.example.task.ClientDataFiles;

import com.example.task.ForgetPasswordFiles.RequestForgetPassword;
import com.example.task.ForgetPasswordFiles.ResponseForgetPassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceClientData {

    @POST("client_details_ride")
    Call<ResponseClientData> userLogin(@Body RequestClientData requestClientData);
}
