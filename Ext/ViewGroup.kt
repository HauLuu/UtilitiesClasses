fun ViewGroup.inflateLayout(layoutResId: Int): View = LayoutInflater.from(context).inflate(layoutResId, this)

// https://blog.mindorks.com/android-app-optimization-using-arraymap-and-sparsearray-f2b4e2e3dc47
fun ViewGroup.saveChildrenState(): SparseArray<Parcelable> {
    val sparseArray = SparseArray<Parcelable>()
    for (i in 0 until childCount) {
        getChildAt(i).saveHierarchyState(sparseArray)
    }
    return sparseArray
}

// https://blog.mindorks.com/android-app-optimization-using-arraymap-and-sparsearray-f2b4e2e3dc47
fun ViewGroup.restoreChildrenState(childrenState: SparseArray<Parcelable>) {
    for (i in 0 until childCount) {
        getChildAt(i).restoreHierarchyState(childrenState)
    }
}