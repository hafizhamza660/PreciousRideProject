package com.example.task.InterCityRequestAccept;

import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRideAcceptInterCity {

    @POST("intercity-driver-accepts")
    Call<ResponseRideAcceptInterCity> userLogin(@Body RequestRideAcceptInterCity requestRideAcceptInterCity);
}
