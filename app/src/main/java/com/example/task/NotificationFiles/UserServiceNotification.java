package com.example.task.NotificationFiles;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceNotification {

    @POST("get-driver-notifications")
    Call<NotificationResponse> userLogin(@Body NotificationRequest notificationRequest);
}
