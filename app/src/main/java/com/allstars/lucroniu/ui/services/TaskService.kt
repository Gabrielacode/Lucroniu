package com.allstars.lucroniu.ui.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class TaskService :Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"Service Called",Toast.LENGTH_SHORT).show()

        return START_REDELIVER_INTENT

    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}

enum class TaskServiceIntentAction{

}