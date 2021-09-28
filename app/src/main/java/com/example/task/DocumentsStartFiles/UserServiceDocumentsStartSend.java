package com.example.task.DocumentsStartFiles;

import com.example.task.FilesLogin.RequestLogin;
import com.example.task.FilesLogin.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceDocumentsStartSend {

    @POST("add_driver_all_documents")
    Call<ResponseDocumentStartSend> userLogin(@Body RequestDocumentStartSend requestDocumentStartSend);
}
