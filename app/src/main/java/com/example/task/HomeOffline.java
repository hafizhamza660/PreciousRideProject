package com.example.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.StackView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.Dialog.Rules;
import com.example.task.Floating.FloatingViewService;
import com.example.task.adapters.StackAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import in.arjsna.swipecardlib.SwipeCardView;

public class HomeOffline extends AppCompatActivity  {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    Switch switchbtn;
    LinearLayout homeOnline;
    RelativeLayout homeOffline;
    CardStack stackView;
    TextView toolbar_title;
    TextView timer;

    ImageView minus_range,add_range;
    TextView km_range;
    Context context;

    public static final String TAG ="HomeONline";
//    public int counter;
    BottomSheetBehavior bottomSheetBehavior;
    ConstraintLayout bottomSheetLayout;
//    private static final long START_TIME_IN_MILLIS = 600000;
//    private CountDownTimer mCountDownTimer;
//    private boolean mTimerRunning;
//    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    int counter=0;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    FloatingActionButton floatingActionButton,floatingActionButton_online;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_offline);
        context=this;
        Configuration config = getResources().getConfiguration();
        if(isNetworkConnected())
        {
            Toast.makeText(this, "Internet connected successfully", Toast.LENGTH_SHORT).show();
            if(locationServicesEnabled(context))
            {
                Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            startActivity(new Intent(HomeOffline.this,SetupGPSLocationActivity.class));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }






        /*ToolBar With NavBar*/
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
//        defaultScreen();



        // This will display an Up icon (<-), we will replace it with hamburger later
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_burger_icon);


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
        nvDrawer.getMenu().getItem(0).setChecked(true);

        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        timer = findViewById(R.id.timer);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton_online = findViewById(R.id.floatingActionButton_online);

//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();




        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
//                        textViewBottomSheetState.setText("STATE HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
//                        textViewBottomSheetState.setText("STATE EXPANDED");
                        // update toggle button text
//                        toggleBottomSheet.setText("Expand BottomSheet");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        textViewBottomSheetState.setText("STATE COLLAPSED");
                        // update collapsed button text
//                        toggleBottomSheet.setText("Collapse BottomSheet");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
//                        textViewBottomSheetState.setText("STATE DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
//                        textViewBottomSheetState.setText("STATE SETTLING");
                        break;
                }
                Log.d(TAG, "onStateChanged: " + newState);
            }
            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });



        switchbtn= findViewById(R.id.switchbtn);
        homeOffline= findViewById(R.id.homeOffline);
        homeOnline= findViewById(R.id.homeOnline);

        stackView= findViewById(R.id.stackview);
        toolbar_title= findViewById(R.id.toolbar_title);


        minus_range= findViewById(R.id.minus_range);
        add_range= findViewById(R.id.add_range);
        km_range= findViewById(R.id.km_range);


        km_range.setText(counter+" KM");

            minus_range.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter--;
                    km_range.setText(counter+" KM");
                    if (counter == 0)
                    {
                        minus_range.setEnabled(false);


                    }

                }
            });
            add_range.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter++;
                    km_range.setText(counter+" KM");
                    if (counter>0)
                    {
                        minus_range.setEnabled(true);
                    }
                }
            });





        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    Rules exitDialog = new Rules(HomeOffline.this);
                    exitDialog.show();
                    Window window = exitDialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    // The toggle is enabled
                    long duration = TimeUnit.MINUTES.toMillis(1);
//                  new CountDownTimer(duration, 1000){
//            public void onTick(long millisUntilFinished){
//                String sDuration = String.format(Locale.ENGLISH,"%02d:%02d"
//                ,TimeUnit.MILLISECONDS.toMinutes(1)
//                ,TimeUnit.MILLISECONDS.toSeconds(1)-
//                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
//
//                timer.setText(sDuration);
////                counter++;
//            }
//            public  void onFinish(){
////                stackView.setVisibility(View.GONE);
////                size = size-1;
//                timer.setText("");
//            }
//        }.start();
//                    startTimer();
                    homeOffline.setVisibility(View.GONE);
                    bottomSheetLayout.setVisibility(View.GONE);
                    homeOnline.setVisibility(View.VISIBLE);
                    toolbar_title.setText("Online");
                    StackAdapter adapter = new StackAdapter(numberWord(),HomeOffline.this,R.layout.item_stack);

                    stackView.setAdapter(adapter);
                    YourListner yourListner = new YourListner();
                    stackView.setListener(yourListner);
                    floatingActionButton.setVisibility(View.GONE);
                    floatingActionButton_online.setVisibility(View.VISIBLE);
//                    floatingActionButton.setY(800);

//                    stackView.setFlingListener(new SwipeCardView.OnCardFlingListener() {
//                        @Override
//                        public void onCardExitLeft(Object dataObject) {
//                            Log.i(TAG, "Left Exit");
//                        }
//
//                        @Override
//                        public void onCardExitRight(Object dataObject) {
//                            Log.i(TAG, "Right Exit");
//                        }
//
//                        @Override
//                        public void onAdapterAboutToEmpty(int itemsInAdapter) {
//                            Log.i(TAG, "Adater to be empty");
//                            //add more items to adapter and call notifydatasetchanged
//                        }
//
//                        @Override
//                        public void onScroll(float scrollProgressPercent) {
//                            Log.i(TAG, "Scroll");
//                        }
//
//                        @Override
//                        public void onCardExitTop(Object dataObject) {
//                            Log.i(TAG, "Top Exit");
//                            startActivity(new Intent(HomeOffline.this, HomeSwipeUp.class));
//                        }
//
//                        @Override
//                        public void onCardExitBottom(Object dataObject) {
//                            Log.i(TAG, "Bottom Exit");
//                        }
//                    });

                } else {
                    // The toggle is disabled
                    homeOffline.setVisibility(View.VISIBLE);
                    bottomSheetLayout.setVisibility(View.VISIBLE);
                    homeOnline.setVisibility(View.GONE);
                    toolbar_title.setText("Offline");
//                    Log.d("FloatingParam",""+params.getAnchorId());
                    floatingActionButton_online.setVisibility(View.GONE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            startService(new Intent(HomeOffline.this, FloatingViewService.class));
//            finish();
//        } else if (Settings.canDrawOverlays(this)) {
//            startService(new Intent(HomeOffline.this, FloatingViewService.class));
//            finish();
//        } else {
//            askPermission();
//            Toast.makeText(this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
//        }
//    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public static boolean locationServicesEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean net_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            Log.e(TAG,"Exception gps_enabled");
        }

        try {
            net_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            Log.e(TAG,"Exception network_enabled");
        }
        return gps_enabled || net_enabled;
    }
    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        stopService(new Intent(HomeOffline.this, FloatingViewService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            startService(new Intent(HomeOffline.this, FloatingViewService.class));
            finish();
        } else if (Settings.canDrawOverlays(this)) {
            startService(new Intent(HomeOffline.this, FloatingViewService.class));
            finish();
        } else {
            askPermission();
            Toast.makeText(this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stopService(new Intent(HomeOffline.this, FloatingViewService.class));

    }

    private List<String> numberWord(){
        List<String> word = new ArrayList<>();
        word.add("one");
//        word.add("two");
//        word.add("three");
//        word.add("four");
        return word;
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
                 i = new Intent(HomeOffline.this,HomeOffline.class);
                startActivity(i);
                break;
            case R.id.my_wallet:
                i = new Intent(HomeOffline.this,Wallet.class);
                startActivity(i);
                break;
            case R.id.travel_request:
                i = new Intent(HomeOffline.this,TravelRequest.class);
                startActivity(i);
                break;
//            case R.id.inter_city:
//                i = new Intent(HomeOffline.this,InterCityRequests.class);
//                startActivity(i);
//                break;
            case R.id.history:
                i = new Intent(HomeOffline.this,History.class);
                startActivity(i);
                break;
            case R.id.notification_toolbar:
                i = new Intent(HomeOffline.this,Notifications.class);
                startActivity(i);
                break;
            case R.id.invite_friends:
                i = new Intent(HomeOffline.this,InviteFriends.class);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(HomeOffline.this,Setting.class);
                startActivity(i);
                break;
            case R.id.campaign_menu:
                i = new Intent(HomeOffline.this,CampaignView.class);
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

    public class YourListner implements CardStack.CardEventListener {
        //implement card event interface
        @Override
        public boolean swipeEnd(int direction, float distance) {
            //if "return true" the dismiss animation will be triggered
            //if false, the card will move back to stack
            //distance is finger swipe distance in dp

            //the direction indicate swipe direction
            //there are four directions
            //  0  |  1
            // ----------
            //  2  |  3

//            if (direction ==0 || direction ==1)
//            {
//                startActivity(new Intent(HomeOffline.this,HomeSwipeUp.class));
//            }

            return (distance>300)? true : false;
        }

        @Override
        public boolean swipeStart(int direction, float distance) {

            return true;
        }

        @Override
        public boolean swipeContinue(int direction, float distanceX, float distanceY) {

            return true;
        }

        @Override
        public void discarded(int id, int direction) {
            //this callback invoked when dismiss animation is finished.
        }

        @Override
        public void topCardTapped() {
            //this callback invoked when a top card is tapped by user.
        }
    }


//    private void startTimer() {
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
////                mButtonStartPause.setText("Start");
////                mButtonStartPause.setVisibility(View.INVISIBLE);
////                mButtonReset.setVisibility(View.VISIBLE);
//            }
//        }.start();
//        mTimerRunning = true;
////        mButtonStartPause.setText("pause");
////        mButtonReset.setVisibility(View.INVISIBLE);
//    }
//    private void pauseTimer() {
//        mCountDownTimer.cancel();
//        mTimerRunning = false;
////        mButtonStartPause.setText("Start");
////        mButtonReset.setVisibility(View.VISIBLE);
//    }
//    private void resetTimer() {
//        mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        updateCountDownText();
////        mButtonReset.setVisibility(View.INVISIBLE);
////        mButtonStartPause.setVisibility(View.VISIBLE);
//    }
//    private void updateCountDownText() {
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//        timer.setText(timeLeftFormatted);
//    }

}

