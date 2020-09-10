package com.example.ongesturelistenerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;


// See: https://stuff.mit.edu/afs/sipb/project/android/docs/training/gestures/detector.html

public class MainActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "Gestures";
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
}
