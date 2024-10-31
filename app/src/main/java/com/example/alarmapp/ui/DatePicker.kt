package com.example.alarmapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import android.app.DatePickerDialog
import androidx.compose.runtime.MutableState

@Composable
fun DatePickerScreen(selectedDate: MutableState<String>){

    val context = LocalContext.current
    val calender = Calendar.getInstance()

    DatePickerDialog(
        context,
        {
            _, year, month, day ->
            selectedDate.value = "$day/${month+1}/$year"
        },
        calender.get(Calendar.YEAR),
        calender.get(Calendar.MONTH),
        calender.get(Calendar.DAY_OF_MONTH)
    ).show()

}