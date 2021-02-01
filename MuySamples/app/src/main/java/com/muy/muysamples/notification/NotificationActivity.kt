package com.muy.muysamples.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.muy.muysamples.R
import kotlinx.android.synthetic.main.activity_notification.*

private const val CHANNEL_ID = "1000"

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        createNotificationChannel()

        notification1.setOnClickListener {
            showNotification()
        }

        notification.setOnClickListener {
            createCustomNotification()
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification() {
        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Shopping Sales")
                .setContentText("There are so many goods that have discount. Let's have a look!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        val notificationId = 100

        with(NotificationManagerCompat.from(this)) {
            notify(100, builder.build())
        }
    }

    private fun createCustomNotification() {
        val remoteViews = RemoteViews(packageName, R.layout.custom_notification)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.rainbow)
        val paint = Paint().apply {
            isAntiAlias = true
            isFilterBitmap = true
            shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }

        val size = bitmap.width
        val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output).apply {
            drawFilter = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        }
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

        remoteViews.setImageViewBitmap(R.id.iv_icon, output)


//        val gradientDrawable = GradientDrawable().apply {
//            shape = GradientDrawable.RECTANGLE
//            cornerRadius = 5f.dp
//            setColor(Color.parseColor("#007700"))
//            isFilterBitmap = true
//        }
//
//        remoteViews.setImageViewBitmap(R.id.iv_action, UIUtils.drawableToBitmap(gradientDrawable))

//        val btnBitmap = Bitmap.createBitmap(100, 50, Bitmap.Config.ARGB_8888)
//        val btnCanvas = Canvas(btnBitmap)
//        val path = Path().apply {
//            addRoundRect(RectF(0f, 0f, 100f, 50f),
//                    floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f), Path.Direction.CW)
//        }
//        val btnPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//            color = Color.GREEN
//        }
//        btnCanvas.drawPath(path, btnPaint)

        val btnBitmap = BitmapFactory.decodeResource(resources, R.drawable.wihte)
        val realBitmap = makeTintBitmap(btnBitmap, R.color.colorAccent)
        remoteViews.setImageViewBitmap(R.id.iv_action, realBitmap)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setCustomContentView(remoteViews)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(101, builder.build())
        }
    }

    private fun makeTintBitmap(inputBitmap: Bitmap, tintColor: Int): Bitmap {
        val outputBitmap = Bitmap.createBitmap(inputBitmap.width, inputBitmap.height, inputBitmap.config)
        val canvas = Canvas(outputBitmap)
        val paint = Paint()
        paint.colorFilter = PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(inputBitmap, 0f, 0f, paint)
        return outputBitmap
    }

    private fun showNotificationWithAction() {
//        val snoozeIntent = Intent(this, MyBroadcastReceiver::class.java).apply {
//            action = ACTION_SNOOZE
//            putExtra(EXTRA_NOTIFICATION_ID, 0)
//        }
//
//        val snoozePendingIntent = PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)
//
//        val intent = Intent(this, NotificationActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("My notification")
//                .setContentText("Hello World!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
//                .addAction(R.drawable.ic_snooze, getString(R.string.snooze),
//                        snoozePendingIntent)
    }


}
