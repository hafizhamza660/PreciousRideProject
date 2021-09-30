package com.example.task.AccountDetailsFiles;

import com.example.task.DocumentsStartFiles.RequestDocumentStartSend;
import com.example.task.DocumentsStartFiles.ResponseDocumentStartSend;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceAccountDetails {

    @POST("add-driver-banking-details")
    Call<ResponseAccountDetails> userLogin(@Body RequestAccountDetails requestAccountDetails);
}
