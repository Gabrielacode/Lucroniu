package com.allstars.lucroniu.ui.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

object NotificationHandler {

     var notificationManager : NotificationManager? = null

    fun initManager(context: Context){
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        if(notificationManager == null){
            Toast.makeText(context,"Notification cannot be made on used on this device",Toast.LENGTH_SHORT).show()
        }
    }



    fun createNotificationChannel(notificationChannel: List<NotificationChannel>){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(notificationManager?.notificationChannels?.containsAll(notificationChannel) == false)
            notificationManager?.createNotificationChannels(notificationChannel)
        }
    }


}
