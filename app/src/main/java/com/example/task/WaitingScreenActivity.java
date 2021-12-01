package com.example.task;

import static com.example.task.Session.SaveSharedPreference.getClientId;
import static com.example.task.Session.SaveSharedPreference.getRideId;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.task.CheckRideStatusFiles.CheckRideStatusRequest;
import com.example.task.CheckRideStatusFiles.CheckRideStatusResponse;
import com.example.task.UserServiceInterface.ApiClass;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitingScreenActivity extends AppCompatActivity {
    Context context;
    Activity activity;
    TextView timer;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;
    RelativeLayout parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);
        activity=this;
        context=this;

        parentLayout= findViewById(R.id.parentLayout);
        timer= findViewById(R.id.timer);

        reverseTimer(120,timer);

        checkridereq();
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                checkridereq();
//                Toast.makeText(DriversRequestFind.this, "This method is run every 10 seconds",
//                        Toast.LENGTH_SHORT).show();
            }
        }, delay);
    }

    @Override
    public void onBackPressed() {

    }

    public void reverseTimer(int Seconds, final TextView tv) {

        new CountDownTimer(Seconds * 1000 + 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
//                size = size -1;
//                card_stack.setVisibility(View.GONE);
//                tv.setText("Completed");
            }
        }.start();
    }


    public void checkridereq() {
        CheckRideStatusRequest checkRideStatusRequest = new CheckRideStatusRequest();
        checkRideStatusRequest.setDriver_id(getClientId(context));
        checkRideStatusRequest.setRide_id(getRideId(context));

        Call<CheckRideStatusResponse> showDriverResponseCall = ApiClass.getUserServiceAPI().userCheckRideStatus(checkRideStatusRequest);
        showDriverResponseCall.enqueue(new Callback<CheckRideStatusResponse>() {
            @Override
            public void onResponse(Call<CheckRideStatusResponse> call, Response<CheckRideStatusResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("2"))
                    {

                        handler.removeCallbacks(runnable);
                        Snackbar snackbar = Snackbar
                                .make(parentLayout, "RideAccepted", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent intent = new Intent(WaitingScreenActivity.this,HomeOnlineBookingDetailsGotopickup.class);
                        intent.putExtra("driver_id",response.body().data.driver_id);
                        intent.putExtra("client_id",response.body().data.client_id);
                        intent.putExtra("start_lat",response.body().data.start_lat);
                        intent.putExtra("start_long",response.body().data.start_long);
                        intent.putExtra("end_lat",response.body().data.end_lat);
                        intent.putExtra("end_long",response.body().data.end_long);
                        intent.putExtra("client_name",response.body().data.client_name);
                        startActivity(intent);
                        finish();
                    }
                    else if(response.body().getStatus().equals("0"))
                    {
                        handler.removeCallbacks(runnable);
                        Snackbar snackbar = Snackbar
                                .make(parentLayout, "Ride has been Canceled", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        startActivity(new Intent(WaitingScreenActivity.this,HomeOffline.class));
                        finish();
                    }
                    else if(response.body().getStatus().equals("1"))
                    {
//                        handler.removeCallbacks(runnable);
//                        Snackbar snackbar = Snackbar
//                                .make(parentLayout, "Status 1", Snackbar.LENGTH_LONG);
//                        snackbar.show();
//                        startActivity(new Intent(WaitingScreenActivity.this,HomeOffline.class));
//                        finish();
                    }
                } else {
//                    Toast.makeText(History.this, "Register Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckRideStatusResponse> call, Throwable t) {
//                Toast.makeText(History.this, "Throwable " + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Error " + t);
            }
        });
    }

}