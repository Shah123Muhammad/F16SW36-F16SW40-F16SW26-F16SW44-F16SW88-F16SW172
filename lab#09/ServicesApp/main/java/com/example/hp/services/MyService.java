package com.example.hp.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class MyService extends Service {
    private final String CHANNEL_ID = "1";
    Notification mNotification, stopNotification;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        Toast.makeText(this, "Service Started!", Toast.LENGTH_LONG).show();
        buildNotification();
        startForeground(41,mNotification);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service stopped!", Toast.LENGTH_LONG).show();
        // buildDestroyNotification();
        //stopNotification.notify();
        // startForeground(1, stopNotification);

    }
    public void buildNotification () {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            CharSequence chanelName = "Playback Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, chanelName, importance);
            nManager.createNotificationChannel(notificationChannel);
        }
        mNotification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                .setContentTitle("On going")
                .setContentText("Service Started")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle())
                .build();
    }
    /*public void buildDestroyNotification () {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        stopNotification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                .setAutoCancel(true)
                .setContentTitle("Stop")
                .setContentText("Service Stopped")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle())
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getBaseContext());
        notificationManagerCompat.notify(1, stopNotification);
    }*/
}
