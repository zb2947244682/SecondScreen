package com.hdmi

import android.app.Activity
import android.content.Intent
import android.os.Bundle


class WakeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (GlobalUtils.packageName != "" && GlobalUtils.displayId >= 0) {
            AppUtils.launchApp(this, GlobalUtils.packageName, GlobalUtils.displayId)
            moveTaskToBack(true)
            finish()
//            val intent = Intent(Intent.ACTION_MAIN)
//            intent.addCategory(Intent.CATEGORY_HOME)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
        }
    }
}
