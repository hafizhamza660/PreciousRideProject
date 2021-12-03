package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.task.Models.LocationModel;
import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.DataSendFiles.RequestDataSend;
import com.example.task.DataSendFiles.ResponseDataSend;
import com.example.task.Directionhelpers.FetchURL;
import com.example.task.Directionhelpers.TaskLoadedCallback;

import com.example.task.adapters.RideStepsListAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeOnlineBookingDetailsGotopickup extends AppCompatActivity implements LocationListener, OnMapReadyCallback, TaskLoadedCallback {
    public static final String TAG = "Details";
    BottomSheetBehavior bottomSheetBehavior;
    ConstraintLayout bottomSheetLayout;
    FloatingActionButton floatingMessage;
    String driver_id, client_id, end_long, start_lat, start_long, end_lat,client_name;
    double flat, flng, tlat, tlng,live_lat,live_lng,loc_lat,loc_lng;

    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    Context context;
    Button dropoff_btn;

    //Current Location
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    MapFragment mapFragment;

    TextView pickup_location, duration_txt, distance_txt;
//    List<RideDataTable> rideDataTables;


    RecyclerView recyclerViewRideRequest;
    LinearLayoutManager linearLayoutManager;
    private RideStepsListAdapter rideStepsListAdapter;
    int id_To_Update = 0;
    TextView direction_up,distance_up;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CoordinatorLayout parentLayout;
    CardView start_ride_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_online_booking_details_gotopickup);

        context = this;

        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        floatingMessage = findViewById(R.id.floatingMessage);
        dropoff_btn = findViewById(R.id.dropoff_btn);
        pickup_location = findViewById(R.id.pickup_location);
        duration_txt = findViewById(R.id.duration);
        distance_txt = findViewById(R.id.distance);
        direction_up = findViewById(R.id.direction_up);
        distance_up = findViewById(R.id.distance_up);
        parentLayout = findViewById(R.id.parentLayout);
        start_ride_btn = findViewById(R.id.start_ride_btn);


        Intent intent = getIntent();
        driver_id = intent.getStringExtra("driver_id");
        client_id = intent.getStringExtra("client_id");
        start_lat = intent.getStringExtra("start_lat");
        start_long = intent.getStringExtra("start_long");
        end_lat = intent.getStringExtra("end_lat");
        end_long = intent.getStringExtra("end_long");
        client_name = intent.getStringExtra("client_name");


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        flat = Double.parseDouble(start_lat);
        flng = Double.parseDouble(start_long);
        tlat = Double.parseDouble(end_lat);
        tlng = Double.parseDouble(end_long);


        loc_lat = flat;
        loc_lng = flng;
//        addRideRequest(startLat, startLng, endLat, endLng);

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

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


        floatingMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeOnlineBookingDetailsGotopickup.this, Message.class);
                intent.putExtra("client_id", client_id);
                intent.putExtra("driver_id", driver_id);
                intent.putExtra("client_name", client_name);
                startActivity(intent);
            }
        });
        dropoff_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + flat + "," + flng + "&daddr=" + tlat + "," + tlng));
                startActivity(intent);
            }
        });
        start_ride_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loc_lat = tlat;
                loc_lng = tlng;
                start_ride_btn.setVisibility(View.GONE);
            }
        });

        recyclerViewRideRequest = findViewById(R.id.recycler_view_rideSteps);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewRideRequest.setLayoutManager(linearLayoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Location");


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new  Runnable() {

                    public void run() {
                        double distance = getKmFromLatLong(loc_lat, loc_lng, live_lat, live_lng);


                        if (distance<0.01){
                            btnshow();

                        }



                        String pickup = getStringAddres(flat, flng);

                        String dropoff = getStringAddres(tlat, tlng);

                        place1 = new MarkerOptions().position(new LatLng(loc_lat, loc_lng)).title(pickup);
                        place2 = new MarkerOptions().position(new LatLng(live_lat, live_lng)).title(dropoff);


                        new FetchURL(HomeOnlineBookingDetailsGotopickup.this).execute(getUrl(place2.getPosition(), place1.getPosition(), "driving"), "driving");


                        JSONObject object = (JSONObject) getPreferenceObjectJson(context, "mapObject");
                        datasend(object);

                    }
                });


            }
        }, 0, 1000);
    }

    public void back_button(View view) {
//        onBackPressed();
    }
    public void btnshow()
    {
        start_ride_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    public String getStringAddres(Double lat, Double lng) {
        String address = "";
        String city = "";
        Geocoder geocoder;
        List<Address> addresses;

        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address + "  " + city;
    }


    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");


        mMap.addMarker(place1);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

//the include method will calculate the min and max bound.
        builder.include(place1.getPosition());
        builder.include(place2.getPosition());
//        builder.include(marker3.getPosition());
//        builder.include(marker4.getPosition());

        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.30); // offset from edges of the map 10% of screen

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        mMap.animateCamera(cu);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
//        mMap.set

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                            new LatLng(place2.getPosition().latitude,
//                                                    place2.getPosition().latitude), padding));
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        Log.d("DrivingData",""+parameters);
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }



    @Override
    public void onLocationChanged(Location location) {


        live_lat=location.getLatitude();
        live_lng=location.getLongitude();
        mapFragment.getMapAsync(this);

        LocationModel locationModel = new LocationModel(String.valueOf(live_lat),String.valueOf(live_lng));
        databaseReference.child(getClientId(context)).setValue(locationModel);


//        getTasks();

//        Log.d("Ridedatatable","Ride Data: "+rideDataTables.get(0).getEnd_address());
//        pickup_location.setText(rideDataTablesress());
//        Cursor data = mydb.getData(id_To_Update);

//        pickup_location.setText(data.getColumnIndex("end_address"));



    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");

//        pickup_location.setText(provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

//
//    private void getTasks() {
//        class GetTasks extends AsyncTask<Void, Void, List<RideDataTable>> {
//
//            @Override
//            protected List<RideDataTable> doInBackground(Void... voids) {
//                List<RideDataTable> taskList = DatabaseClient
//                        .getInstance(getApplicationContext())
//                        .getAppDatabase()
//                        .rideDao()
//                        .getAll();
//                return taskList;
//            }
//
//            @Override
//            protected void onPostExecute(List<RideDataTable> tasks) {
//                super.onPostExecute(tasks);
//                Log.d("Ridedatatable","Ride Data: "+tasks);
////                Log.d("Ridedatatable","Ride Data: "+tasks.get(0).getEnd_address());
////                rideDataTables=tasks;
//
//            }
//        }
//
//        GetTasks gt = new GetTasks();
//        gt.execute();
//    }



    static public Object getPreferenceObjectJson(Context c,String key) {

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                c.getApplicationContext());
        /**** get user data    *****/
        String json = appSharedPrefs.getString(key, "");
        Gson gson=new Gson();
        JSONObject object = gson.fromJson(json,JSONObject.class);
//        JsonObjectC selectedUser=gson.fromJson(json, JsonObjectC.class);
        return  object;
    }


    public void datasend(JSONObject object) {
        RequestDataSend requestDataSend = new RequestDataSend();
        requestDataSend.setObject(object);
//        signUpRequest.setI_code(invite_code);


        Call<ResponseDataSend> signUpResponseCall = ApiClass.getUserServiceAPI().userDriverApiObject(requestDataSend);
        signUpResponseCall.enqueue(new Callback<ResponseDataSend>() {
            @Override
            public void onResponse(Call<ResponseDataSend> call, Response<ResponseDataSend> response) {
                if (response.isSuccessful()) {

                    ResponseDataSend responseDataSend = new ResponseDataSend();
//                    responseDataSend.setEnd_address(response.body().end_address);

                    Log.d("TAGtotal_distance",""+response.body().total_distance.text);
                    Log.d("TAGduration",""+response.body().duration.text);
//                    Log.d("TAGduration",""+response.body().duration.get(0));
                    Log.d("TAGstart_address",""+response.body().start_address);
                    Log.d("TAGend_address",""+response.body().end_address);

                    String total_distance=response.body().total_distance.text;
                    String duration=response.body().duration.text;
                    String start_address=response.body().start_address;
                    String end_address=response.body().end_address;

                    pickup_location.setText(end_address);
                    duration_txt.setText(duration);
                    distance_txt.setText(total_distance);

                    Log.d("DataSteps",""+response.body().steps.get(0).nameValuePairs.html_instructions);


                    direction_up.setText(Html.fromHtml(response.body().steps.get(0).nameValuePairs.html_instructions));
                    distance_up.setText(Html.fromHtml(response.body().steps.get(0).nameValuePairs.distance.nameValuePairs.text));

//                    holder.route.setText(Html.fromHtml(stepslist.nameValuePairs.html_instructions));
//                    holder.distance_route.setText(stepslist.nameValuePairs.distance.nameValuePairs.text);

                    rideStepsListAdapter = new RideStepsListAdapter( context, response.body().steps);
                    recyclerViewRideRequest.setAdapter(rideStepsListAdapter);

//                    saveTask(total_distance,duration,start_address,end_address);



                } else {
//                    Toast.makeText(SignUp.this, "API not Hit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataSend> call, Throwable t) {
                Snackbar snackbar = Snackbar
                        .make(parentLayout, "Please change your internet connection and try again", Snackbar.LENGTH_LONG);
                snackbar.show();
//                Toast.makeText(SignUp.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
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