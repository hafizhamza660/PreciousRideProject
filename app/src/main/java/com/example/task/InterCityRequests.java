package com.example.task;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.task.API.ApiClass;
import com.example.task.InterCityRequest.InterCityRideRequestResponse;
import com.example.task.RideRequestFiles.Data;
import com.example.task.RideRequestFiles.RideRequestResponse;
import com.example.task.adapters.InterCityRideRequestListAdapter;
import com.example.task.adapters.RideRequestListAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterCityRequests extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    InterCityRequests activity;
    Context context;
    RecyclerView recyclerViewRideRequest;
    LinearLayoutManager linearLayoutManager;
    private InterCityRideRequestListAdapter rideRequestListAdapter;
    private List<Data> dataList;
    Switch switchbtn;
    public static final String TAG ="HomeONline";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_city_requests);
        activity = this;
        context = this;
        /*ToolBar With NavBar*/
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
//        defaultScreen();



        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_burger_icon);


        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // ...From section above...
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        /*ToolBar With NavBar End*/
        nvDrawer.getMenu().getItem(3).setChecked(true);

        switchbtn = findViewById(R.id.switchbtn);



        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
                    startActivity(new Intent(InterCityRequests.this, HomeOffline.class));
                }
            }
        });

        recyclerViewRideRequest = findViewById(R.id.recycler_view_rideRequest);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewRideRequest.setLayoutManager(linearLayoutManager);

        riderequest();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_open,  R.string.navigation_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }



    public void selectDrawerItem(MenuItem menuItem) {

        Intent i;
        switch(menuItem.getItemId()) {
            case R.id.home:
                i = new Intent(InterCityRequests.this,HomeOffline.class);
                startActivity(i);
                break;
            case R.id.my_wallet:
                i = new Intent(InterCityRequests.this,Wallet.class);
                startActivity(i);
                break;
            case R.id.travel_request:
                i = new Intent(InterCityRequests.this,HomeSwipeUp.class);
                startActivity(i);
                break;
//            case R.id.inter_city:
//                i = new Intent(InterCityRequests.this,InterCityRequests.class);
//                startActivity(i);
//                break;
            case R.id.history:
                i = new Intent(InterCityRequests.this,History.class);
                startActivity(i);
                break;
            case R.id.notification_toolbar:
                i = new Intent(InterCityRequests.this,Notifications.class);
                startActivity(i);
                break;
            case R.id.invite_friends:
                i = new Intent(InterCityRequests.this,InviteFriends.class);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(InterCityRequests.this,Setting.class);
                startActivity(i);
                break;

            default:
                Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
        }


        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
//         setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public void riderequest() {


        Call<InterCityRideRequestResponse> signUpResponseCall = ApiClass.getUserServiceInterCityRideRequest().userLogin();
        signUpResponseCall.enqueue(new Callback<InterCityRideRequestResponse>() {
            @Override
            public void onResponse(Call<InterCityRideRequestResponse> call, Response<InterCityRideRequestResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(InterCityRequests.this, ""+response.body().data, Toast.LENGTH_LONG).show();
//                    Log.d(TAG,"Data : "+response.body().data.get(0).id);
                    if (response.body().data.equals(null))
                    {

                    }
                    else {
                        rideRequestListAdapter = new InterCityRideRequestListAdapter(activity, context, response.body().data);
                        recyclerViewRideRequest.setAdapter(rideRequestListAdapter);
                    }
//
                } else {
                    Toast.makeText(InterCityRequests.this, "Register Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InterCityRideRequestResponse> call, Throwable t) {
                Toast.makeText(InterCityRequests.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}