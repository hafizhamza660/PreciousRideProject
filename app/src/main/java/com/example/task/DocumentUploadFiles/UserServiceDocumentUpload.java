package com.example.task.DocumentUploadFiles;

import com.example.task.FilesLogin.RequestLogin;
import com.example.task.FilesLogin.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceDocumentUpload {

    @POST("add-cards")
    Call<ResponseDocumentUpload> userLogin(@Body RequestDocument requestDocument);
}
