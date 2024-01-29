package com.example.mobile_computing_homework

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun EmptyScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Nothing here yet", fontSize = 50.sp)
        Spacer(modifier = Modifier.height(65.dp))
        Image(
            painter = painterResource(R.drawable.potatoes),
            contentDescription = "Potatoes",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(65.dp))
        Button(onClick =
        {
            navController.navigate("Conversation screen"){
                popUpTo("Conversation screen"){inclusive = true}
            }
        }) {
            Text(text = "Return", fontSize = 40.sp)
        }
    }
}