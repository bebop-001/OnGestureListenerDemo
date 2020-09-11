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
package com.example.onDoubleTapListenerdemo

import android.util.Log
import android.view.GestureDetector.OnDoubleTapListener
import android.view.MotionEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ongesturelistenerdemo.MainActivity.Companion.toActionString

internal class DoubleTapListener : OnDoubleTapListener {
    companion object {
        private var _eventHappend = MutableLiveData<Boolean>()
        val eventHappened: LiveData<Boolean>
            get() = _eventHappend
        private var idx = 0
        private var buffer = mutableListOf<String>()
        private fun putData(data : String) {
            synchronized(buffer) {
                Log.d("DoubleTapListener", "putData(\"$data\")")
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
                Log.d("DoubleTapListener", "eventHappened:getData: "
                    + "${buffer.size}:${eventHappened.value}")
            }
            return rv
        }
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        putData("DoubleTapListener::onSingleTapConfirmed: action = "
            + "${event.toActionString()}")
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        putData("DoubleTapListener::onDoubleTap: action = "
            + "${event.toActionString()}")
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        putData("DoubleTapListener::onDoubleTapEvent: action = "
            + "${event.toActionString()}")
        return true
    }
}
