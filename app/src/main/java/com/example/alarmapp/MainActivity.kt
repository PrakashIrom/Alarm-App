package com.example.alarmapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.alarmapp.ui.HomeScreen
import com.example.alarmapp.ui.theme.AlarmAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val alarmViewModel: AlarmViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlarmAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {TopAppBar()}
                    ) {
                    HomeScreen(onRequestPermission=::requestExactAlarmPermission, alarmViewModel = alarmViewModel)
                }
            }
        }
    }

    fun requestExactAlarmPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val intent = alarmViewModel.getExactAlarmPermissionIntent(this)
            startActivity(intent)
        }
    }
}

@Composable
fun TopAppBar(){

    Row( horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(top=50.dp)
        ){
        Text("Alarm Clock", fontWeight = FontWeight.Bold)
        Image(painterResource(R.drawable.clock), contentDescription = "clock")
    }

}


