package com.example.sportsbuddy.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalMatchDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("개인 매칭") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Text("⬅️")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "제목입니다 제목입니다", fontSize = 20.sp, color = Color.Black)
                    Text(text = "나는 닉네임", fontSize = 16.sp, color = Color.Gray)
                    Text(text = "등록일: 2023.02.01", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "종목: 헬스", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "운동구력: 3년차", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "시간대: 평일 밤 10시 이후", fontSize = 14.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Handle chat click */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "채팅하기")
            }
        }
    }
}