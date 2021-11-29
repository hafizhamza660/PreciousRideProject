package com.example.task;

import static com.example.task.Session.SaveSharedPreference.clearClientId;
import static com.example.task.Session.SaveSharedPreference.getClientId;
import static com.example.task.Session.SaveSharedPreference.getFirstName;
import static com.example.task.Session.SaveSharedPreference.getInterCity;
import static com.example.task.Session.SaveSharedPreference.getLastName;
import static com.example.task.Session.SaveSharedPreference.setInterCity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.Dialog.CurrencySelection;
import com.example.task.LogoutStatusFiles.RequestLogoutStatus;
import com.example.task.LogoutStatusFiles.ResponseLogoutStatus;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Setting extends AppCompatActivity {
RelativeLayout container_2,currency_button,bank_button,campaign_button;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    TextView name;
    Context context;
    Switch intercityswtich;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context=this;
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
        View hView =  nvDrawer.getHeaderView(0);
        TextView drivername = (TextView)hView.findViewById(R.id.driver_name);
        drivername.setText(getFirstName(context));
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        /*ToolBar With NavBar End*/
        nvDrawer.getMenu().getItem(7).setChecked(true);

        container_2 = findViewById(R.id.container_2);
        currency_button = findViewById(R.id.currency_button);
        bank_button = findViewById(R.id.bank_button);
        name = findViewById(R.id.name);

        name.setText(getFirstName(context)+" "+getLastName(context));
//        campaign_button = findViewById(R.id.campaign_button);
        container_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,MyAccount.class));
            }
        });
        currency_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencySelection exitDialog = new CurrencySelection(Setting.this);
                exitDialog.show();
                Window window = exitDialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

        bank_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,PaymentMethod.class));
            }
        });

        intercityswtich = findViewById(R.id.intercityswitchbtn);

        val = getInterCity(context);
        if (val.equals("0"))
        {
            intercityswtich.setChecked(false);
        }
        else if (val.equals("1"))
        {
            intercityswtich.setChecked(true);
        }

        intercityswtich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setInterCity(context,"1");
                    val = getInterCity(context);
                } else {
                    setInterCity(context,"0");
                    val = getInterCity(context);
                }
            }
        });

//        campaign_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Setting.this,CampaignView.class));
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        name.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        name.setText(getFirstName(context)+" "+getLastName(context));
    }

    public void vehicle_click(View view) {
        startActivity(new Intent(Setting.this,VehicleManagement.class));
    }

    public void document(View view) {
        startActivity(new Intent(Setting.this,DocumentManagement.class));
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
                i = new Intent(Setting.this,HomeOffline.class);
                startActivity(i);
                finish();
                break;
            case R.id.my_wallet:
                i = new Intent(Setting.this,Wallet.class);
                startActivity(i);
                finish();
                break;
            case R.id.travel_request:
                i = new Intent(Setting.this,TravelRequest.class);
                startActivity(i);
                finish();
                break;
            case R.id.inter_city:
                if (val.equals("0")) {
                    nvDrawer.getMenu().getItem(0).setChecked(true);
                    Toast.makeText(this, "Go to setting and switch on the Inter-State", Toast.LENGTH_SHORT).show();
                } else if (val.equals("1")) {
                    i = new Intent(Setting.this, InterCityRequests.class);
                    startActivity(i);
                    finish();
                }
                break;
            case R.id.history:
                i = new Intent(Setting.this,History.class);
                startActivity(i);
                finish();
                break;
            case R.id.notification_toolbar:
                i = new Intent(Setting.this,Notifications.class);
                startActivity(i);
                finish();
                break;
            case R.id.invite_friends:
                i = new Intent(Setting.this,InviteFriends.class);
                startActivity(i);
                finish();
                break;
            case R.id.setting:
//                i = new Intent(Setting.this,Setting.class);
//                startActivity(i);
                Toast.makeText(this, "Already in that tab", Toast.LENGTH_SHORT).show();
                break;
            case R.id.campaign_menu:
                i = new Intent(Setting.this,CampaignView.class);
                startActivity(i);
                finish();
                break;

            case R.id.logout:
                logoutstatus();
                clearClientId(context);
                i = new Intent(Setting.this, WelcomeScreen.class);
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

    public void logoutstatus() {
        RequestLogoutStatus requestLogoutStatus = new RequestLogoutStatus();
        requestLogoutStatus.setDriver_id(getClientId(context));


        Call<ResponseLogoutStatus> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverLogoutStatus(requestLogoutStatus);
        signUpResponseCall.enqueue(new Callback<ResponseLogoutStatus>() {
            @Override
            public void onResponse(Call<ResponseLogoutStatus> call, Response<ResponseLogoutStatus> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(Setting.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("1")) {
//                        Toast.makeText(Setting.this, "You are online", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(Setting.this, "Driver Logout Successfully", Toast.LENGTH_LONG).show();

                    }

                } else {
//                    Toast.makeText(Setting.this, "Request Denied", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogoutStatus> call, Throwable t) {
//                Toast.makeText(Setting.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }
}