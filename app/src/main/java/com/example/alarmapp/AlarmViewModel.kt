package com.example.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AlarmViewModel : ViewModel() {
    // This state will control when to show the permission request
    var showPermissionRequest = mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.S)
    fun setAlarm(context: Context, alarmTimeInMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Check if exact alarms can be scheduled
        if (alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(context, TimeReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Set the alarm
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeInMillis, pendingIntent)
        } else {
            // Request permission to schedule exact alarms
            showPermissionRequest.value = true
        }
    }

    // Function to reset the permission request state
    fun resetPermissionRequest() {
        showPermissionRequest.value = false
    }

    // Function to get the Intent for requesting exact alarm permission
    @RequiresApi(Build.VERSION_CODES.S)
    fun getExactAlarmPermissionIntent(context: Context): Intent {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
            data = Uri.parse("package:${context.packageName}")
        }
        return intent
    }
}
