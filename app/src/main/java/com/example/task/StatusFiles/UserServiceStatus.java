package com.example.task.StatusFiles;

import com.example.task.FilesSignUp.RequestSignUp;
import com.example.task.FilesSignUp.ResponseSignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceStatus {

    @POST("driver-status")
    Call<ResponseStatus> userLogin(@Body RequestStatus requestStatus);
}
