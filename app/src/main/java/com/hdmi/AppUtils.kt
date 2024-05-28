package com.hdmi

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.startActivity

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

    fun launchApp(
        context: Context, packageName: String, displayId: Int, forceLandscape: Boolean = false
    ) {
        if (packageName != "" && displayId >= 0) {
            val packageManager: PackageManager = context.packageManager
            val intent: Intent? = packageManager.getLaunchIntentForPackage(packageName)

            intent?.apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("force_landscape", forceLandscape)
            }

            val launchDisplayId = displayId
            val activityOptions =
                ActivityOptions.makeBasic().setLaunchDisplayId(launchDisplayId).toBundle()

            if (intent != null) {
                startActivity(context, intent, activityOptions)
            }
        }
    }
}
