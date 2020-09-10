package com.example.ongesturelistenerdemo

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat

// See: https://stuff.mit.edu/afs/sipb/project/android/docs/training/gestures/detector.html
class MainActivity : AppCompatActivity() {
    private var mDetector: GestureDetectorCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate the gesture detector with the application
        // context and an implementation of GestureDetector.OnGestureListener
        mDetector = GestureDetectorCompat(applicationContext, GestureListener())
        // Set the gesture detector as the double tap listener.
        mDetector!!.setOnDoubleTapListener(DoubleTapListener())
    }

    // Gesture detector and double tap listeners must have this.  It
    // seems this gets called
    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("MainActivity", "onTouchEvent: $event")
        val rv = mDetector!!.onTouchEvent(event)
        Log.d("MainActivity", "onTouchEvent: rv = $rv")
        // rv == true means upstream handled event.
        return if (rv) rv else super.onTouchEvent(event)
    }
}