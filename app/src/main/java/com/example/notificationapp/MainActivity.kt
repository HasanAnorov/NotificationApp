package com.example.notificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

val CHANNEL_Id ="channelId"
val CHANNEL_NAME= "channelName"
val NOTIFICATION_ID= 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        val intent=Intent(this, MainActivity::class.java)
        val pendingIntent =TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT )

        }


        val notification = NotificationCompat.Builder(this, CHANNEL_Id)
            .setContentTitle("AwesomeNotification")
            .setContentText("This is notification text . bla bla bla")
                //and setContentText have to be like that
                //.setContentText("your terms - Are you sure to do that ?")
            .setSmallIcon(R.drawable.limitless)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            //if you want to do something like an action you can easily do it like that\
            //.addAction(R.drawable.bckground,"yes",pendingIntent1)
            //.addAction(R.drawable.bckground,"yes",pendingIntent2)
            .build()

        val notificationManager =NotificationManagerCompat.from(this)
        showNotification.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID,notification)
        }
    }

    private fun createNotificationChannel(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel =NotificationChannel(CHANNEL_Id, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                    lightColor= Color.GREEN
                    enableLights(true)
                }
                val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.cancelAll()
                manager.createNotificationChannel(channel)
            }
        }


}

