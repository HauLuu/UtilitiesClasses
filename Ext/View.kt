
fun View.toast(mess: String) {
    Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
}

fun View.clicked(l: (v: View) -> Unit) {
    setOnClickListener(SafeClickListener { l.invoke(this) })
}

fun View.gone() {
    if (this.visibility != View.GONE) this.visibility = View.GONE
}

fun View.visible() {
    if (this.visibility != View.VISIBLE) this.visibility = View.VISIBLE
}

fun View.invisible() {
    if (this.visibility != View.INVISIBLE) this.visibility = View.INVISIBLE
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

