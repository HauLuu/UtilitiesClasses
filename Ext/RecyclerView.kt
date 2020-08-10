import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onListReachEnd(threadHold: Int = 3, block: () -> Unit) {
    layoutManager?.run {
        addOnScrollListener(OnListReachEndListener(
                layoutManager = this,
                threadHold = threadHold,
                onReachEnd = block))
    }
}