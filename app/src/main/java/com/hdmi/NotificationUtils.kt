package com.hdmi

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

object NotificationUtils {

    private const val NOTIFICATION_ID = 1
    private const val CHANNEL_ID = "reback"
    private const val CHANNEL_NAME = "回到应用"

    fun showNotification(context: Context, activity: Activity) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 创建一个Intent，指定要跳转的Activity
        val intent = Intent(context, activity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)

        val title = "第二屏幕"
        val content = "点击此处返回APP"

        // 创建通知
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setOngoing(true) // 使通知变为常驻通知
            .setAutoCancel(false) // 设置为不自动取消

        // 显示通知
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    fun createNotificationChannel(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
            description = "回到应用"
        }
        // 注册通知渠道
        notificationManager.createNotificationChannel(channel)
    }
}
