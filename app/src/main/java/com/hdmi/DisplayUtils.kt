package com.hdmi

import android.content.Context
import android.hardware.display.DisplayManager
import android.view.Display

object DisplayUtils {
    fun getAllDisplays(context: Context): List<Display> {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        var displayList = displayManager.displays.toList()
        return displayList
    }
}
