package com.example.hp.services;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startServiceBtn, stopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceBtn = findViewById(R.id.startServiceBtn);
        stopService = findViewById(R.id.stopService);
        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(),MyService.class));
            }
        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), MyService.class));
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Destroy")
                        .setContentText("Service stopped!")
                        .setAutoCancel(true);

                // Obtain NotificationManager system service in order to show the notification
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, mBuilder.build());

            }
        });
    }
}
