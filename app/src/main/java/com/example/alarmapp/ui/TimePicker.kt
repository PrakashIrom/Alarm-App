package com.example.alarmapp.ui

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@SuppressLint("DefaultLocale")
@Composable
fun TimePickerScreen(selectedTime: MutableState<String>){

    val context = LocalContext.current
    val calender = Calendar.getInstance()

    TimePickerDialog(
        context,
        {
            _,hour,minute ->
            selectedTime.value = String.format("%02d:%02d", hour, minute)
        },
        calender.get(Calendar.HOUR_OF_DAY),
        calender.get(Calendar.MINUTE),
        true
        ).show()

}