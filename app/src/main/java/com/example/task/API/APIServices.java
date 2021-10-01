package com.example.task.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServices {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA_Y9Fufs:APA91bFByP1f3xOYz-XbMtxJv6JYFM-F-SpJvCowhwXySdQZyiMC5EDOIDaPMDFqkr6gNsKaOVxJzSy0dmbg_ti9WYYWiFx3eh13nDhbfyiRrHvKX9ibxuQcpAwIkymnZopPaeaUY0Sx"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
