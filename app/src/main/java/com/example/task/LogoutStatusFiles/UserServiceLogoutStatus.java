package com.example.task.LogoutStatusFiles;

import com.example.task.StatusFiles.RequestStatus;
import com.example.task.StatusFiles.ResponseStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceLogoutStatus {

    @POST("driver-logout-status")
    Call<ResponseLogoutStatus> userLogin(@Body RequestLogoutStatus requestLogoutStatus);
}
