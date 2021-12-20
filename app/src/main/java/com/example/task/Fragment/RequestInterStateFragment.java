package com.example.task.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task.InterCityRequest.InterCityRideRequest;
import com.example.task.InterCityRequest.InterCityRideRequestResponse;
import com.example.task.InterCityRequests;
import com.example.task.R;
import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.adapters.InterCityRideRequestListAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestInterStateFragment extends Fragment {
    RecyclerView recyclerViewRideRequest;
    LinearLayoutManager linearLayoutManager;
    private InterCityRideRequestListAdapter rideRequestListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_request_inter_state, container, false);
        recyclerViewRideRequest = view.findViewById(R.id.recycler_view_rideRequest);
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewRideRequest.setLayoutManager(linearLayoutManager);

        countryName();
        return view;
    }

    public void countryName() {
        String country_name = null;
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(getActivity());
        for (String provider : lm.getAllProviders()) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            @SuppressWarnings("ResourceType") Location location = lm.getLastKnownLocation(provider);
            if(location!=null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addresses != null && addresses.size() > 0) {
                        country_name = addresses.get(0).getCountryName();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        Toast.makeText(getApplicationContext(), country_name, Toast.LENGTH_LONG).show();
//        citiesNamesGet(country_name);
        riderequest(country_name);
    }

    public void riderequest(String country) {
        InterCityRideRequest interCityRideRequest = new InterCityRideRequest();
        interCityRideRequest.setCountry(country);
        interCityRideRequest.setVehicle_type_id("3");


        Call<InterCityRideRequestResponse> signUpResponseCall = ApiClass.getUserServiceAPI().userInterCityRideHistory(interCityRideRequest);
        signUpResponseCall.enqueue(new Callback<InterCityRideRequestResponse>() {
            @Override
            public void onResponse(Call<InterCityRideRequestResponse> call, Response<InterCityRideRequestResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(InterCityRequests.this, ""+response.body().data, Toast.LENGTH_LONG).show();
//                    Log.d(TAG,"Data : "+response.body().data.get(0).id);
                    if (response.body().data.equals(null))
                    {

                    }
                    else {
                        rideRequestListAdapter = new InterCityRideRequestListAdapter(getActivity(), getContext(), response.body().data);
                        recyclerViewRideRequest.setAdapter(rideRequestListAdapter);
                    }
//
                } else {
//                    Toast.makeText(InterCityRequests.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InterCityRideRequestResponse> call, Throwable t) {
//                Toast.makeText(InterCityRequests.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
                Toast.makeText(getContext(), "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}