package com.example.task;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.wenchao.cardstack.CardStack;

public class History extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;


    LinearLayout sun_card,mon_card,tue_card,wed_card,thu_card,fri_card,sat_card;
    TextView sun_txt,mon_txt,tue_txt,wed_txt,thu_txt,fri_txt,sat_txt;
    TextView sun_date,mon_date,tue_date,wed_date,thu_date,fri_date,sat_date;
    ColorStateList oldColor;
    Drawable bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


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
        nvDrawer.getMenu().getItem(4).setChecked(true);

        initView();

        oldColor =  sun_txt.getTextColors(); //save original colors
        bg =  sun_card.getBackground(); //save original colors

        buttonFuc();
    }

    private void initView(){
        sun_card=findViewById(R.id.sun_card);
        mon_card=findViewById(R.id.mon_card);
        tue_card=findViewById(R.id.tue_card);
        wed_card=findViewById(R.id.wed_card);
        thu_card=findViewById(R.id.thu_card);
        fri_card=findViewById(R.id.fri_card);
        sat_card=findViewById(R.id.sat_card);

        sun_txt=findViewById(R.id.sun_txt);
        mon_txt=findViewById(R.id.mon_txt);
        tue_txt=findViewById(R.id.tue_txt);
        wed_txt=findViewById(R.id.wed_txt);
        thu_txt=findViewById(R.id.thu_txt);
        fri_txt=findViewById(R.id.fri_txt);
        sat_txt=findViewById(R.id.sat_txt);

        sun_date=findViewById(R.id.sun_date);
        mon_date=findViewById(R.id.mon_date);
        tue_date=findViewById(R.id.tue_date);
        wed_date=findViewById(R.id.wed_date);
        thu_date=findViewById(R.id.thu_date);
        fri_date=findViewById(R.id.fri_date);
        sat_date=findViewById(R.id.sat_date);

    }


    private void buttonFuc(){
        sun_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                sun_card.setBackgroundResource(R.drawable.background_card_stroke);
                sun_txt.setTextColor(getResources().getColor(R.color.orange, null));
                sun_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("sun");

            }
        });

        mon_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mon_card.setBackgroundResource(R.drawable.background_card_stroke);
                mon_txt.setTextColor(getResources().getColor(R.color.orange, null));
                mon_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("mon");

            }
        });

        tue_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                tue_card.setBackgroundResource(R.drawable.background_card_stroke);
                tue_txt.setTextColor(getResources().getColor(R.color.orange, null));
                tue_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("tue");

            }
        });

        wed_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                wed_card.setBackgroundResource(R.drawable.background_card_stroke);
                wed_txt.setTextColor(getResources().getColor(R.color.orange, null));
                wed_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("wed");

            }
        });

        thu_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                thu_card.setBackgroundResource(R.drawable.background_card_stroke);
                thu_txt.setTextColor(getResources().getColor(R.color.orange, null));
                thu_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("thu");

            }
        });

        fri_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                fri_card.setBackgroundResource(R.drawable.background_card_stroke);
                fri_txt.setTextColor(getResources().getColor(R.color.orange, null));
                fri_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("fri");

            }
        });
        sat_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                sat_card.setBackgroundResource(R.drawable.background_card_stroke);
                sat_txt.setTextColor(getResources().getColor(R.color.orange, null));
                sat_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("sat");

            }
        });
    }

    private void colorRestore(String name){
        switch (name)
        {
            case "sun":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);



                break;
            case "mon":
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                sun_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "tue":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                sun_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "wed":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                sun_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "thu":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sat_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                sun_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "fri":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sat_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                sun_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "sat":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sun_card.setBackground(bg);
                break;
        }
    }


    public void history_details(View view) {
        startActivity(new Intent(History.this,Recipt.class));
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
                i = new Intent(History.this,HomeOffline.class);
                startActivity(i);
                break;
            case R.id.my_wallet:
                i = new Intent(History.this,Wallet.class);
                startActivity(i);
                break;
            case R.id.travel_request:
                i = new Intent(History.this,TravelRequest.class);
                startActivity(i);
                break;
            case R.id.inter_city:
                i = new Intent(History.this,InterCityRequests.class);
                startActivity(i);
                break;
            case R.id.history:
                i = new Intent(History.this,History.class);
                startActivity(i);
                break;
            case R.id.notification_toolbar:
                i = new Intent(History.this,Notifications.class);
                startActivity(i);
                break;
            case R.id.invite_friends:
                i = new Intent(History.this,InviteFriends.class);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(History.this,Setting.class);
                startActivity(i);
                break;
            case R.id.campaign_menu:
                i = new Intent(History.this,CampaignView.class);
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