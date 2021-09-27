package com.example.task.PersonalInformationFiles;

import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServicePersonalInformation {

    @POST("add_driver_details")
    Call<ResponsePersonalInformation> userLogin(@Body RequestPersonalInformation requestPersonalInformation);
}
