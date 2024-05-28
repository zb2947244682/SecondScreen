package com.hdmi

import android.app.Application
import android.util.Log


class ApplicationRoot : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyApplication", "应用程序已启动")

        // 在这里可以进行一些全局初始化操作
        // 例如：初始化日志、初始化数据库等
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("MyApplication", "应用程序已终止")

        // 在这里可以进行一些清理操作
        // 例如：关闭数据库连接等
    }
}
