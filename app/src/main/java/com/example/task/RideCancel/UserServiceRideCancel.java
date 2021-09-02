package com.example.task.RideCancel;

import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;
import com.example.task.RideRequestedHistory.RideRequestHistoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRideCancel {

    @POST("driver-cancel-ride")
    Call<RideCancelResponse> userLogin(@Body RideCancelRequest rideCancelRequest);
}
