package com.example.task.AllDocumentFiles;

import com.example.task.DocumentUploadFiles.RequestDocument;
import com.example.task.DocumentUploadFiles.ResponseDocumentUpload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceAllDocument {

    @POST("get-all-documents")
    Call<ResponseAllDocument> userLogin(@Body RequestAllDocument requestAllDocument);
}
