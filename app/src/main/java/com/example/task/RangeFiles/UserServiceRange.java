package com.example.task.RangeFiles;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRange {

    @POST("range-update")
    Call<ResponseRange> userLogin(@Body RequestRange requestRange);
}
