package com.example.task.Service;

import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.task.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

public class InternetSpeedService extends Service {
    Context context;
    AlertDialog.Builder builder;
    public static final int notify = 10000;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        context = this;

        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new
        mTimer.scheduleAtFixedRate(new InternetSpeedService.TimeDisplay(), 0, notify);   //Schedule task
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();    //For Cancel Timer
        Log.d("service is ", "Destroyed");
    }



    class TimeDisplay extends TimerTask {
        @Override
        public void run() {

            mHandler.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                    Log.d("service_is_internet", "running");
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                    int downSpeed = nc.getLinkDownstreamBandwidthKbps();
                    int upSpeed = nc.getLinkUpstreamBandwidthKbps();
                    Log.d("DownSpeedTag",""+downSpeed);
                    if (downSpeed<2000){
                        Toast.makeText(context, "Please change your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
                }
            });
        }
    }


    public int getWifiLevel()
    {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int linkSpeed = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(linkSpeed, 5);
        return level;
    }

}


