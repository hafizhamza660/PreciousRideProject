package com.example.task.InterCityRequest;

import com.example.task.RideRequestFiles.RideRequestResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserServiceInterCityRideRequest {

    @POST("intercity-ride-history")
    Call<InterCityRideRequestResponse> userLogin();
}
