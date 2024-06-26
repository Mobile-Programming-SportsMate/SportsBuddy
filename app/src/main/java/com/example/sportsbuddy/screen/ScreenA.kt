package com.example.sportsbuddy.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

//private val viewModel: MainViewModel by viewModels()
//val conversation = viewModel.conversation.collectAsState()
@Composable
fun ScreenA(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text="채팅 리스트",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold
        )

    }
}
