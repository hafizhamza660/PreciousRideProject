package com.example.task.Fragment;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.AcceptedInterStateRideFiles.AcceptedInterStateRideRequest;
import com.example.task.AcceptedInterStateRideFiles.AcceptedInterStateRideResponse;
import com.example.task.R;
import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.adapters.ShowDriverAcceptedAdatper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AcceptInterStateFragment extends Fragment {

    RecyclerView recyclerViewRideRequest;
    LinearLayoutManager linearLayoutManager;
    private ShowDriverAcceptedAdatper rideRequestListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_accept_inter_state, container, false);
        recyclerViewRideRequest = view.findViewById(R.id.recycler_view_rideRequest);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerViewRideRequest.setLayoutManager(linearLayoutManager);
        riderequest();


        return view;
    }

    public void riderequest() {
        AcceptedInterStateRideRequest acceptRideRequest = new AcceptedInterStateRideRequest();
        acceptRideRequest.setDriver_id(getClientId(getContext()));

        Call<AcceptedInterStateRideResponse> signUpResponseCall = ApiClass.getUserServiceAPI().userAcceptedInterStateRide(acceptRideRequest);
        signUpResponseCall.enqueue(new Callback<AcceptedInterStateRideResponse>() {
            @Override
            public void onResponse(Call<AcceptedInterStateRideResponse> call, Response<AcceptedInterStateRideResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().data.equals(null))
                    {

                    }
                    else {
                        rideRequestListAdapter = new ShowDriverAcceptedAdatper(getActivity(), getContext(), response.body().data);
                        recyclerViewRideRequest.setAdapter(rideRequestListAdapter);
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<AcceptedInterStateRideResponse> call, Throwable t) {
                Log.d("TAG", "Error " + t);
            }
        });
    }
}