package com.example.task.DataSendFiles;

import com.example.task.LoginValues.RequestLoginValues;
import com.example.task.LoginValues.ResponseLoginValues;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceDataSend {

    @POST("api-object")
    Call<ResponseDataSend> userLogin(@Body RequestDataSend requestDataSend);
}
