package com.example.task.RideRequestedHistory;

import com.example.task.RideRequestFiles.RideRequestResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserServiceRideHistoryRequest {

    @POST("getDriverRequestedRideRequests")
    Call<RideRequestHistoryResponse> userLogin();
}
