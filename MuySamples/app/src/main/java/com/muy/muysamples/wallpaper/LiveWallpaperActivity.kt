package com.muy.muysamples.wallpaper

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muy.muysamples.R
import kotlinx.android.synthetic.main.activity_live_wallpaper.*
import java.io.IOException

class LiveWallpaperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_wallpaper)

        btn_set_live_wallpaper.setOnClickListener {

        }

        btn_set_static_wallpaper.setOnClickListener {
            setStaticWallpaper()
        }
    }

    private fun setStaticWallpaper() {
        val wallpaperManager = WallpaperManager.getInstance(this)

        try {
            val bitmap = BitmapFactory.decodeResource(resources, R.raw.preview)
            wallpaperManager.setBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun clearStaticWallpaper() {
        WallpaperManager.getInstance(this).clear()
    }
}
