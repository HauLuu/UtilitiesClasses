import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

//https://stackoverflow.com/a/45727769/5597765
fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    links.forEach { link ->
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Selection.setSelection((widget as TextView).text as Spannable, 0)
                widget.invalidate()
                link.second.onClick(widget)
            }
        }
        val startIndexOfLink = this.text.toString().toUpperCase().indexOf(link.first.toUpperCase())
        spannableString.setSpan(clickableSpan,
                startIndexOfLink,
                startIndexOfLink + link.first.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    this.movementMethod = LinkMovementMethod.getInstance()
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

fun TextView.string(resId: Int): String = context.getString(resId)