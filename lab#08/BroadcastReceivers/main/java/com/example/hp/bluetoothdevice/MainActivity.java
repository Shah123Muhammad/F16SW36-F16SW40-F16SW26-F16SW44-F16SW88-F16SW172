package com.example.hp.bluetoothdevice;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MessageListener{

    MyReceiver receiver;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int pCheck= 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pCheck = checkSelfPermission(Manifest.permission.RECEIVE_SMS);
        }
        if(pCheck != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_SMS},1);
            }
        }
        else{
            Toast.makeText(MainActivity.this,"Permission already Granted!",Toast.LENGTH_LONG).show();
        }

        MessageReceiver.bindListener(this);

        filter = new IntentFilter();

        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");

        receiver = new MyReceiver();
        registerReceiver(receiver,filter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"Permission Granted!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Permision Denied",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    @Override
    public void messageRecieved(String message) {
        if(message.equalsIgnoreCase("hello"))
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
    }
}