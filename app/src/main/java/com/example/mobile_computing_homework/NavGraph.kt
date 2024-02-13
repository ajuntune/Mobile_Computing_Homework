package com.example.mobile_computing_homework

import SampleData
import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Conversation screen"){
        composable(route = "Profile screen"){
            ProfileScreen(navController)
        }
        composable(route = "Conversation screen"){
            Conversation(SampleData.conversationSample, navController)
        }
    }
}