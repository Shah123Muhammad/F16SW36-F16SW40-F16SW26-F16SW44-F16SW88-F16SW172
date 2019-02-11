package com.example.hp.task1;

import android.gesture.Gesture;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    ImageView imageView;
    GestureDetectorCompat gesture;
    int images[] = {R.drawable.image1,R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};
    int index = 0;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        gesture = new GestureDetectorCompat(this,this);
        gesture.setIsLongpressEnabled(true);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        gesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX() > e2.getX()) {
            index++;
            if(index==5)
                index = 0;
            imageView.setImageResource(images[index]);
        }
        if(e1.getX() < e2.getX()) {
            index--;
            if(index < 0) {
                index = 4;
            }
            imageView.setImageResource(images[index]);
        }

        return false;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        //private ImageView imageView;
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}