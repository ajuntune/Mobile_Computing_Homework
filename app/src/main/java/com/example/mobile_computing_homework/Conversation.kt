package com.example.mobile_computing_homework

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Message(val title: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Column() {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.potato),
                contentDescription = "Potato",
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            var isExpanded by remember { mutableStateOf(false) }

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.title,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color(0, 128, 0, 255)
                )
                Text(
                    text = msg.body,
                    color = Color(128, 0, 0, 255),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>, navController: NavController) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
        item { Button(
            modifier = Modifier.fillMaxWidth(),
            onClick =
        {
            navController.navigate("Empty screen")
        }) {
            Text(text = "Next screen", fontSize = 40.sp)
        } }
    }
}
