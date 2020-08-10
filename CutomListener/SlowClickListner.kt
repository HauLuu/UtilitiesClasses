import android.os.SystemClock
import android.view.View

class SlowClickListener(
        private val interval: Long = 200L,
        private val listener: (v: View?) -> Unit)
    : View.OnClickListener {

    private var lastTimeClicked: Long = 0L

    override fun onClick(v: View?) {
        val timeHasPassed = SystemClock.elapsedRealtime() - lastTimeClicked
        if (timeHasPassed < interval) {
            return
        } else{
            lastTimeClicked = SystemClock.elapsedRealtime()
            listener.invoke(v)
        }
    }
}