package com.example.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.StackView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.adapters.StackAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import in.arjsna.swipecardlib.SwipeCardView;

public class HomeOffline extends AppCompatActivity  {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    Switch switchbtn;
    LinearLayout homeOnline;
    RelativeLayout homeOffline;
    SwipeCardView stackView;
    TextView toolbar_title;

    public static final String TAG ="HomeONline";

    BottomSheetBehavior bottomSheetBehavior;
    ConstraintLayout bottomSheetLayout;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_offline);

        Configuration config = getResources().getConfiguration();




//        BottomFragment bottomFragment = new BottomFragment();
//        bottomFragment.show(getSupportFragmentManager(),bottomFragment.getTag());




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


        bottomSheetLayout = findViewById(R.id.bottom_sheet);

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
        // set listener on button click
//        toggleBottomSheet.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    toggleBottomSheet.setText("Collapse BottomSheet");
//                } else {
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                    toggleBottomSheet.setText("Expand BottomSheet");
//                }
//            }
//        });


        switchbtn= findViewById(R.id.switchbtn);
        homeOffline= findViewById(R.id.homeOffline);
        homeOnline= findViewById(R.id.homeOnline);


        stackView= findViewById(R.id.stackview);
        toolbar_title= findViewById(R.id.toolbar_title);



        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    homeOffline.setVisibility(View.GONE);
                    bottomSheetLayout.setVisibility(View.GONE);
                    homeOnline.setVisibility(View.VISIBLE);
                    toolbar_title.setText("Online");
                    StackAdapter adapter = new StackAdapter(numberWord(),HomeOffline.this,R.layout.item_stack);

                    stackView.setAdapter(adapter);
                    stackView.setFlingListener(new SwipeCardView.OnCardFlingListener() {
                        @Override
                        public void onCardExitLeft(Object dataObject) {
                            Log.i(TAG, "Left Exit");
                        }

                        @Override
                        public void onCardExitRight(Object dataObject) {
                            Log.i(TAG, "Right Exit");
                        }

                        @Override
                        public void onAdapterAboutToEmpty(int itemsInAdapter) {
                            Log.i(TAG, "Adater to be empty");
                            //add more items to adapter and call notifydatasetchanged
                        }

                        @Override
                        public void onScroll(float scrollProgressPercent) {
                            Log.i(TAG, "Scroll");
                        }

                        @Override
                        public void onCardExitTop(Object dataObject) {
                            Log.i(TAG, "Top Exit");
                            startActivity(new Intent(HomeOffline.this, HomeSwipeUp.class));
                        }

                        @Override
                        public void onCardExitBottom(Object dataObject) {
                            Log.i(TAG, "Bottom Exit");
                        }
                    });

                } else {
                    // The toggle is disabled
                    homeOffline.setVisibility(View.VISIBLE);
                    bottomSheetLayout.setVisibility(View.VISIBLE);
                    homeOnline.setVisibility(View.GONE);
                    toolbar_title.setText("Offline");
                }
            }
        });
    }

    private List<String> numberWord(){
        List<String> word = new ArrayList<>();
        word.add("one");
        word.add("two");
        word.add("three");
        word.add("four");
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
                Toast.makeText(this, "Wallet Coming Soon...", Toast.LENGTH_SHORT).show();
//                startActivity(i);
//                finish();
                break;
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