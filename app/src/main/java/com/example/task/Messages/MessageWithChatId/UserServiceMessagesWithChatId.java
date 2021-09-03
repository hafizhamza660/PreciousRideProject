package com.example.task.Messages.MessageWithChatId;

import com.example.task.NotificationFiles.NotificationRequest;
import com.example.task.NotificationFiles.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceMessagesWithChatId {

    @POST("get-all-chats")
    Call<MessagesResponse> userLogin(@Body MessageWithChatidRequest messageWithChatidRequest);
}
