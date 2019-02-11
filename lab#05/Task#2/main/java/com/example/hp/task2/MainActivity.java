package com.example.hp.task2;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    ImageView imageView;
    SensorManager mSensorManager;
    Sensor mProximity;
    float x;
    float y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(mProximity == null) {
            finish();
        }
        x=imageView.getScaleX();
        y=imageView.getScaleY();
    }
    @Override
    protected  void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }
    @Override
    protected  void onResume() {
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float scaleX = imageView.getScaleX();
        float scaleY = imageView.getScaleY();

        if (event.values[0] < mProximity.getMaximumRange()) {
            //Detected Something nearby
            imageView.setScaleX(scaleX*2);
            imageView.setScaleY(scaleY*2);
        }
        else {
            imageView.setScaleX(x);
            imageView.setScaleY(y);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
