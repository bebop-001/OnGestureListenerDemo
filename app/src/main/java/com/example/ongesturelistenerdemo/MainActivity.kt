package com.example.ongesturelistenerdemo

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.onDoubleTapListenerdemo.DoubleTapListener

// See: https://stuff.mit.edu/afs/sipb/project/android/docs/training/gestures/detector.html
class MainActivity : AppCompatActivity() {
    companion object {
        var onTouchEventCounter = 0

        fun MotionEvent.toActionString () : String {
            return when (this.actionMasked) {
                MotionEvent.ACTION_DOWN   -> "DOWN"
                MotionEvent.ACTION_UP     -> "UP"
                MotionEvent.ACTION_CANCEL -> "CANCEL"
                MotionEvent.ACTION_MOVE   -> "MOVE"
                else                      -> {"Unexpected action: ${this.actionMasked}"}
            }
        }
    }
    private var mDetector: GestureDetectorCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate the gesture detector with the application
        // context and an implementation of GestureDetector.OnGestureListener
        mDetector = GestureDetectorCompat(applicationContext, GestureListener())
        // Set the gesture detector as the double tap listener.
        mDetector!!.setOnDoubleTapListener(DoubleTapListener())

        GestureListener.eventHappened.observe(this, { eventHappened:Boolean ->
            Log.d("GestureListener", "eventHappened:$eventHappened")
            if (eventHappened) {
                var str = GestureListener.getData()
                val tv = findViewById<TextView>(R.id.event_TV)
                while (str != null) {
                    tv.update(str!!)
                    str = GestureListener.getData()
                }
            }
        })
        DoubleTapListener.eventHappened.observe(this, { eventHappened:Boolean ->
            Log.d("GestureListener", "eventHappened:$eventHappened")
            if (eventHappened) {
                var str = DoubleTapListener.getData()
                val tv = findViewById<TextView>(R.id.event_TV)
                while (str != null) {
                    tv.update(str!!)
                    str = DoubleTapListener.getData()
                }
            }
        })
    }
    fun TextView.update(str: String) {
        val data = text.split("\n".toRegex()).toMutableList()
        data.add(0, str)
        text = data.take(25).joinToString("\n")
    }

    // Gesture detector and double tap listeners must have this.  It
    // seems this gets called
    override fun onTouchEvent(event: MotionEvent): Boolean {
        findViewById<TextView>(R.id.event_TV).update(
            String.format("%3d) MainActivity:OnTouch action=\"%s\"",
                onTouchEventCounter++, event.toActionString()))
        val rv = mDetector!!.onTouchEvent(event)
        return  if (rv) rv
                else super.onTouchEvent(event)
    }
}