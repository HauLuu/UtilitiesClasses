import android.app.Activity
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

val Activity.screenHeight get() = Point().apply { windowManager.defaultDisplay.getSize(this) }.y

val Activity.injector get() = (application as DaggerComponentProvider).component

fun Activity.requestPermission(perms: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, perms, requestCode)
}

fun Activity.shouldProvideRationale(permission: String) =
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun Activity.changeStatusBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.window.apply {
            try {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = ContextCompat.getColor(this@changeStatusBarColor, color)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    } else {
        log("debug", "Can't change status bar color on pre-lollipop devices")
    }
}