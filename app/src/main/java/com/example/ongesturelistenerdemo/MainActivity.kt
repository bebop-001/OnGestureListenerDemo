/*
 *  Copyright 2020 Steven Smith kana-tutor.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *  either express or implied.
 *
 *  See the License for the specific language governing permissions
 *  and limitations under the License.
 */
package com.example.ongesturelistenerdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.onDoubleTapListenerdemo.DoubleTapListener
// good article on "How touch events are delivered in Android":
//     https://medium.com/@suragch/how-touch-events-are-delivered-in-android-eee3b607b038
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
                else                      -> {
                    "Unexpected action: ${this.actionMasked}"}
            }
        }
    }


    private var mDetector: GestureDetectorCompat? = null
    private var simpleDetector : GestureDetectorCompat? = null
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate the gesture detector with the application
        // context and an implementation of GestureDetector.OnGestureListener
        mDetector = GestureDetectorCompat(applicationContext, GestureListener())
        // Set the gesture detector as the double tap listener.
        mDetector!!.setOnDoubleTapListener(DoubleTapListener())

        // GestureDetector.SimpleOnSimpleListener is a convenience class to
        // extend when you only want to listen for a subset of all the gestures.
        // This implements all methods in the OnSimpleListener, OnDoubleTapListener,
        // and OnContextClickListener but does nothing and return false for all
        // applicable methods.
        simpleDetector = GestureDetectorCompat(applicationContext,
            SimpleListener())
        // the onTouch callback is the connection between the view and the
        // listener.  onTouch calls onTouchEvent which is what calls the
        // class callbacks.
        findViewById<Button>(R.id.button).setOnTouchListener(
                object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                // pass the events to the gesture detector
                // a return value of true means the detector is handling it
                // a return value of false means the detector didn't
                // recognize the event
                val tv = findViewById<TextView>(R.id.event_TV)
                tv.update("ENTER BUTTON")
                val rv = simpleDetector!!.onTouchEvent(event)
                tv.update("EXIT BUTTON")
                return rv
            }
        })

        val tv = findViewById<TextView>(R.id.event_TV)
        GestureListener.eventHappened.observe(this, { eventHappened: Boolean ->
            Log.d("GestureListener", "eventHappened:$eventHappened")
            if (eventHappened) {
                var str = GestureListener.getData()
                while (str != null) {
                    tv.update(str!!)
                    str = GestureListener.getData()
                }
            }
        })
        DoubleTapListener.eventHappened.observe(this, { eventHappened: Boolean ->
            Log.d("GestureListener", "eventHappened:$eventHappened")
            if (eventHappened) {
                var str = DoubleTapListener.getData()
                while (str != null) {
                    tv.update(str!!)
                    str = DoubleTapListener.getData()
                }
            }
        })
        SimpleListener.eventHappened.observe(this, { eventHappened: Boolean ->
            Log.d("com.example.ongesturelistenerdemo.SimpleListener",
                "eventHappened:$eventHappened")
            if (eventHappened) {
                var str = SimpleListener.getData()
                while (str != null) {
                    tv.update(str!!)
                    str = SimpleListener.getData()
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
