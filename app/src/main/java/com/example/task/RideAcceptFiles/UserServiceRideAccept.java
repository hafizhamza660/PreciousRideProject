package com.example.task.RideAcceptFiles;

import com.example.task.FilesLogin.RequestLogin;
import com.example.task.FilesLogin.ResponseLogin;
import com.example.task.RideRequestFiles.RideRequestResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRideAccept {

    @POST("driver-accept-ride")
    Call<ResponseRideAccept> userLogin(@Body RequestRideAccept requestRideAccept);
}
