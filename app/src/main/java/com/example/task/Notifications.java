package com.example.task;

import static com.example.task.Session.SaveSharedPreference.clearClientId;
import static com.example.task.Session.SaveSharedPreference.getClientId;
import static com.example.task.Session.SaveSharedPreference.getFirstName;
import static com.example.task.Session.SaveSharedPreference.getInterCity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.AllNotificiationFiles.AllNotificationRequest;
import com.example.task.AllNotificiationFiles.AllNotificationResponse;
import com.example.task.LogoutStatusFiles.RequestLogoutStatus;
import com.example.task.LogoutStatusFiles.ResponseLogoutStatus;
import com.example.task.adapters.NotificationAdapter;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notifications extends AppCompatActivity {


    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;


    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    // ArrayList for person names
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    NotificationAdapter notificationAdapter;
    Context context;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        context = this;
        /*ToolBar With NavBar*/
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
//        defaultScreen();
        val = getInterCity(context);


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
        View hView =  nvDrawer.getHeaderView(0);
        TextView drivername = (TextView)hView.findViewById(R.id.driver_name);
        drivername.setText(getFirstName(context));
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        /*ToolBar With NavBar End*/
        nvDrawer.getMenu().getItem(5).setChecked(true);


        recyclerView = findViewById(R.id.recycler_view_notification);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        allnotificationapi();




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
                i = new Intent(Notifications.this,HomeOffline.class);
                startActivity(i);
                finish();
                break;
            case R.id.my_wallet:
                i = new Intent(Notifications.this,Wallet.class);
                startActivity(i);
                finish();
                break;
            case R.id.travel_request:
                i = new Intent(Notifications.this,TravelRequest.class);
                startActivity(i);
                finish();
                break;
            case R.id.inter_city:
                if (val.equals("0")) {
                    nvDrawer.getMenu().getItem(0).setChecked(true);
                    Toast.makeText(this, "Go to setting and switch on the Inter-State", Toast.LENGTH_SHORT).show();
                } else if (val.equals("1")) {
                    i = new Intent(Notifications.this, InterCityRequests.class);
                    startActivity(i);
                    finish();
                }
                break;
            case R.id.history:
                i = new Intent(Notifications.this,History.class);
                startActivity(i);
                finish();
                break;
            case R.id.notification_toolbar:
//                i = new Intent(Notifications.this,Notifications.class);
//                startActivity(i);
                Toast.makeText(this, "Already in that tab", Toast.LENGTH_SHORT).show();
                break;
            case R.id.invite_friends:
                i = new Intent(Notifications.this,InviteFriends.class);
                startActivity(i);
                finish();
                break;
            case R.id.setting:
                i = new Intent(Notifications.this,Setting.class);
                startActivity(i);
                finish();
                break;
            case R.id.campaign_menu:
                i = new Intent(Notifications.this,CampaignView.class);
                startActivity(i);
                finish();
                break;

            case R.id.logout:
                logoutstatus();
                clearClientId(context);
                i = new Intent(Notifications.this, WelcomeScreen.class);
                startActivity(i);
                finish();
                break;

            default:
//                Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
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

    public void allnotificationapi() {
        AllNotificationRequest allnotificationRequest = new AllNotificationRequest();
        allnotificationRequest.setDriver_id(getClientId(context));

        Call<AllNotificationResponse> notificationResponseCall = ApiClass.getUserServiceAPI().userGetDriverAllNotifications(allnotificationRequest);
        notificationResponseCall.enqueue(new Callback<AllNotificationResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<AllNotificationResponse> call, Response<AllNotificationResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(ServiceClass.this, ""+response.body().message, Toast.LENGTH_SHORT).show();

//                    Toast.makeText(Notifications.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("Notifications"))
                    {
//
                        notificationAdapter = new NotificationAdapter(Notifications.this,response.body().data);
                        recyclerView.setAdapter(notificationAdapter);



                    }
//                    Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), PhoneVerification.class);
//                    startActivity(intent);
                } else {
//                    Toast.makeText(Notifications.this, "Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllNotificationResponse> call, Throwable t) {
//                Toast.makeText(Notifications.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(Notifications.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        val = getInterCity(context);
    }

    public void logoutstatus() {
        RequestLogoutStatus requestLogoutStatus = new RequestLogoutStatus();
        requestLogoutStatus.setDriver_id(getClientId(context));


        Call<ResponseLogoutStatus> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverLogoutStatus(requestLogoutStatus);
        signUpResponseCall.enqueue(new Callback<ResponseLogoutStatus>() {
            @Override
            public void onResponse(Call<ResponseLogoutStatus> call, Response<ResponseLogoutStatus> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(Notifications.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("1")) {
//                        Toast.makeText(Notifications.this, "You are online", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(Notifications.this, "Driver Logout Successfully", Toast.LENGTH_LONG).show();

                    }

                } else {
//                    Toast.makeText(Notifications.this, "Request Denied", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogoutStatus> call, Throwable t) {
//                Toast.makeText(Notifications.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(Notifications.this, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}