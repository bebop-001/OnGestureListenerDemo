import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ongesturelistenerdemo.MainActivity.Companion.toActionString


// GestureDetector.SimpleOnSimpleListener is a convenience class to
// extend when you only want to listen for a subset of all the gestures.
// This implements all methods in the OnSimpleListener, OnDoubleTapListener,
// and OnContextClickListener but does nothing and return false for all
// applicable methods.
internal class SimpleListener : GestureDetector.SimpleOnGestureListener() {
    companion object {
        private var _eventHappend = MutableLiveData<Boolean>()
        val eventHappened: LiveData<Boolean>
            get() = _eventHappend
        private var idx = 0
        private var buffer = mutableListOf<String>()
        private fun putData(data : String) {
            synchronized(buffer) {
                Log.d("SimpleListener", "putData(\"$data\")")
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
            }
            return rv
        }
    }
    override fun onLongPress(e: MotionEvent) {
        putData("SimpleListener::onLongPress: action = ${e.toActionString()}")
        // super.onLongPress(e) to not consume
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent,
        velocityX: Float, velocityY: Float): Boolean {
        putData("SimpleListener::onFling: action " +
                " = ${e1.toActionString()}, ${e1.toActionString()}")
        return true // consume event
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        putData("SimpleListener::onSingleTapConfirmed: action = ${e.toActionString()}")
        return true // consume event
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        putData("SimpleListener::onDoubleTap: action = ${e.toActionString()}")
        return true // consume event
    }
}
