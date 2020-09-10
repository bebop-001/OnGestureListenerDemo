package com.example.ongesturelistenerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


// See: https://stuff.mit.edu/afs/sipb/project/android/docs/training/gestures/detector.html

public class MainActivity extends AppCompatActivity {
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the gesture detector with the application
        // context and an implementation of GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, new GestureListener());
        // Set the gesture detector as the double tap listener.
        mDetector.setOnDoubleTapListener(new DoubleTapListener());
    }
    // Gesture detector and double tap listeners must have this.
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d("MainActivity","onTouchEvent: " + event.toString());
        boolean rv = mDetector.onTouchEvent(event);
        Log.d("MainActivity","onTouchEvent: rv = " + rv);
        // rv == true means upstream handled event.
        return (rv) ? rv : super.onTouchEvent(event);
    }
}
