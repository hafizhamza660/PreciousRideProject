package com.example.task;

import static com.example.task.Session.SaveSharedPreference.clearClientId;
import static com.example.task.Session.SaveSharedPreference.getClientId;
import static com.example.task.Session.SaveSharedPreference.getFirstName;
import static com.example.task.Session.SaveSharedPreference.getImageUrl;
import static com.example.task.Session.SaveSharedPreference.getInterCity;
import static com.example.task.Session.SaveSharedPreference.getLocaitonLng;
import static com.example.task.Session.SaveSharedPreference.getLocationLat;

import static com.example.task.Session.SaveSharedPreference.getStatus;
import static com.example.task.Session.SaveSharedPreference.setLocaionLat;
import static com.example.task.Session.SaveSharedPreference.setLocaitonLng;
import static com.example.task.Session.SaveSharedPreference.setRange;
import static com.example.task.Session.SaveSharedPreference.setStatus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.Dialog.Rules;
import com.example.task.LogoutStatusFiles.RequestLogoutStatus;
import com.example.task.LogoutStatusFiles.ResponseLogoutStatus;
import com.example.task.RangeFiles.RequestRange;
import com.example.task.RangeFiles.ResponseRange;
import com.example.task.RideRequestedHistory.Data;
import com.example.task.RideRequestedHistory.RideRequestHistoryResponse;
import com.example.task.StatusFiles.RequestStatus;
import com.example.task.StatusFiles.ResponseStatus;
import com.example.task.adapters.StackAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.wenchao.cardstack.CardStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeOffline extends AppCompatActivity implements OnMapReadyCallback {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer, nvDrawer2;

    Switch switchbtn;
    LinearLayout homeOnline;
    RelativeLayout homeOffline;
    CardStack stackView;
    TextView toolbar_title;

    ImageView minus_range, add_range, driver_image;
    TextView km_range, id_name, driver_name;
    Context context;
    String status_s, driver_id;

    public static final String TAG = "HomeONline";
    //    public int counter;
    BottomSheetBehavior bottomSheetBehavior;
    ConstraintLayout bottomSheetLayout;
//    private static final long START_TIME_IN_MILLIS = 600000;
//    private CountDownTimer mCountDownTimer;
//    private boolean mTimerRunning;
//    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    float counter = 1;
    String val;
    List<Data> data;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    FloatingActionButton floatingActionButton, floatingActionButton_online;
    int PLACE_PICKER_REQUEST = 1;


//    RecyclerView recyclerViewRideRequest;
//    LinearLayoutManager linearLayoutManager;
//    private RideRequestListAdapter rideRequestListAdapter;


    private GoogleMap map;
    private CameraPosition cameraPosition;

    // The entry point to the Places API.
    private PlacesClient placesClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    // [START maps_current_place_state_keys]
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    // [END maps_current_place_state_keys]

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private LatLng[] likelyPlaceLatLngs;
    String currentName, currentLat, currentLng;
    String dropName, dropLat, dropLng;
    // [START maps_current_place_on_create]

    private List<Place.Field> fields;
    final int place_picker_req_code = 1;
    SupportMapFragment mapFragment;
    double radiusvalue;
    static List<Circle> mCircleList;
    private AVLoadingIndicatorView avi;
    CoordinatorLayout parentLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreat");

//        if (SaveSharedPreference.getClientId(HomeOffline.this).length() == 0) {
//            startActivity(new Intent(HomeOffline.this, WelcomeScreen.class));
//            finish();
//        } else {
        // Stay at the current activity.
        setContentView(R.layout.activity_home_offline);
        context = this;
        Configuration config = getResources().getConfiguration();
//            if (isNetworkConnected()) {
//                Toast.makeText(this, "Internet connected successfully", Toast.LENGTH_SHORT).show();
//                if (locationServicesEnabled(context)) {
//                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                startActivity(new Intent(HomeOffline.this, SetupGPSLocationActivity.class));
//            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }


        driver_id = getClientId(context);

        /*ToolBar With NavBar*/
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
//        defaultScreen();
        val = getInterCity(context);

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
        View hView = nvDrawer.getHeaderView(0);
        TextView drivername = (TextView) hView.findViewById(R.id.driver_name);
        ImageView profile_dp = (ImageView) hView.findViewById(R.id.user_image);
        drivername.setText(getFirstName(context));


//            nvDrawer2 = (NavigationView) findViewById(R.id.nvView2);


//            if (val=="0") {
//                Toast.makeText(this, "0 Val", Toast.LENGTH_SHORT).show();
//                nvDrawer.setVisibility(View.VISIBLE);
//                nvDrawer2.setVisibility(View.GONE);
//                // Setup drawer view
        setupDrawerContent(nvDrawer);

        /*ToolBar With NavBar End*/
        nvDrawer.getMenu().getItem(0).setChecked(true);
//            Toast.makeText(this, "Value: "+val, Toast.LENGTH_SHORT).show();
//            }
//            else if (val=="1")
//            {
//                Toast.makeText(this, "1 Val", Toast.LENGTH_SHORT).show();
//
//                nvDrawer.setVisibility(View.GONE);
//                nvDrawer2.setVisibility(View.VISIBLE);
//                // Setup drawer view
//                setupDrawerContent(nvDrawer2);
//
//                /*ToolBar With NavBar End*/
//                nvDrawer2.getMenu().getItem(0).setChecked(true);
//            }
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton_online = findViewById(R.id.floatingActionButton_online);
        driver_image = findViewById(R.id.driver_image);
        parentLayout = findViewById(R.id.parentLayout);


        if (getImageUrl(context).equals("0") || getImageUrl(context).equals("123.jpg")) {

        } else {
            String url = "http://precious-ride.ragzon.com/" + getImageUrl(context);
            Picasso.get().load(url).into(profile_dp);
            Picasso.get().load(url).into(driver_image);
        }

//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();


        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
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

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });


        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        // [END maps_current_place_on_create_save_instance_state]
        // [END_EXCLUDE]

        // Retrieve the content view that renders the map.


        // [START_EXCLUDE silent]
        // Construct a PlacesClient
        Places.initialize(getApplicationContext(), "AIzaSyAd8q-fqcHslANRJ3WZxR5cMYY1CgtBe9I");
        placesClient = Places.createClient(this);

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Build the map.
        // [START maps_current_place_map_fragment]
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // [END maps_current_place_map_fragment]
        // [END_EXCLUDE]


//        PlaceListAdapter placeListAdapter = new PlaceListAdapter(HomeOffline.this, place_name);
//        recyclerViewplace.setAdapter(placeListAdapter);


        switchbtn = findViewById(R.id.switchbtn);
        homeOffline = findViewById(R.id.homeOffline);
        homeOnline = findViewById(R.id.homeOnline);
        data = new ArrayList<>();

        stackView = findViewById(R.id.stackview);
        toolbar_title = findViewById(R.id.toolbar_title);


        minus_range = findViewById(R.id.minus_range);
        add_range = findViewById(R.id.add_range);
        km_range = findViewById(R.id.km_range);
        id_name = findViewById(R.id.id_name);
//        driver_name = findViewById(R.id.driver_name);


//        km_range.setText(counter + " KM");

//            Toast.makeText(this, "" + "\n" + getFirstName(context) + "\n" + getCity(context) + "\n" + getLastName(context) + "\n" + getEmail(context) + "\n" + getClientId(context) + "\n" + getMobileNumber(context), Toast.LENGTH_SHORT).show();


        id_name.setText(getFirstName(context));

//        counter= Double.parseDouble(getRange(context));
        rideRange(String.valueOf(counter), "nothing");
        minus_range.setEnabled(false);
        minus_range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnim();
                minus_range.setEnabled(false);
                add_range.setEnabled(false);
                counter--;
                km_range.setText(counter + " KM");
                if (counter < 2) {
                    minus_range.setEnabled(false);
                    rideRange(String.valueOf(counter), "minus");

                } else {
                    rideRange(String.valueOf(counter), "minus");

                }

            }
        });
        add_range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnim();
                minus_range.setEnabled(false);
                add_range.setEnabled(false);
                counter++;

//                km_range.setText(counter + " KM");
                if (counter > 1) {
                    minus_range.setEnabled(true);
                    rideRange(String.valueOf(counter), "add");
//                    double lat = Double.parseDouble(currentLat);
//                    double lng = Double.parseDouble(currentLng);
//                    LatLng latLng = new LatLng(lat, lng);
//                    createcircle(latLng,counter);

                }
            }
        });
//        String nameDriver =getFirstName(context);
//        driver_name.setText(nameDriver);
        status_s = getStatus(context);
//            Toast.makeText(this, "Status: "+status_s, Toast.LENGTH_SHORT).show();
        if (status_s.equals("0")) {
            switchbtn.setChecked(false);
        } else if (status_s.equals("1")) {
            switchbtn.setChecked(true);
            homeOffline.setVisibility(View.GONE);
            bottomSheetLayout.setVisibility(View.GONE);
            homeOnline.setVisibility(View.VISIBLE);
            toolbar_title.setText("Online");
            data.clear();
            ridehistoryrequest();
            floatingActionButton.setVisibility(View.GONE);
//            floatingActionButton_online.setVisibility(View.VISIBLE);
        }

//        recyclerViewRideRequest = findViewById(R.id.recycler_view_rideRequest);
//        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//        recyclerViewRideRequest.setLayoutManager(linearLayoutManager);

        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setStatus(context, "1");
                    status_s = "1";
                    status();
                    Rules exitDialog = new Rules(HomeOffline.this);
                    exitDialog.show();
                    Window window = exitDialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    // The toggle is enabled
                    long duration = TimeUnit.MINUTES.toMillis(1);

                    homeOffline.setVisibility(View.GONE);
                    bottomSheetLayout.setVisibility(View.GONE);
                    homeOnline.setVisibility(View.VISIBLE);
                    toolbar_title.setText("Online");
//                        StackAdapter adapter = new StackAdapter(numberWord(), HomeOffline.this, R.layout.item_stack);
//
//                        stackView.setAdapter(adapter);
//                        YourListner yourListner = new YourListner();
//                        stackView.setListener(yourListner);
                    ridehistoryrequest();
                    floatingActionButton.setVisibility(View.GONE);
//                    floatingActionButton_online.setVisibility(View.VISIBLE);
                } else {
                    setStatus(context, "0");
                    status_s = "0";
                    status();
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


//            startService(new Intent(HomeOffline.this, ServiceClass.class));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDeviceLocation();
            }
        });


    }
//    }


    public void createcircle(LatLng latLng, double radius) {
        radiusvalue = radius * 1000;

        if (mCircleList == null) {
            mCircleList = new ArrayList<Circle>();
        }

        CircleOptions circleOptions = new CircleOptions()
                .center(latLng)   //set center
                .radius(radiusvalue)   //set radius in meters
                .fillColor(R.color.mapfillcolor)  //default
                .strokeColor(Color.BLUE)
                .strokeWidth(5);

        if (mCircleList.size() < 8) {
            Circle mCircle = map.addCircle(circleOptions);
            mCircleList.add(mCircle);

        }
        for (int i = 0; i <= mCircleList.size() - 2; i++) {
            Circle mCircle = mCircleList.get(i);
            mCircle.remove();
        }


//        CircleOptions mapCircle=new CircleOptions()
//                .center(latLng)
//                .radius(radiusvalue)
//                .strokeColor(Color.RED)
//                .fillColor(Color.LTGRAY);
//        map.addCircle(mapCircle);
//        mapFragment.getMapAsync(this);
    }

    public void reducecircle(LatLng latLng) {

        for (int i = 0; i <= mCircleList.size() - 1; i++) {
            Circle mCircle = mCircleList.get(i);
            mCircle.remove();
        }


        mCircleList.clear();
        createcircle(latLng, counter);
//        radiusvalue= radiusvalue-1000;
//        map.
//        CircleOptions mapCircle=new CircleOptions()
//                .center(latLng)
//                .radius(radiusvalue)
//                .strokeColor(Color.RED)
//                .fillColor(Color.LTGRAY);
//
//                map.addCircle(mapCircle);
//        mapFragment.getMapAsync(this);
    }

    public void removelastOnecircle() {

        for (int i = 0; i <= mCircleList.size() - 1; i++) {
            Circle mCircle = mCircleList.get(i);
            mCircle.remove();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onPause() {
        super.onPause();
        id_name.setText("");
        driver_id = getClientId(context);


    }


    public void ridehistoryrequest() {


        Call<RideRequestHistoryResponse> signUpResponseCall = ApiClass.getUserServiceAPI().userGetDriverRequestedRideRequests();
        signUpResponseCall.enqueue(new Callback<RideRequestHistoryResponse>() {
            @Override
            public void onResponse(Call<RideRequestHistoryResponse> call, Response<RideRequestHistoryResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(TravelRequest.this, ""+response.body().data, Toast.LENGTH_LONG).show();
//                    Log.d(TAG,"Data : "+response.body().data.get(0).id);
                    if (response.body().status.equals("0")) {

                    } else {
//                        rideRequestListAdapter = new RideRequestListAdapter(HomeOffline.this, context, response.body().data);
//                        recyclerViewRideRequest.setAdapter(rideRequestListAdapter);


//                        data.addAll(response.body().data);
                        for (int i = 0; i < response.body().data.size(); i++) {

                            double lat1, lat2, long1, long2, total;
                            lat1 = Float.parseFloat(getLocationLat(context));
                            long1 = Float.parseFloat(getLocaitonLng(context));
                            lat2 = Double.parseDouble(response.body().data.get(i).start_lat);
                            long2 = Double.parseDouble(response.body().data.get(i).start_long);
                            total = getKmFromLatLong(lat1, long1, lat2, long2);
                            if (total < 5) {
                                data.add(response.body().data.get(i));
                            }
                        }

                        StackAdapter adapter = new StackAdapter(data, HomeOffline.this, R.layout.item_stack);

                        stackView.setAdapter(adapter);
                        YourListner yourListner = new YourListner();
                        stackView.setListener(yourListner);
                    }
//
                } else {
//                    Toast.makeText(TravelRequest.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RideRequestHistoryResponse> call, Throwable t) {
                stopAnim();
                Snackbar snackbar = Snackbar
                        .make(parentLayout, "Please change your internet connection and try again", Snackbar.LENGTH_LONG);
                snackbar.show();
//                Toast.makeText(TravelRequest.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


    public void rideRange(String range, String typebtn) {
        RequestRange requestRange = new RequestRange();
        requestRange.setDriver_id(getClientId(context));
        requestRange.setRange(range);


        Call<ResponseRange> responseRangeCall = ApiClass.getUserServiceAPI().userRangeUpdate(requestRange);
        responseRangeCall.enqueue(new Callback<ResponseRange>() {
            @Override
            public void onResponse(Call<ResponseRange> call, Response<ResponseRange> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (typebtn.equals("add")) {
                            counter = Float.parseFloat(response.body().range);
                            setRange(context, String.valueOf(counter));
                            km_range.setText(counter + " KM");
                            double lat = Double.parseDouble(currentLat);
                            double lng = Double.parseDouble(currentLng);
                            LatLng latLng = new LatLng(lat, lng);
                            createcircle(latLng, counter);
                            if (counter < 2) {


                                minus_range.setEnabled(false);
                                add_range.setEnabled(true);
                            } else {
                                minus_range.setEnabled(true);
                                add_range.setEnabled(true);
                            }
                            stopAnim();

                        } else if (typebtn.equals("minus")) {
                            counter = Float.parseFloat(response.body().range);
                            setRange(context, String.valueOf(counter));
                            km_range.setText(counter + " KM");
                            double lat = Double.parseDouble(currentLat);
                            double lng = Double.parseDouble(currentLng);
                            LatLng latLng = new LatLng(lat, lng);
                            reducecircle(latLng);
                            if (counter < 2) {
                                minus_range.setEnabled(false);
                                add_range.setEnabled(true);
                            } else {
                                minus_range.setEnabled(true);
                                add_range.setEnabled(true);
                            }
                            stopAnim();
                        } else {
                            counter = Float.parseFloat(response.body().range);
                            setRange(context, String.valueOf(counter));
                            km_range.setText(counter + " KM");
                            double lat = Double.parseDouble(currentLat);
                            double lng = Double.parseDouble(currentLng);
                            LatLng latLng = new LatLng(lat, lng);
                            createcircle(latLng, counter);
                            if (counter < 2) {
                                minus_range.setEnabled(false);
                                add_range.setEnabled(true);
                            } else {
                                minus_range.setEnabled(true);
                                add_range.setEnabled(true);
                            }
                            stopAnim();
                        }

                    } else {

                    }
                } else {
//                    Toast.makeText(TravelRequest.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRange> call, Throwable t) {
                stopAnim();
                Snackbar snackbar = Snackbar
                        .make(parentLayout, "Please change your internet connection and try again", Snackbar.LENGTH_LONG);
                snackbar.show();

                Log.d("TAG", "Error " + t);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        nvDrawer.getMenu().getItem(0).setChecked(true);
        driver_id = getClientId(context);
        id_name.setText(getFirstName(context));
        val = getInterCity(context);
        status_s = getStatus(context);
    }

    public static boolean locationServicesEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean net_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            Log.e(TAG, "Exception gps_enabled");
        }

        try {
            net_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            Log.e(TAG, "Exception network_enabled");
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
        Log.d(TAG, "onStart");
        driver_id = getClientId(context);
//        stopService(new Intent(HomeOffline.this, FloatingViewService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        driver_id = getClientId(context);

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

    }


    private List<String> numberWord() {
        List<String> word = new ArrayList<>();
        word.add("one");
        return word;
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_open, R.string.navigation_close);
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
        switch (menuItem.getItemId()) {
            case R.id.home:
//                i = new Intent(HomeOffline.this, HomeOffline.class);
//                startActivity(i);
                Toast.makeText(this, "Already in that tab", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_wallet:
                i = new Intent(HomeOffline.this, Wallet.class);
                startActivity(i);
                break;
            case R.id.travel_request:
                if (status_s.equals("0")) {
                    nvDrawer.getMenu().getItem(0).setChecked(true);
                    Toast.makeText(this, "Go Online First", Toast.LENGTH_SHORT).show();
                } else if (status_s.equals("1")) {
                    i = new Intent(HomeOffline.this, TravelRequest.class);
                    startActivity(i);
                }
                break;
            case R.id.inter_city:
                if (val.equals("0")) {
                    nvDrawer.getMenu().getItem(0).setChecked(true);
                    Toast.makeText(this, "Go to setting and switch on the Inter-State", Toast.LENGTH_SHORT).show();
                } else if (val.equals("1")) {
                    i = new Intent(HomeOffline.this, InterCityRequests.class);
                    startActivity(i);
                }
                break;
            case R.id.history:
                i = new Intent(HomeOffline.this, History.class);
                startActivity(i);
                break;
            case R.id.notification_toolbar:
                i = new Intent(HomeOffline.this, Notifications.class);
                startActivity(i);
                break;
            case R.id.invite_friends:
                i = new Intent(HomeOffline.this, InviteFriends.class);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(HomeOffline.this, Setting.class);
                startActivity(i);
                break;
            case R.id.campaign_menu:
                i = new Intent(HomeOffline.this, CampaignView.class);
                startActivity(i);
                break;

            case R.id.logout:
                logoutstatus();
                clearClientId(context);
                i = new Intent(HomeOffline.this, WelcomeScreen.class);
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

            return (distance > 300) ? true : false;
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


    public void status() {
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setId(driver_id);


        Call<ResponseStatus> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverStatus(requestStatus);
        signUpResponseCall.enqueue(new Callback<ResponseStatus>() {
            @Override
            public void onResponse(Call<ResponseStatus> call, Response<ResponseStatus> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(HomeOffline.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("1") && status_s.equals("1")) {
//                        Toast.makeText(HomeOffline.this, "You are online", Toast.LENGTH_LONG).show();
//                        Rules exitDialog = new Rules(HomeOffline.this);
//                        exitDialog.show();
//                        Window window = exitDialog.getWindow();
//                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        // The toggle is enabled
//                        long duration = TimeUnit.MINUTES.toMillis(1);
//
//                        homeOffline.setVisibility(View.GONE);
//                        bottomSheetLayout.setVisibility(View.GONE);
//                        homeOnline.setVisibility(View.VISIBLE);
//                        toolbar_title.setText("Online");
//                        StackAdapter adapter = new StackAdapter(numberWord(), HomeOffline.this, R.layout.item_stack);
//
//                        stackView.setAdapter(adapter);
//                        YourListner yourListner = new YourListner();
//                        stackView.setListener(yourListner);
//                        floatingActionButton.setVisibility(View.GONE);
//                        floatingActionButton_online.setVisibility(View.VISIBLE);
                    } else {
//                        Toast.makeText(HomeOffline.this, "You are offline", Toast.LENGTH_LONG).show();
                        // The toggle is disabled
//                        homeOffline.setVisibility(View.VISIBLE);
//                        bottomSheetLayout.setVisibility(View.VISIBLE);
//                        homeOnline.setVisibility(View.GONE);
//                        toolbar_title.setText("Offline");
////                    Log.d("FloatingParam",""+params.getAnchorId());
//                        floatingActionButton_online.setVisibility(View.GONE);
//                        floatingActionButton.setVisibility(View.VISIBLE);
                    }

                } else {
//                    Toast.makeText(HomeOffline.this, "Request Denied", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatus> call, Throwable t) {
                stopAnim();
                Snackbar snackbar = Snackbar
                        .make(parentLayout, "Please change your internet connection and try again", Snackbar.LENGTH_LONG);
                snackbar.show();
//                Toast.makeText(HomeOffline.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


    public void logoutstatus() {
        RequestLogoutStatus requestLogoutStatus = new RequestLogoutStatus();
        requestLogoutStatus.setDriver_id(driver_id);


        Call<ResponseLogoutStatus> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverLogoutStatus(requestLogoutStatus);
        signUpResponseCall.enqueue(new Callback<ResponseLogoutStatus>() {
            @Override
            public void onResponse(Call<ResponseLogoutStatus> call, Response<ResponseLogoutStatus> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(HomeOffline.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().message.equals("1")) {
                        Toast.makeText(HomeOffline.this, "You are online", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(HomeOffline.this, "Driver Logout Successfully", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(HomeOffline.this, "Request Denied", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogoutStatus> call, Throwable t) {
//                Toast.makeText(HomeOffline.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }


    //Maps
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }
    // [END maps_current_place_on_save_instance_state]


    // [START maps_current_place_on_map_ready]
    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        // [START_EXCLUDE]
        // [START map_current_place_set_info_window_adapter]
        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        this.map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (FrameLayout) findViewById(R.id.map), false);

                TextView title = infoWindow.findViewById(R.id.title);
                title.setText(marker.getTitle());

                TextView snippet = infoWindow.findViewById(R.id.snippet);
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });
        // [END map_current_place_set_info_window_adapter]

        // Prompt the user for permission.
        getLocationPermission();
        // [END_EXCLUDE]

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }
    // [END maps_current_place_on_map_ready]

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    // [START maps_current_place_get_device_location]
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                Double Lat = lastKnownLocation.getLatitude();
                                Double Long = lastKnownLocation.getLongitude();
                                currentName = getStringAddres(Lat, Long);

//                                pickuplocation.setText(currentName);
                                currentLat = Lat.toString();
                                currentLng = Long.toString();
                                setLocaionLat(context, currentLat);
                                setLocaitonLng(context, currentLng);
                                if (lastKnownLocation != null) {

                                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                            new LatLng(lastKnownLocation.getLatitude(),
                                                    lastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                                }
                            } else {
                                Log.d(TAG, "Current location is null. Using defaults.");
                                Log.e(TAG, "Exception: %s", task.getException());
                                map.moveCamera(CameraUpdateFactory
                                        .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                                map.getUiSettings().setMyLocationButtonEnabled(false);
                            }
                        } else {

                        }

                    }
                });
            } else {
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }
    // [END maps_current_place_get_device_location]

    /**
     * Prompts the user for permission to use the device location.
     */
    // [START maps_current_place_location_permission]
    private boolean getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
            return true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//            locationPermissionGranted = false;
            return false;
        }
//        return false;
    }
    // [END maps_current_place_location_permission]

    /**
     * Handles the result of the request for location permissions.
     */
    // [START maps_current_place_on_request_permissions_result]
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                    updateLocationUI();

                }
            }
        }


    }
    // [END maps_current_place_on_request_permissions_result]

    /**
     * Prompts the user to select the current place from a list of likely places, and shows the
     * current place on the map - provided the user has granted location permission.
     */
    // [START maps_current_place_show_current_place]
    private void showCurrentPlace() {
        if (map == null) {
            return;
        }

        if (locationPermissionGranted) {
            // Use fields to define the data types to return.
            List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS,
                    Place.Field.LAT_LNG);

            // Use the builder to create a FindCurrentPlaceRequest.
            FindCurrentPlaceRequest request =
                    FindCurrentPlaceRequest.newInstance(placeFields);

            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
            @SuppressWarnings("MissingPermission") final Task<FindCurrentPlaceResponse> placeResult =
                    placesClient.findCurrentPlace(request);
            placeResult.addOnCompleteListener(new OnCompleteListener<FindCurrentPlaceResponse>() {
                @Override
                public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        FindCurrentPlaceResponse likelyPlaces = task.getResult();

                        // Set the count, handling cases where less than 5 entries are returned.
                        int count;
                        if (likelyPlaces.getPlaceLikelihoods().size() < M_MAX_ENTRIES) {
                            count = likelyPlaces.getPlaceLikelihoods().size();
                        } else {
                            count = M_MAX_ENTRIES;
                        }

                        int i = 0;
                        likelyPlaceNames = new String[count];
                        likelyPlaceAddresses = new String[count];
                        likelyPlaceAttributions = new List[count];
                        likelyPlaceLatLngs = new LatLng[count];

                        for (PlaceLikelihood placeLikelihood : likelyPlaces.getPlaceLikelihoods()) {
                            // Build a list of likely places to show the user.
                            likelyPlaceNames[i] = placeLikelihood.getPlace().getName();
                            likelyPlaceAddresses[i] = placeLikelihood.getPlace().getAddress();
                            likelyPlaceAttributions[i] = placeLikelihood.getPlace()
                                    .getAttributions();
                            likelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                            i++;
                            if (i > (count - 1)) {
                                break;
                            }
                        }

                        // Show a dialog offering the user the list of likely places, and add a
                        // marker at the selected place.
                        HomeOffline.this.openPlacesDialog();
                    } else {
                        Log.e(TAG, "Exception: %s", task.getException());
                    }
                }
            });
        } else {
            // The user has not granted permission.
            Log.i(TAG, "The user did not grant location permission.");

            // Add a default marker, because the user hasn't selected a place.
            map.addMarker(new MarkerOptions()
                    .title(getString(R.string.default_info_title))
                    .position(defaultLocation)
                    .snippet(getString(R.string.default_info_snippet)));

            // Prompt the user for permission.
            getLocationPermission();

        }
    }
    // [END maps_current_place_show_current_place]

    /**
     * Displays a form allowing the user to select a place from a list of likely places.
     */
    // [START maps_current_place_open_places_dialog]
    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = likelyPlaceLatLngs[which];
                String markerSnippet = likelyPlaceAddresses[which];
                if (likelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + likelyPlaceAttributions[which];
                }

                // Add a marker for the selected place, with an info window
                // showing information about that place.
                map.addMarker(new MarkerOptions()
                        .title(likelyPlaceNames[which])
                        .position(markerLatLng)
                        .snippet(markerSnippet));

                // Position the map's camera at the location of the marker.
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        DEFAULT_ZOOM));
            }
        };

        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.pick_place)
                .setItems(likelyPlaceNames, listener)
                .show();
    }
    // [END maps_current_place_open_places_dialog]

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    // [START maps_current_place_update_location_ui]
    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);


            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();


            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    // [END maps_current_place_update_location_ui]

    public String getStringAddres(Double lat, Double lng) {
        String address = "";
        String city = "";
        Geocoder geocoder;
        List<Address> addresses;

        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address + "  " + city;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case place_picker_req_code:
                Place place = Autocomplete.getPlaceFromIntent(data);


//                if (resultCode == 0) {
//
//                } else {
//                    place = Autocomplete.getPlaceFromIntent(data);
//                    String name;
//                    LatLng latLng;
//                    name = place.getName();
//                    latLng = place.getLatLng();
//                    if (locaitonGetName.equals("pickup")) {
//
//                        currentLat = String.valueOf(latLng.latitude);
//                        currentLng = String.valueOf(latLng.longitude);
//                        currentName = name;
//                        pickuplocation.setText(name);
//                    } else if (locaitonGetName.equals("dropoff")) {
//
//                        dropLat = String.valueOf(latLng.latitude);
//                        dropLng = String.valueOf(latLng.longitude);
//                        dropName = name;
//                        chossedropoff.setText(name);
//                    }


                break;
        }

    }


    void startAnim() {
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim() {
        avi.hide();
        // or avi.smoothToHide();
    }

    public static double getKmFromLatLong(double lat1, double lng1, double lat2, double lng2) {
        Location loc1 = new Location("");
        loc1.setLatitude(lat1);
        loc1.setLongitude(lng1);
        Location loc2 = new Location("");
        loc2.setLatitude(lat2);
        loc2.setLongitude(lng2);
        double distanceInMeters = loc1.distanceTo(loc2);
        return distanceInMeters / 1000;
    }


}

