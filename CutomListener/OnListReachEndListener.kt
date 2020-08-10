import android.os.SystemClock
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class OnListReachEndListener(
        private val layoutManager: RecyclerView.LayoutManager,
        private val defaultInterval: Int = 400,
        private val threadHold: Int = 3,
        private val onReachEnd: () -> Unit = {}) : RecyclerView.OnScrollListener() {
    private var lastTimeInvokeOnReachEnd: Long = 0L

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        try {
            (layoutManager as LinearLayoutManager).also {
                val elapsed = SystemClock.elapsedRealtime()
                val reachEnd = it.itemCount <= it.findLastVisibleItemPosition() + threadHold
                val isScrolledDown = dy > 0
                if (reachEnd && isScrolledDown) {
                    if (elapsed - lastTimeInvokeOnReachEnd < defaultInterval)
                        return
                    lastTimeInvokeOnReachEnd = elapsed
                    onReachEnd.invoke()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}