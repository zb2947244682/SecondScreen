package com.hdmi

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children


class MainActivity : Activity() {

    lateinit var inflater: LayoutInflater

    lateinit var sel_display: Display

    lateinit var sel_packageName: String

    lateinit var sel_appName: String

    var display_ok = false

    var app_ok = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        refreshDisplayList()
        refreshAppList()

        val refreshButton = findViewById<Button>(R.id.refreshButton)

        refreshButton.setOnClickListener {
            refreshDisplayList()
            refreshAppList()
            Toast.makeText(this, "数据刷新完成", Toast.LENGTH_SHORT).show()
        }


        val deliverButton = findViewById<Button>(R.id.deliverButton)

        deliverButton.setOnClickListener {
            if (display_ok && app_ok) {

                val intent =
                    packageManager.getLaunchIntentForPackage(sel_packageName)

                intent?.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent?.putExtra("force_landscape", true); // 通过Extra传递参数告诉Activity需要强制横屏
                startActivity(
                    intent,
                    ActivityOptions.makeBasic().setLaunchDisplayId(sel_display.displayId)
                        .toBundle()
                )
            }
        }
    }

    private fun refreshStatus() {
        if (display_ok) {
            findViewById<Button>(R.id.display_title).setBackgroundResource(R.drawable.rounded_button_orange)
            findViewById<Button>(R.id.display_title).text = sel_display.name
        }
        if (app_ok) {
            findViewById<Button>(R.id.app_name).setBackgroundResource(R.drawable.rounded_button_orange)
            findViewById<Button>(R.id.app_name).text = sel_appName
        }
        if (display_ok && app_ok) {
            findViewById<Button>(R.id.deliverButton).setBackgroundResource(R.drawable.rounded_button_green)
        }
    }

    private fun refreshDisplayList() {
        var displayList = DisplayUtils.getAllDisplays(this)

        // 获取 HorizontalScrollView 的引用
        val screen_ll: LinearLayout = findViewById(R.id.screen_ll)

        screen_ll.removeAllViews()

        for (display in displayList) {
            val screen_list_item_layout = inflater.inflate(R.layout.screen_list_item_layout, null)

            screen_list_item_layout.findViewById<TextView>(R.id.screen_list_item_layout_title).text =
                display.name

            screen_list_item_layout.findViewById<TextView>(R.id.screen_list_item_layout_pixel).text =
                "${display.mode.physicalWidth}x${display.mode.physicalHeight}"

            screen_list_item_layout.findViewById<TextView>(R.id.screen_list_item_layout_id).text =
                "${display.displayId}"

            screen_list_item_layout.setOnClickListener {
                sel_display = display
                display_ok = true
                refreshStatus()
            }

            screen_ll.addView(screen_list_item_layout)
        }
    }

    private fun refreshAppList() {
        var appList = AppUtils.getApps(this)

        // 获取 HorizontalScrollView 的引用
        val app_ll: LinearLayout = findViewById(R.id.app_ll)

        app_ll.removeAllViews()

        for (app in appList) {

            val app_list_item_layout = inflater.inflate(R.layout.app_list_item_layout, null)

            app_list_item_layout.findViewById<TextView>(R.id.app_list_item_layout_title).text =
                "${app.name}"

            app_list_item_layout.findViewById<TextView>(R.id.app_list_item_layout_packagename).text =
                "包名：${app.packageName}"

//            app_list_item_layout.findViewById<TextView>(R.id.app_list_item_layout_classname).text =
//                "${app.className}"

            app_list_item_layout.setOnClickListener {
                sel_appName = app.name
                sel_packageName = app.packageName
                app_ok = true
                refreshStatus()
            }
            app_ll.addView(app_list_item_layout)
        }
//        var text = app_list_item_layout.findViewById<TextView>(R.id.app_list_item_layout_title)
    }
}