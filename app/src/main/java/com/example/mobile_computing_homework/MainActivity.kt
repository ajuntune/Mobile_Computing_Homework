package com.example.mobile_computing_homework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobile_computing_homework.ui.theme.Mobile_Computing_HomeworkTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

// some of the code is copied from the tutorial
// https://developer.android.com/jetpack/compose/tutorial
// scroll items function from
// https://codingwithrashid.com/how-to-add-scrolling-in-android-jetpack-compose/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ScrollItems(Message("Potato", "List of potato"))
            }
        }
    }
}

data class Message(val title: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Column() {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.potato),
                contentDescription = "Potato",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = msg.title,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color(0, 128, 0, 255)
                )
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.body2,
                    color = Color(128, 0, 0, 255)
                )
            }
        }
    }
}

@Composable
fun List(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun ScrollItems(msg: Message) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        repeat(20) {
            MessageCard(msg)
        }
    }
}


