package com.example.task.Messages.MessageWithoutChatId;

import com.example.task.Messages.MessageWithChatId.MessagesResponse;
import com.example.task.NotificationFiles.NotificationRequest;
import com.example.task.NotificationFiles.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceMessagesWithoutChatId {

    @POST("get-chats")
    Call<MessagesResponse> userLogin(@Body MessageWithoutChatidRequest messageWithoutChatidRequest);
}
