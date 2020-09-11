package com.example.ongesturelistenerdemo

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ongesturelistenerdemo.MainActivity.Companion.toActionString

internal class GestureListener : GestureDetector.OnGestureListener {
    companion object {
        private var _eventHappend = MutableLiveData<Boolean>()
        val eventHappened: LiveData<Boolean>
            get() = _eventHappend
        private var idx = 0
        private var buffer = mutableListOf<String>()
        private fun putData(data : String) {
            synchronized(buffer) {
                Log.d("GestureListener", "putData(\"$data\")")
                buffer.add(String.format("%3d) %s", idx++, data))
                _eventHappend.value = true
            }
        }
        fun getData() : String?  {
            val rv : String?
            synchronized(buffer) {
                rv = if (buffer.isNotEmpty()) buffer.removeAt(0)
                else null
                _eventHappend.value = buffer.isNotEmpty()
                Log.d("GestureListener", "eventHappened:getData: ${buffer.size}:${eventHappened.value}")
            }
            return rv
        }
    }
    override fun onDown(event: MotionEvent): Boolean {
        putData("GestureListener::onDown: action = ${event.toActionString()}")
        return true
    }

    override fun onShowPress(event: MotionEvent) {
        putData("GestureListener::onShowPress: action = ${event.toActionString()}")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        putData("GestureListener::onSingleTapUp: action = ${event.toActionString()}")
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent,
                          distanceX: Float, distanceY: Float): Boolean {
        putData("GestureListener::onScroll: action = ${e1.toActionString()}${e2.toActionString()}:")
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        putData("GestureListener::onLongPress: action = ${event.toActionString()}")
    }

    override fun onFling(event1: MotionEvent, event2: MotionEvent,
                         velocityX: Float, velocityY: Float): Boolean {
        putData("GestureListener::onFling: action = ${event1.action}${event2.action}:")
        return true
    }
}
