package com.example.sportsbuddy.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Button(onClick = { navController.navigate("navigation_bar") }) {
//                Text(text = "Go to Navigation Bar")
//            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("login") }) {
                Text(text = "로그인")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("signup") }) {
                Text(text = "회원가입")
            }
        }
    }
}