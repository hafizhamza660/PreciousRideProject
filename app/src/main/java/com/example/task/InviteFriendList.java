package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.task.adapters.InviteFiendListAdapter;
import com.example.task.adapters.NotificationAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class InviteFriendList extends AppCompatActivity {
    ArrayList friends_name = new ArrayList<>(Arrays.asList("Jackson Daniel", "Nellie Scott","Shane Morales","Sophie Bell","Rhoda Palmer","Trevor Salazar","Chester Jennings","Amy Pena"));
    ArrayList mutual_friend_count = new ArrayList<>(Arrays.asList("5 mutual friends","I5 mutual friends","5 mutual friends","5 mutual friends","5 mutual friends","I5 mutual friends","5 mutual friends","5 mutual friends"));
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend_list);


        recyclerView = findViewById(R.id.recycler_view_notification);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        InviteFiendListAdapter inviteFiendListAdapter = new InviteFiendListAdapter(InviteFriendList.this, friends_name,mutual_friend_count);
        recyclerView.setAdapter(inviteFiendListAdapter);
    }

    public void back_button(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}