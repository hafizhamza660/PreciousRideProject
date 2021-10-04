package com.example.task.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServices {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAz16p0T8:APA91bGhmkXNjxTkj2J1UUTCDZgUTcj2Z5WlvSsYg_kGDhsTN07g_yq_5NMn4m2oCiZM8mWQi6t-C4rZ-W7Q-Jcv3BvfOS-fafSWvl4778pMceLvJhHprDs7NKJVphVI-A9JmDw68_Mg"
            }
    )
    @POST("fcm/send")
    Call<ResponseBody> sendNotification(@Body RootModel root);
//    Call<MyResponse> sendNotification(@Body Sender body);
}
