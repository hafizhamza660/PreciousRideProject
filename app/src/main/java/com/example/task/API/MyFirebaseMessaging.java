package com.example.task.API;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.task.Message;
import com.example.task.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService {

//    public static final String FCM_CHANNEL_ID = "Channel Fcm";
    public static final String TAG = "MyTag1";
    String title,message;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        title = remoteMessage.getNotification().getTitle();
        message = remoteMessage.getNotification().getBody();

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }





//        Log.d(Tag,"OnMessage Recived");
//
//        Log.d(Tag, "onMessageReceived: "+remoteMessage.getFrom());
//
//        if (remoteMessage.getNotification() != null)
//        {
//            String title = remoteMessage.getNotification().getTitle();
//            String body = remoteMessage.getNotification().getBody();
//
//            Notification notification = new NotificationCompat.Builder(this,FCM_CHANNEL_ID)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setColor(Color.RED)
//                    .build();
//
//            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//            manager.notify(1002,notification);
//        }
//        if (remoteMessage.getData().size()>0)
//        {
//            Log.d(Tag, "onMessageReceived: "+remoteMessage.getData().size());
//
//            for (String key :remoteMessage.getData().keySet())
//            {
//                Log.d(Tag, "onMessageReceived: "+key+" Data : "+ remoteMessage.getData().get(key));
//            }
//
//            Log.d(Tag, "onMessageReceived: "+remoteMessage.getData().toString());
//        }



//Comment of video code

//
//        String sented = remoteMessage.getData().get("sented");
//
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (firebaseUser != null && sented.equals(firebaseUser.getUid()))
//        {
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
//            {
//                sendOreoNotification(remoteMessage);
//            }
//            else {
//                sendNotification(remoteMessage);
////                Notification notification = new NotificationCompat.Builder(this)
////                        .setContentTitle(remoteMessage.getNotification().getTitle())
////                        .setContentText(remoteMessage.getNotification().getBody())
////                        .setSmallIcon(R.mipmap.ic_launcher)
////                        .build();
////                NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
////                manager.notify(123, notification);
//            }
//        }
//    }
//
////    private void sendOreoNotification(RemoteMessage remoteMessage) {
////        String user = remoteMessage.getData().get("user");
////        String icon = remoteMessage.getData().get("icon");
////        String title = remoteMessage.getData().get("title");
////        String body = remoteMessage.getData().get("body");
////
////        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
////        String NOTIFICATION_CHANNEL_ID  = "CHATAPP";
////
////
////        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
////            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"ChatApp Notification",
////                    NotificationManager.IMPORTANCE_MAX);
////
////            notificationChannel.setDescription("Channel Notification");
////            notificationChannel.enableLights(true);
////            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
////            notificationChannel.enableVibration(true);
////
////            notificationManager.createNotificationChannel(notificationChannel);
////        }
////
////
////    }


    private void sendOreoNotification(RemoteMessage remoteMessage) {
        String user = remoteMessage.getData().get("user");
        String icon = remoteMessage.getData().get("icon");
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");

        RemoteMessage.Notification  notification = remoteMessage.getNotification();
        int j = Integer.parseInt(user.replaceAll("[\\D]",""));
        Intent intent = new Intent(this, Message.class);
        Bundle bundle = new Bundle();
        bundle.putString("userid",user);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,j,intent,PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        OreoNotification oreoNotification = new OreoNotification(this);
        Notification.Builder builder = oreoNotification.getOreoNotification(title,body,pendingIntent,defaultSound,icon);

        int i = 0;
        if (j>0)
        {
            i=j;
        }
        oreoNotification.getManager().notify(i,builder.build());
    }

    private void sendNotification(RemoteMessage remoteMessage) {

        String user = remoteMessage.getData().get("user");
        String icon = remoteMessage.getData().get("icon");
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");

        RemoteMessage.Notification  notification = remoteMessage.getNotification();
        int j = Integer.parseInt(user.replaceAll("[\\D]",""));
        Intent intent = new Intent(this, Message.class);
        Bundle bundle = new Bundle();
        bundle.putString("userid",user);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,j,intent,PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(Integer.parseInt(icon))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent);
        NotificationManager noti = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        int i = 0;
        if (j>0)
        {
            i=j;
        }
        noti.notify(i,builder.build());

    }
}
