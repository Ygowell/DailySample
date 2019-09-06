package com.muy.muysamples.systemui

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.*
import android.view.WindowManager
import com.muy.muysamples.R
import kotlinx.android.synthetic.main.activity_system_ui.*


class SystemUiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_ui)

        btn_show_status.setOnClickListener {
            setFullscreen(isShowStatusBar = true, isShowNavigationBar = false)
        }

        btn_show_navigation.setOnClickListener {
            setFullscreen(isShowStatusBar = false, isShowNavigationBar = true)
        }

        setFullscreen(true, true)
    }

    fun setFullscreen(isShowStatusBar: Boolean, isShowNavigationBar: Boolean) {
        var uiOptions = (SYSTEM_UI_FLAG_LAYOUT_STABLE
                or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        if (!isShowStatusBar) {
            uiOptions = uiOptions or SYSTEM_UI_FLAG_FULLSCREEN
        }
        if (!isShowNavigationBar) {
            uiOptions = uiOptions or SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
        window.decorView.systemUiVisibility = uiOptions

        //隐藏标题栏
        supportActionBar!!.hide()
        //专门设置一下状态栏导航栏背景颜色为透明，凸显效果。
        setNavigationStatusColor(Color.TRANSPARENT)
    }

    private fun setNavigationStatusColor(color: Int) {
        //VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.navigationBarColor = color
        window.statusBarColor = color
    }
}
