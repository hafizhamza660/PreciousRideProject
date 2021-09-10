package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getFirstName;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.adapters.StackAdapter;
import com.google.android.material.navigation.NavigationView;

import in.arjsna.swipecardlib.SwipeCardView;

public class HomeSwipeUp extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    Button accept_btn_1,accept_btn_2,accept_btn_3,accept_btn_4,accept_btn_5,accept_btn_6;
    CardView card_offer_1,card_offer_2,card_offer_3,card_offer_4,card_offer_5,card_offer_6;
    Switch switchbtn;
    public static final String TAG ="HomeONline";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_swipe_up);

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
//        View hView =  nvDrawer.getHeaderView(0);
//        TextView drivername = (TextView)hView.findViewById(R.id.driver_name);
//        drivername.setText(getFirstName(context));
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        /*ToolBar With NavBar End*/

        card_offer_1 = findViewById(R.id.card_offer_1);
        card_offer_2 = findViewById(R.id.card_offer_2);
        card_offer_3 = findViewById(R.id.card_offer_3);
        card_offer_4 = findViewById(R.id.card_offer_4);
        card_offer_5 = findViewById(R.id.card_offer_5);
        card_offer_6 = findViewById(R.id.card_offer_6);
        accept_btn_1 = findViewById(R.id.accept_btn_1);
        accept_btn_2 = findViewById(R.id.accept_btn_2);
        accept_btn_3 = findViewById(R.id.accept_btn_3);
        accept_btn_4 = findViewById(R.id.accept_btn_4);
        accept_btn_5 = findViewById(R.id.accept_btn_5);
        accept_btn_6 = findViewById(R.id.accept_btn_6);
        switchbtn = findViewById(R.id.switchbtn);
        card_offer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.VISIBLE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.VISIBLE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.VISIBLE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.VISIBLE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.VISIBLE);
                accept_btn_6.setVisibility(View.GONE);
            }
        });

        card_offer_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept_btn_1.setVisibility(View.GONE);
                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.VISIBLE);
            }
        });



        accept_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeSwipeUp.this, HomeOnlineBookingDetails.class));
            }
        });


        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
                    startActivity(new Intent(HomeSwipeUp.this, HomeOffline.class));
                }
            }
        });
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
                i = new Intent(HomeSwipeUp.this,HomeOffline.class);
                startActivity(i);
                break;
            case R.id.my_wallet:
                i = new Intent(HomeSwipeUp.this,Wallet.class);
                startActivity(i);
                break;
            case R.id.travel_request:
                i = new Intent(HomeSwipeUp.this,TravelRequest.class);
                startActivity(i);
                break;
//            case R.id.inter_city:
//                i = new Intent(HomeSwipeUp.this,InterCityRequests.class);
//                startActivity(i);
//                break;
            case R.id.history:
                i = new Intent(HomeSwipeUp.this,History.class);
                startActivity(i);
                break;
            case R.id.notification_toolbar:
                i = new Intent(HomeSwipeUp.this,Notifications.class);
                startActivity(i);
                break;
            case R.id.invite_friends:
                i = new Intent(HomeSwipeUp.this,InviteFriends.class);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(HomeSwipeUp.this,Setting.class);
                startActivity(i);
                break;
            case R.id.campaign_menu:
                i = new Intent(HomeSwipeUp.this,CampaignView.class);
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
}