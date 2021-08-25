package com.example.task.AllNotificiationFiles;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceAllNotification {

    @POST("get-driver-all-notifications")
    Call<AllNotificationResponse> userLogin(@Body AllNotificationRequest allNotificationRequest);
}
