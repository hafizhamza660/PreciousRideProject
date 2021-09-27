package com.example.task.DocumentDataFiles;

import com.example.task.DocumentUploadFiles.RequestDocument;
import com.example.task.DocumentUploadFiles.ResponseDocumentUpload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceDocumentCountrywise {

    @POST("get_documents")
    Call<ResponseDocumentCountrywise> userLogin(@Body RequestDocumentCountrywise requestDocumentCountrywise);
}
