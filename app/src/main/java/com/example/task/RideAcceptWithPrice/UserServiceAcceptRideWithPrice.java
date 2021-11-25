package com.example.task.RideAcceptWithPrice;

import com.example.task.NotificationFiles.NotificationRequest;
import com.example.task.NotificationFiles.NotificationResponse;
import com.example.task.RideRequestedHistory.RideRequestHistoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceAcceptRideWithPrice {

    @POST("add_driver_added_price")
    Call<AcceptRideWithPriceResponse> userLogin(@Body AcceptRideWithPriceRequest acceptRideWithPriceRequest);
}
