package com.example.task.UpdateFiles;

import com.example.task.FilesSignUp.RequestSignUp;
import com.example.task.FilesSignUp.ResponseSignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceUpdate {

    @POST("driver-profile-update")
    Call<ResponseUpdate> userLogin(@Body RequestUpdate requestUpdate);
}
