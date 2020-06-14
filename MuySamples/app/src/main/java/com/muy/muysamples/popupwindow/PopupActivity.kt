package com.muy.muysamples.popupwindow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.muy.muysamples.R
import kotlinx.android.synthetic.main.activity_popup.*


class PopupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        root.post {
            showPopup()
        }
    }

    private fun showPopup() {
        val data = UpdateData()
        data.apkSize = 15
        data.updateLog = "1. More video wallpapers are available now!\n2. We fixed some bugs as well." +
                "\n1. More video wallpapers are available now!\n2. We fixed some bugs as well." +
                "\n1. More video wallpapers are available now!\n2. We fixed some bugs as well." +
                "\n1. More video wallpapers are available now!\n2. We fixed some bugs as well." +
                "\n1. More video wallpapers are available now!\n2. We fixed some bugs as well." +
                "\n1. More video wallpapers are available now!\n2. We fixed some bugs as well." +
                "\n1. More video wallpapers are available now!\n2. We fixed some bugs as well."
        data.versionName = "1.1.0"

        val popup = VersionUpgradePopup(this, data)
        popup.show()
    }
}
