package com.example.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.Floating.FloatingViewService;
import com.example.task.Service.InternetSpeedService;
import com.example.task.Session.SaveSharedPreference;

public class SetupGPSLocationActivity extends AppCompatActivity {

    Button btn_GetStarted;
    TextView text_Skip;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_gpslocation);

        if (SaveSharedPreference.getClientId(SetupGPSLocationActivity.this).length() == 0) {
            startActivity(new Intent(SetupGPSLocationActivity.this, WelcomeScreen.class));
            finish();
        } else {
            btn_GetStarted = findViewById(R.id.btn_GetStarted);
            text_Skip = findViewById(R.id.text_Skip);

            if (statusCheck()) {

                Intent intent = new Intent(SetupGPSLocationActivity.this, HomeOffline.class);
                startActivity(intent);
                finish();
            }


            btn_GetStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent intent = new Intent(SetupGPSLocationActivity.this, SignIn.class);
//                startActivity(intent);
                    if (statusCheck()) {
                        Intent intent = new Intent(SetupGPSLocationActivity.this, HomeOffline.class);
                        startActivity(intent);
                        finish();
                    } else {
//                        Intent intent = new Intent(SetupGPSLocationActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }


                }
            });
            text_Skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SetupGPSLocationActivity.this, HomeOffline.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }


    public boolean statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        } else {
            if (getLocationPermission()) {
                Intent intent = new Intent(SetupGPSLocationActivity.this, HomeOffline.class);
                startActivity(intent);
                finish();
                return true;
            }
//            Intent intent = new Intent(SetupGPSLocationActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//            getLocationPermission();
            return false;
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        getLocationPermission();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
//                        Intent intent = new Intent(SetupGPSLocationActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

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
            return false;
        }
//        return false;
    }

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
//                    updateLocationUI();
                }
            }
        }

    }
    // [END maps_current_place_on_request_permissions_result]
}