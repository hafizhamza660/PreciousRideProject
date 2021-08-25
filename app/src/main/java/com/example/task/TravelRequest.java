package com.example.task;

import static com.example.task.Session.SaveSharedPreference.setCity;
import static com.example.task.Session.SaveSharedPreference.setClientId;
import static com.example.task.Session.SaveSharedPreference.setEmail;
import static com.example.task.Session.SaveSharedPreference.setFirstName;
import static com.example.task.Session.SaveSharedPreference.setLastName;
import static com.example.task.Session.SaveSharedPreference.setMobileNumber;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.task.API.ApiClass;
import com.example.task.Dialog.AmountEnter;
import com.example.task.Dialog.CurrencySelection;
import com.example.task.FilesLogin.RequestLogin;
import com.example.task.FilesLogin.ResponseLogin;
import com.example.task.RideRequestFiles.Data;
import com.example.task.RideRequestFiles.RideRequestResponse;
import com.example.task.adapters.RideRequestListAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelRequest extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    TravelRequest activity;
    Context context;

    Switch switchbtn;
    public static final String TAG ="HomeONline";

    RecyclerView recyclerViewRideRequest;
    LinearLayoutManager linearLayoutManager;
    private RideRequestListAdapter rideRequestListAdapter;
    private List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_request);
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
        nvDrawer.getMenu().getItem(2).setChecked(true);

        switchbtn = findViewById(R.id.switchbtn);



        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
                    startActivity(new Intent(TravelRequest.this, HomeOffline.class));
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
                i = new Intent(TravelRequest.this,HomeOffline.class);
                startActivity(i);
                break;
            case R.id.my_wallet:
                i = new Intent(TravelRequest.this,Wallet.class);
                startActivity(i);
                break;
            case R.id.travel_request:
                i = new Intent(TravelRequest.this,TravelRequest.class);
                startActivity(i);
                break;
//            case R.id.inter_city:
//                i = new Intent(TravelRequest.this,InterCityRequests.class);
//                startActivity(i);
//                break;
            case R.id.history:
                i = new Intent(TravelRequest.this,History.class);
                startActivity(i);
                break;
            case R.id.notification_toolbar:
                i = new Intent(TravelRequest.this,Notifications.class);
                startActivity(i);
                break;
            case R.id.invite_friends:
                i = new Intent(TravelRequest.this,InviteFriends.class);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(TravelRequest.this,Setting.class);
                startActivity(i);
                break;
            case R.id.campaign_menu:
                i = new Intent(TravelRequest.this,CampaignView.class);
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

    public void negotiate(View view) {
        AmountEnter exitDialog = new AmountEnter(TravelRequest.this);
        exitDialog.show();
        Window window = exitDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void riderequest() {


        Call<RideRequestResponse> signUpResponseCall = ApiClass.getUserServiceRideRequest().userLogin();
        signUpResponseCall.enqueue(new Callback<RideRequestResponse>() {
            @Override
            public void onResponse(Call<RideRequestResponse> call, Response<RideRequestResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TravelRequest.this, ""+response.body().data, Toast.LENGTH_LONG).show();
//                    Log.d(TAG,"Data : "+response.body().data.get(0).id);
                    if (response.body().data.equals(null))
                    {

                    }
                    else {
                        rideRequestListAdapter = new RideRequestListAdapter(activity, context, response.body().data);
                        recyclerViewRideRequest.setAdapter(rideRequestListAdapter);
                    }
//
                } else {
                    Toast.makeText(TravelRequest.this, "Register Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RideRequestResponse> call, Throwable t) {
                Toast.makeText(TravelRequest.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}