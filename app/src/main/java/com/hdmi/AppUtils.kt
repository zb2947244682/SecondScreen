package com.hdmi

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

object AppUtils {
    fun getApps(context: Context): List<ApplicationInfo> {
        val packageManager = context.packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val userInstalledApps = mutableListOf<ApplicationInfo>()

        for (app in apps) {
            if ((app.flags and ApplicationInfo.FLAG_SYSTEM) == 0) {
                // Exclude system apps
                app.name = app.loadLabel(packageManager).toString()
                userInstalledApps.add(app)
            }
        }

        return userInstalledApps
    }
}
