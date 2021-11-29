package com.example.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.task.API.APIServices;
import com.example.task.API.Client;
import com.example.task.API.DataModel;
import com.example.task.API.NotificationModel;
import com.example.task.API.RootModel;
import com.example.task.Models.Chat;
import com.example.task.adapters.MessageFirebaseAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class Message extends AppCompatActivity {
    RecyclerView recyclerView;
    MessageFirebaseAdapter messageAdapter;
    EditText message;
    ImageButton button_gchat_send;
    String driver_id, client_id,id_F,messagepostid,token_id;
    Context context;
    APIServices apiServices;
    boolean notify = false;
    DatabaseReference databaseReference;
    ValueEventListener seenlistner;
    List<Chat> mChat;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        context = this;

        message = findViewById(R.id.message);
        button_gchat_send = findViewById(R.id.button_gchat_send);

        apiServices = Client.getClient("http://fcm.googleapis.com/").create(APIServices.class);






        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


//        Intent intent = getIntent();
//        driver_id=intent.getStringExtra("driver_id");
//        client_id=intent.getStringExtra("client_id");

        driver_id = "26";
        client_id = "88";

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                // send it to server

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("token",token);


//                reference.child("Tokens").push().setValue(hashMap);

                DatabaseReference tokenref = FirebaseDatabase.getInstance().getReference();
                tokenref.child("Tokens").child(driver_id).setValue(hashMap);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("drivers").child("id");
        button_gchat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String chatid = getChatId(context);
////                Toast.makeText(Message.this, ""+chatid, Toast.LENGTH_SHORT).show();
//                Log.d("DataChatId", "" + chatid);
//                if (chatid.equals("")) {
////                    Toast.makeText(Message.this, "Null", Toast.LENGTH_SHORT).show();
//                    messageSend(driver_id, client_id, message.getText().toString());
//                } else {
////                    Toast.makeText(Message.this, "Not nUll", Toast.LENGTH_SHORT).show();
//                    messageSendWithChatId(driver_id, client_id, message.getText().toString(), chatid);
//                }

                notify = true;
                String msg = message.getText().toString();
                if (!msg.equals(""))
                {
                    sendMessage(driver_id,client_id,msg);

                }
                else{
                    Toast.makeText(Message.this, "You Can't Send Empty Message...", Toast.LENGTH_SHORT).show();
                }
                message.setText("");



            }
        });






        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Hirer hirer = snapshot.getValue(Hirer.class);
//                username.setText(hirer.getUsername());
//                if (hirer.getImageUrl().equals("default")){
//                    profileImg.setImageResource(R.mipmap.ic_launcher);
//                }
//                else
//                {
//                    Glide.with(getApplicationContext()).load(hirer.getImageUrl()).into(profileImg);
//                }

                readMessage(driver_id,client_id);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        readMessage(driver_id,client_id);

//        seenMessage(driver_id);

        seenMessage(driver_id);
    }

//    public void messageSend(String driver_id, String client_id, String text) {
//        MessageWithoutChatidRequest messageWithoutChatidRequest = new MessageWithoutChatidRequest();
//        messageWithoutChatidRequest.setDriver_id(driver_id);
//        messageWithoutChatidRequest.setClient_id(client_id);
//        messageWithoutChatidRequest.setText(text);
//        messageWithoutChatidRequest.setType("driver");
//
//
//        Call<MessagesResponse> messagesResponseCall = ApiClass.getUserServiceMessagesWithoutChatId().userLogin(messageWithoutChatidRequest);
//        messagesResponseCall.enqueue(new Callback<MessagesResponse>() {
//            @Override
//            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
//                if (response.isSuccessful()) {
////                    Toast.makeText(context, ""+response.body().message, Toast.LENGTH_SHORT).show();
//                    String chatid = response.body().data.get(0).chat_id;
//                    setChatId(context, chatid);
//                    messageAdapter = new MessageAdapter(Message.this, response.body().data);
//                    recyclerView.setAdapter(messageAdapter);
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MessagesResponse> call, Throwable t) {
////                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "Error " + t);
//            }
//        });
//    }

//    public void messageSendWithChatId(String driver_id, String client_id, String text, String chat_id) {
//        MessageWithChatidRequest messageWithChatidRequest = new MessageWithChatidRequest();
//        messageWithChatidRequest.setDriver_id(driver_id);
//        messageWithChatidRequest.setChat_id(chat_id);
//        messageWithChatidRequest.setClient_id(client_id);
//        messageWithChatidRequest.setText(text);
//        messageWithChatidRequest.setType("driver");
//
//
//        Call<MessagesResponse> messagesResponseCall = ApiClass.getUserServiceMessagesWithChatId().userLogin(messageWithChatidRequest);
//        messagesResponseCall.enqueue(new Callback<MessagesResponse>() {
//            @Override
//            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
//                if (response.isSuccessful()) {
//                    messageAdapter = new MessageFirebaseAdapter(Message.this, response.body().data,driver_id);
//                    recyclerView.setAdapter(messageAdapter);
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MessagesResponse> call, Throwable t) {
////                Toast.makeText(context, "Throwable " + t, Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "Error " + t);
//            }
//        });
//    }

    private void seenMessage(final String client_id)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        seenlistner = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(driver_id) && chat.getSender().equals(client_id))
                    {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("isseen", true);
                        dataSnapshot.getRef().updateChildren(hashMap);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void sendMessage(final String sender, final String receiver, final String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
//        hashMap.put("messagepostid",id_F);
        hashMap.put("isseen",false);

        reference.child("Chats").push().setValue(hashMap);

        DatabaseReference usertokeref = FirebaseDatabase.getInstance().getReference("Tokens").child(client_id).child("token");
        usertokeref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    token_id = snapshot.getValue().toString();
                    Log.d("TokenId",":"+token_id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(client_id)
                .child(driver_id);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists())
                {
                    chatRef.child("id").setValue(token_id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final String msg = message;

//        reference = FirebaseDatabase.getInstance().getReference("Driver").child(driver_id);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Hirer hirer = snapshot.getValue(Hirer.class);
////                if (notify) {
//                Log.d("MyTag : Notification ","Notification Call");
//                    sendNoti(usertoken,sender,message);
//        sendNotification(client_id,driver_id,message,token_id);
        sendNotificationToUser(token_id);
//                }
        notify =false;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void sendNotification(final String receiver, final String username, final String msg,final String token) {

//        Token tokendata = new Token();
//        tokendata.setToken(token);
//
//        Data data = new Data(client_id,R.mipmap.ic_launcher,username+": "+msg,"New Message",driver_id);
////                    Toast.makeText(MessageActivity.this, "Message Recived "+username+": "+msg, Toast.LENGTH_SHORT).show();
////                    Toast.makeText(MessageActivity.this, "Tokenn"+token, Toast.LENGTH_SHORT).show();
//        Sender sender = new Sender(data,tokendata.getToken());
//
//        apiServices.sendNotification(sender)
//                .enqueue(new Callback<MyResponse>() {
//                    @Override
//                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
//                        if (response.code() == 200) {
//                            if (response.body().success == 1) {
//                                Toast.makeText(Message.this, "Failed!", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MyResponse> call, Throwable t) {
//
//                    }
//                });
//







//        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens").child(receiver);


//        Query query = tokens.orderByKey().equalTo(receiver);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren())
//                {
//                    Token token = dataSnapshot.getValue(Token.class);
//                    Data data = new Data(driver_id,R.mipmap.ic_launcher,username+": "+msg,"New Message",client_id);
////                    Toast.makeText(MessageActivity.this, "Message Recived "+username+": "+msg, Toast.LENGTH_SHORT).show();
////                    Toast.makeText(MessageActivity.this, "Tokenn"+token, Toast.LENGTH_SHORT).show();
//                    Sender sender = new Sender(data,token.getToken());
//
//                    apiServices.sendNotification(sender)
//                            .enqueue(new Callback<MyResponse>() {
//                                @Override
//                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
//                                    if (response.code() == 200){
//                                        if (response.body().success != 1)
//                                        {
//                                            Toast.makeText(Messages.this, "Failed!", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<MyResponse> call, Throwable t) {
//
//                                }
//                            });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Messages.this, ""+error, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void sendNotificationToUser(String token) {
        RootModel rootModel = new RootModel(token, new NotificationModel("Title", "Body"), new DataModel("Name", "30"));

        APIServices apiService =  Client.getClient("http://fcm.googleapis.com/").create(APIServices.class);
        retrofit2.Call<ResponseBody> responseBodyCall = apiService.sendNotification(rootModel);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d("TAG","Successfully notification send by using retrofit.");
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }



    private void readMessage(final String myId,final String userId){
        mChat = new ArrayList<>();

        databaseReference= FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChat.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    Log.d("MyTag : Receiver",chat.getReceiver());
                    Log.d("MyTag",chat.getSender());
                    if(chat.getReceiver().equals(myId) && chat.getSender().equals(userId) || chat.getReceiver().equals(userId) && chat.getSender().equals(myId))
                    {
//                        messagepostid= chat.getMessagepostid();
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                        editor.putString("messagepostid", messagepostid);
//                        editor.commit();
                        mChat.add(chat);
//                        Log.d("MyTag","id :"+messagepostid);
                    }

                    messageAdapter = new MessageFirebaseAdapter(Message.this, mChat,driver_id);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        id_F = sharedPref.getString("messagepostid","");
//
//        if (firebaseiduser==null)
//        {
////            Toast.makeText(this, "id"+messagepostid, Toast.LENGTH_SHORT).show();
//            dataGetFirebasePost(id_F);
//        }
//        else {
//            id_F=firebaseiduser;
////            Toast.makeText(this, "Not Null", Toast.LENGTH_SHORT).show();
//            dataGetFirebasePost(id_F);
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}