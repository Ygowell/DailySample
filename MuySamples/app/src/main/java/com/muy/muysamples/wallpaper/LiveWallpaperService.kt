package com.muy.muysamples.wallpaper

import android.media.MediaPlayer
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder

class LiveWallpaperService : WallpaperService() {

    override fun onCreateEngine(): Engine {
        return LiveEngine();
    }

    inner class LiveEngine : Engine() {
        private lateinit var mediaPlayer: MediaPlayer

        override fun onVisibilityChanged(visible: Boolean) {
            if (visible) mediaPlayer.start()
            else mediaPlayer.pause()
        }

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            super.onSurfaceCreated(holder)
            mediaPlayer = MediaPlayer()
            mediaPlayer.setSurface(holder.surface)
//            mediaPlayer.setDataSource()
            mediaPlayer.prepare()
            mediaPlayer.start()
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder?) {
            super.onSurfaceDestroyed(holder)
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
        }
    }
}