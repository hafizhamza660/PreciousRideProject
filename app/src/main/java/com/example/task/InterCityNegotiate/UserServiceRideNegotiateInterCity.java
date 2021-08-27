package com.example.task.InterCityNegotiate;

import com.example.task.RideNegotiate.RequestRideNegotiate;
import com.example.task.RideNegotiate.ResponseRideNegotiate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRideNegotiateInterCity {

    @POST("intercity-driver-negotiate")
    Call<ResponseRideNegotiateInterCity> userLogin(@Body RequestRideNegotiateInterCity requestRideNegotiateInterCity);
}
