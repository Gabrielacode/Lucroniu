package com.allstars.lucroniu.ui.utils

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.allstars.lucroniu.data.entities.Priority
import com.allstars.lucroniu.domain.models.TaskModel
import com.allstars.lucroniu.ui.MainActivity
import com.allstars.lucroniu.ui.services.TaskService

object AlarmHandler {
    var manager : AlarmManager? = null

   fun initManager(context: Context){
       manager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
       if(manager == null){
           Toast.makeText(context,"Alarms  cannot be made on used on this device", Toast.LENGTH_SHORT).show()
       }
   }

    fun setAlarmforTask(taskModel: TaskModel , context: Context){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.i("TaskService",manager?.canScheduleExactAlarms().toString())
        }
        Log.i("TaskService","Alarm Created with id ${taskModel.id}")
        manager?.setExact(AlarmManager.RTC_WAKEUP,taskModel.endTime.time, PendingIntent.getService(context,
         taskModel.id.toInt(),
         Intent(context,TaskService::class.java),PendingIntent.FLAG_IMMUTABLE)
     )

    }

    fun cancelAlarmforTask(taskModel: TaskModel, context: Context){
        Log.i("TaskService","Alarm Cancelled with id ${taskModel.id}")
        manager?.cancel(PendingIntent.getService(context, taskModel.id.toInt(),
            Intent(context,TaskService::class.java),PendingIntent.FLAG_IMMUTABLE))
    }
    fun cancelAll(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            manager?.cancelAll()
        }
    }
}