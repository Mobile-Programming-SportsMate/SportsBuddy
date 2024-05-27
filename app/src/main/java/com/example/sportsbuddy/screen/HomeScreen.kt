package com.example.sportsbuddy.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { navController.navigate("navigation_bar") }) {
            Text(text = "Go to Navigation Bar")
        }
    }
}