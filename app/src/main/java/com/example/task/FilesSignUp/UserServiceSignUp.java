package com.example.task.FilesSignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceSignUp {

    @POST("driver-sign_up")
    Call<ResponseSignUp> userLogin(@Body RequestSignUp requestSignUp);
}
