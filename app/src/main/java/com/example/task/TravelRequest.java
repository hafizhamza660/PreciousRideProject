package com.example.task;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.task.Dialog.AmountEnter;
import com.example.task.Dialog.CurrencySelection;
import com.google.android.material.navigation.NavigationView;

public class TravelRequest extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    Button accept_btn_1,accept_btn_2,accept_btn_3,accept_btn_4,accept_btn_5,accept_btn_6;
    CardView card_offer_1,card_offer_2,card_offer_3,card_offer_4,card_offer_5,card_offer_6;
    LinearLayout nego_layout_1,nego_layout_2,nego_layout_3,nego_layout_4,nego_layout_5,nego_layout_6;
    Switch switchbtn;
    public static final String TAG ="HomeONline";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_request);
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
        nego_layout_1 = findViewById(R.id.nego_layout_1);
        nego_layout_2 = findViewById(R.id.nego_layout_2);
        nego_layout_3 = findViewById(R.id.nego_layout_3);
        nego_layout_4 = findViewById(R.id.nego_layout_4);
        nego_layout_5 = findViewById(R.id.nego_layout_5);
        nego_layout_6 = findViewById(R.id.nego_layout_6);
        switchbtn = findViewById(R.id.switchbtn);
        card_offer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nego_layout_1.setVisibility(View.VISIBLE);
                accept_btn_1.setVisibility(View.VISIBLE);

                accept_btn_2.setVisibility(View.GONE);
                accept_btn_3.setVisibility(View.GONE);
                accept_btn_4.setVisibility(View.GONE);
                accept_btn_5.setVisibility(View.GONE);
                accept_btn_6.setVisibility(View.GONE);


                nego_layout_2.setVisibility(View.GONE);
                nego_layout_3.setVisibility(View.GONE);
                nego_layout_4.setVisibility(View.GONE);
                nego_layout_5.setVisibility(View.GONE);
                nego_layout_6.setVisibility(View.GONE);
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

                nego_layout_1.setVisibility(View.GONE);
                nego_layout_2.setVisibility(View.VISIBLE);
                nego_layout_3.setVisibility(View.GONE);
                nego_layout_4.setVisibility(View.GONE);
                nego_layout_5.setVisibility(View.GONE);
                nego_layout_6.setVisibility(View.GONE);
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


                nego_layout_1.setVisibility(View.GONE);
                nego_layout_2.setVisibility(View.GONE);
                nego_layout_3.setVisibility(View.VISIBLE);
                nego_layout_4.setVisibility(View.GONE);
                nego_layout_5.setVisibility(View.GONE);
                nego_layout_6.setVisibility(View.GONE);
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

                nego_layout_1.setVisibility(View.GONE);
                nego_layout_2.setVisibility(View.GONE);
                nego_layout_3.setVisibility(View.GONE);
                nego_layout_4.setVisibility(View.VISIBLE);
                nego_layout_5.setVisibility(View.GONE);
                nego_layout_6.setVisibility(View.GONE);
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

                nego_layout_1.setVisibility(View.GONE);
                nego_layout_2.setVisibility(View.GONE);
                nego_layout_3.setVisibility(View.GONE);
                nego_layout_4.setVisibility(View.GONE);
                nego_layout_5.setVisibility(View.VISIBLE);
                nego_layout_6.setVisibility(View.GONE);
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

                nego_layout_1.setVisibility(View.GONE);
                nego_layout_2.setVisibility(View.GONE);
                nego_layout_3.setVisibility(View.GONE);
                nego_layout_4.setVisibility(View.GONE);
                nego_layout_5.setVisibility(View.GONE);
                nego_layout_6.setVisibility(View.VISIBLE);
            }
        });



        accept_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelRequest.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelRequest.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelRequest.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelRequest.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelRequest.this, HomeOnlineBookingDetails.class));
            }
        });
        accept_btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelRequest.this, HomeOnlineBookingDetails.class));
            }
        });


        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
                    startActivity(new Intent(TravelRequest.this, HomeOffline.class));
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
}