package com.example.task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class SignUp extends AppCompatActivity {
    TextView city_name;
    String name;
    Context context;
    EditText number;
    private List<Place.Field> fields;
    final int place_picker_req_code =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        number=findViewById(R.id.number);
        city_name=findViewById(R.id.city_name);
        context=this;
        fields = Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG);
        Places.initialize(getApplicationContext(), "AIzaSyAd8q-fqcHslANRJ3WZxR5cMYY1CgtBe9I");




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case place_picker_req_code:
                Place place = Autocomplete.getPlaceFromIntent(data);
                name = place.getName();
                city_name.setText(name);

                break;

        }
    }


    public void sign_up(View view) {
        Intent intent = new Intent(SignUp.this,HomeOffline.class);
        startActivity(intent);
    }

    public void sign_in(View view) {
        Intent intent = new Intent(SignUp.this,MainActivity.class);
        startActivity(intent);
    }

    public void google_sign_in(View view) {
        startNewActivity(context,"com.google.android.googlequicksearchbox");
    }


    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        }
    }

    public void facebook_sign_in(View view) {
        startNewActivity(context,"com.facebook.katana");
    }

    public void worldgrin_sign_in(View view) {
        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {
        number.setText("");
    }

    public void city_get(View view) {
        Intent i = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).build(SignUp.this);
        startActivityForResult(i,place_picker_req_code);
    }
}