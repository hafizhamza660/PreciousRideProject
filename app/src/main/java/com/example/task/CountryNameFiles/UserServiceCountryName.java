package com.example.task.CountryNameFiles;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserServiceCountryName {

    @POST("all-country-codes")
    Call<CountryNameResponse> userLogin();
}
