package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getChatId;
import static com.example.task.Session.SaveSharedPreference.setChatId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.task.API.ApiClass;
import com.example.task.Messages.MessageWithChatId.MessageWithChatidRequest;
import com.example.task.Messages.MessageWithChatId.MessagesResponse;
import com.example.task.Messages.MessageWithoutChatId.MessageWithoutChatidRequest;
import com.example.task.RideAcceptFiles.RequestRideAccept;
import com.example.task.RideAcceptFiles.ResponseRideAccept;
import com.example.task.adapters.MessageAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message extends AppCompatActivity {
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    EditText message;
    ImageButton button_gchat_send;
    String driver_id,client_id;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        context=this;

        message = findViewById(R.id.message);
        button_gchat_send = findViewById(R.id.button_gchat_send);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        Intent intent = getIntent();
        driver_id=intent.getStringExtra("driver_id");
        client_id=intent.getStringExtra("client_id");



        button_gchat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatid=getChatId(context);
//                Toast.makeText(Message.this, ""+chatid, Toast.LENGTH_SHORT).show();
                Log.d("DataChatId",""+chatid);
                if (chatid.equals("")) {
//                    Toast.makeText(Message.this, "Null", Toast.LENGTH_SHORT).show();
                    messageSend(driver_id, client_id, message.getText().toString());
                }
                else{
//                    Toast.makeText(Message.this, "Not nUll", Toast.LENGTH_SHORT).show();
                    messageSendWithChatId(driver_id, client_id, message.getText().toString(),chatid);
                }
            }
        });
    }

    public void messageSend(String driver_id,String client_id,String text) {
        MessageWithoutChatidRequest messageWithoutChatidRequest = new MessageWithoutChatidRequest();
        messageWithoutChatidRequest.setDriver_id(driver_id);
        messageWithoutChatidRequest.setClient_id(client_id);
        messageWithoutChatidRequest.setText(text);
        messageWithoutChatidRequest.setType("driver");



        Call<MessagesResponse> messagesResponseCall = ApiClass.getUserServiceMessagesWithoutChatId().userLogin(messageWithoutChatidRequest);
        messagesResponseCall.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(context, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    String chatid= response.body().data.get(0).chat_id;
                    setChatId(context,chatid);
                    messageAdapter = new MessageAdapter(Message.this, response.body().data);
                    recyclerView.setAdapter(messageAdapter);

                } else {

                }
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
//                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }

    public void messageSendWithChatId(String driver_id,String client_id,String text,String chat_id) {
        MessageWithChatidRequest messageWithChatidRequest = new MessageWithChatidRequest();
        messageWithChatidRequest.setDriver_id(driver_id);
        messageWithChatidRequest.setChat_id(chat_id);
        messageWithChatidRequest.setClient_id(client_id);
        messageWithChatidRequest.setText(text);
        messageWithChatidRequest.setType("driver");


        Call<MessagesResponse> messagesResponseCall = ApiClass.getUserServiceMessagesWithChatId().userLogin(messageWithChatidRequest);
        messagesResponseCall.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                if (response.isSuccessful()) {
                    messageAdapter = new MessageAdapter(Message.this, response.body().data);
                    recyclerView.setAdapter(messageAdapter);

                } else {

                }
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
//                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


}