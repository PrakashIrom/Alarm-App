package com.example.alarmapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alarmapp.AlarmViewModel
import com.example.alarmapp.R
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeScreen(onRequestPermission: () -> Unit, alarmViewModel: AlarmViewModel){

    val showPermissionRequest by alarmViewModel.showPermissionRequest
    val context = LocalContext.current
    val showTime = remember{mutableStateOf(false)}
    val showDate = remember{mutableStateOf(false)}
    val selectedTime = remember{mutableStateOf("Set Time")}
    val selectedDate = remember{mutableStateOf("Set Date")}

    LaunchedEffect(showPermissionRequest) {
        if (showPermissionRequest) {
            onRequestPermission()
            alarmViewModel.resetPermissionRequest()
        }
    }

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
            ){
            OutlinedButton(
                onClick={
                    showDate.value = true
                }, modifier = Modifier.width(130.dp)
            ) {
                Text(selectedDate.value)
            }
            OutlinedButton(
                onClick={
                    showTime.value = true
                }, modifier = Modifier.width(130.dp)
            ){
                Text(selectedTime.value)
            }
            Spacer(modifier=Modifier.size(10.dp))
            IconButton(onClick = {

                val calendar = Calendar.getInstance()
                val year = selectedDate.value.split("/")[2].toInt()
                val month = selectedDate.value.split("/")[1].toInt() - 1 // Month is zero-based
                val day = selectedDate.value.split("/")[0].toInt()
                val timeParts = selectedTime.value.split(":")
                val hour = timeParts[0].toInt()
                val minute = timeParts[1].toInt()

                calendar.set(year, month, day, hour, minute, 0) // Set the calendar with selected values
                val alarmTimeInMillis = calendar.timeInMillis
                alarmViewModel.setAlarm(context, alarmTimeInMillis)

            }) {
                Image(painterResource(R.drawable.alarm_on_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                    contentDescription = "clock",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiaryContainer)
                    )
            }
        }

    if(showTime.value){
        TimePickerScreen(selectedTime)
    }
    else if(showDate.value){
        DatePickerScreen(selectedDate)
    }

}