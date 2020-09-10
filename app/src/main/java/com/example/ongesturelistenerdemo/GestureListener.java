package com.example.ongesturelistenerdemo;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

class GestureListener implements GestureDetector.OnGestureListener {
    @Override
    public boolean onDown(MotionEvent event) {
        Log.d("GestureListener","onDown: " + event.toString());
        return true;
    }
    @Override
    public void onShowPress(MotionEvent event) {
        Log.d("GestureListener", "onShowPress: " + event.toString());
    }
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d("GestureListener", "onSingleTapUp: " + event.toString());
        return true;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        Log.d("GestureListener", "onScroll: " +
                e1.toString()+e2.toString());
        return true;
    }
    @Override
    public void onLongPress(MotionEvent event) {
        Log.d("GestureListener", "onLongPress: " + event.toString());
    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d("GestureListener", "onFling: "
                + event1.toString()+event2.toString());
        return true;
    }
}
