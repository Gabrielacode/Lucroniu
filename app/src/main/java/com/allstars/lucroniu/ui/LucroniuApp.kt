package com.allstars.lucroniu.ui

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.room.RoomDatabase
import com.allstars.lucroniu.data.database.TaskDatabase
import com.allstars.lucroniu.data.entities.Task
import com.allstars.lucroniu.ui.utils.AlarmHandler
import com.allstars.lucroniu.ui.utils.NotificationHandler

class LucroniuApp :Application(){

    @RequiresApi(Build.VERSION_CODES.O)
    val listofNotificationChannels = listOf(
        NotificationChannel("1","Task Notifications",NotificationManager.IMPORTANCE_HIGH).apply { description = " These are notifications about ongoing tasks" }
    )
    companion object{
        lateinit var taskDatabase  :RoomDatabase
    }
    override fun onCreate() {
        super.onCreate()
         taskDatabase = TaskDatabase.getDatabase(applicationContext)
         NotificationHandler.initManager(applicationContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationHandler.createNotificationChannel(listofNotificationChannels)
        }
        AlarmHandler.initManager(applicationContext)

    }
}