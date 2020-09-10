package com.example.ongesturelistenerdemo;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

class DoubleTapListener implements GestureDetector.OnDoubleTapListener{
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d("DoubleTapListener", "onSingleTapConfirmed: "
                + event.toString());
        return true;
    }
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d("DoubleTapListener", "onDoubleTap: " + event.toString());
        return true;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d("DoubleTapListener", "onDoubleTapEvent: " + event.toString());
        return true;
    }
}
