package com.hdmi

import android.app.Activity
import android.content.Intent
import android.os.Bundle


class WakeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_wake)
        if (GlobalUtils.packageName != "" && GlobalUtils.displayId >= 0) {
            AppUtils.launchApp(this, GlobalUtils.packageName, GlobalUtils.displayId)
            moveTaskToBack(true)
        }
        finish()
    }
}
