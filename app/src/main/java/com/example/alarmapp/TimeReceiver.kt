package com.example.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class TimeReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
     //   val action = intent?.action
        val serviceIntent = Intent(context, TimeService::class.java)
        ContextCompat.startForegroundService(context!!, serviceIntent)
    }
}