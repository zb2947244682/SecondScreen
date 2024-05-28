package com.hdmi

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

object ToastUtils {
    private var toast: Toast? = null
    private val handler = Handler(Looper.getMainLooper())

    fun showShort(context: Context, message: CharSequence) {
        showToast(context, message, Toast.LENGTH_SHORT)
    }

    fun showLong(context: Context, message: CharSequence) {
        showToast(context, message, Toast.LENGTH_LONG)
    }

    private fun showToast(context: Context, message: CharSequence, duration: Int) {
        handler.post {
            toast?.cancel() // 取消之前的吐司
            toast = Toast.makeText(context, message, duration)
            toast?.show()
        }
    }
}
