package com.example.task.Service;


import static com.example.task.Session.SaveSharedPreference.getClientId;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


import com.example.task.UserServiceInterface.ApiClass;
import com.example.task.HomeOnlineBookingDetailsGotopickup;
import com.example.task.NotificationFiles.NotificationRequest;
import com.example.task.NotificationFiles.NotificationResponse;
import com.example.task.Notifications;
import com.example.task.R;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServiceClass extends Service {
    Context context;
    String driverId;

    public static final int notify = 30000;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        context = this;
        driverId = getClientId(context);
        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new
        mTimer.scheduleAtFixedRate(new TimeDisplay(), 0, notify);   //Schedule task
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();    //For Cancel Timer
        Log.d("service is ", "Destroyed");
    }

    //class TimeDisplay for handling task
    class TimeDisplay extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("service is ", "running");
                    notificationapi(driverId);
                }
            });
        }
    }

    public void notificationapi(String driverId) {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setDriver_id(driverId);

        Call<NotificationResponse> notificationResponseCall = ApiClass.getUserServiceAPI().userGetDriverNotification(notificationRequest);
        notificationResponseCall.enqueue(new Callback<NotificationResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if (response.isSuccessful()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("Aber", "SimpleNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = getSystemService(NotificationManager.class);
                        manager.createNotificationChannel(channel);
                    }
                    if (response.body().message.equals("Notifications")) {
                        if (response.body().data == null) {
                        } else {
                            notificationDialog(response.body().data.text);
                            Intent intent = new Intent(ServiceClass.this, HomeOnlineBookingDetailsGotopickup.class);
                            startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Log.d("TAG", "Error " + t);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notificationDialog(String text) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "aber";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Sample Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_logo)
                .setTicker("Aber")
                //.setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(text)
                .setContentText("This is sample notification")
                .setContentInfo("Information");


        Intent notificationIntent = new Intent(this, Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(1, notificationBuilder.build());
    }

    private void addNotification(String text) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notificaiton")
                        .setContentText(text)
                        .setAutoCancel(true);
        Intent notificationIntent = new Intent(this, Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
