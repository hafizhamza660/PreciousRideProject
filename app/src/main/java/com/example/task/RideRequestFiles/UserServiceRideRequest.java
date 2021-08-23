package com.example.task.RideRequestFiles;

import com.example.task.StatusFiles.RequestStatus;
import com.example.task.StatusFiles.ResponseStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRideRequest {

    @POST("getDriverRideRequests")
    Call<RideRequestResponse> userLogin();
}
