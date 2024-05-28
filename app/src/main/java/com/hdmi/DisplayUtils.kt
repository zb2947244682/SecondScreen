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

    fun startListen(
        context: Context,
        onDisplayAdded: (Int) -> Unit,
        onDisplayChanged: (Int) -> Unit,
        onDisplayRemoved: (Int) -> Unit
    ): DisplayManager.DisplayListener {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

        val displayListener = object : DisplayManager.DisplayListener {
            override fun onDisplayAdded(displayId: Int) {
                onDisplayAdded(displayId)
            }

            override fun onDisplayChanged(displayId: Int) {
                onDisplayChanged(displayId)
            }

            override fun onDisplayRemoved(displayId: Int) {
                onDisplayRemoved(displayId)
            }
        }

        displayManager.registerDisplayListener(displayListener, null)

        return displayListener
    }

    fun stopListen(context: Context, displayListener: DisplayManager.DisplayListener) {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        displayManager.unregisterDisplayListener(displayListener)
    }

    fun getConnectedDisplays(context: Context): Array<Display> {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        return displayManager.getDisplays()
    }
}
