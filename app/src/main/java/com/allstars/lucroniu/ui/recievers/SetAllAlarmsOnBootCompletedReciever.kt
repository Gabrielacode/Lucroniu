package com.allstars.lucroniu.ui.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SetAllAlarmsOnBootCompletedReciever :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_BOOT_COMPLETED){

        }
    }
}