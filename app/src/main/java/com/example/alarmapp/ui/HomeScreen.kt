package com.example.alarmapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(){

    val showTime = remember{mutableStateOf(false)}
    val showDate = remember{mutableStateOf(false)}
    val selectedTime = remember{mutableStateOf("Set Time")}
    val selectedDate = remember{mutableStateOf("Set Date")}

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
        }

    if(showTime.value){
        TimePickerScreen(selectedTime)
    }
    else if(showDate.value){
        DatePickerScreen(selectedDate)
    }

}