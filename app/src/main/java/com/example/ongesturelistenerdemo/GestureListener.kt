package com.example.ongesturelistenerdemo

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

internal class GestureListener : GestureDetector.OnGestureListener {
    override fun onDown(event: MotionEvent): Boolean {
        Log.d("GestureListener", "onDown: $event")
        return true
    }

    override fun onShowPress(event: MotionEvent) {
        Log.d("GestureListener", "onShowPress: $event")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d("GestureListener", "onSingleTapUp: $event")
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent,
                          distanceX: Float, distanceY: Float): Boolean {
        Log.d("GestureListener", "onScroll: " +
                e1.toString() + e2.toString())
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d("GestureListener", "onLongPress: $event")
    }

    override fun onFling(event1: MotionEvent, event2: MotionEvent,
                         velocityX: Float, velocityY: Float): Boolean {
        Log.d("GestureListener", "onFling: "
                + event1.toString() + event2.toString())
        return true
    }
}