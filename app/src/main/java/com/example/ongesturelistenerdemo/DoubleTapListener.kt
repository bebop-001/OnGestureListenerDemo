package com.example.ongesturelistenerdemo

import android.util.Log
import android.view.GestureDetector.OnDoubleTapListener
import android.view.MotionEvent

internal class DoubleTapListener : OnDoubleTapListener {
    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        Log.d("DoubleTapListener", "onSingleTapConfirmed: "
                + event.toString())
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d("DoubleTapListener", "onDoubleTap: $event")
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        Log.d("DoubleTapListener", "onDoubleTapEvent: $event")
        return true
    }
}