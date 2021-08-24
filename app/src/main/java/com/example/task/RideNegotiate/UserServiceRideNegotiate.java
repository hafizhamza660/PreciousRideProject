package com.example.task.RideNegotiate;

import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRideNegotiate {

    @POST("driver-negotiate-ride")
    Call<ResponseRideNegotiate> userLogin(@Body RequestRideNegotiate requestRideNegotiate);
}
