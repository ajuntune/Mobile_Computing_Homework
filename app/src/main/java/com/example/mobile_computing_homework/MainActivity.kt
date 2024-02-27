package com.example.mobile_computing_homework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notificationService = Notification(this)
            Column {
                //Nav()
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick =
                    {
                        notificationService.showNotification()
                    }) {
                    Text(text = "Notification", fontSize = 40.sp)
                }
            }
        }
    }
}



