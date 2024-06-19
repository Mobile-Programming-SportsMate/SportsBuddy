package com.example.sportsbuddy.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportsbuddy.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamMatchDetailScreen(
    navController: NavController,
    listChatroom: MutableList<List<String>>,
    userViewModel: UserViewModel,
    title: String,
    time: String,
    content: String,
    sport: String,
    experience: String
) {
    val focusManager = LocalFocusManager.current
    val user by userViewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .addFocusCleaner(focusManager)
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material.Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.material.Text(
                text = "개인 매칭",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        androidx.compose.material.Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
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
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(text = user.nickname, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "운동 시간대 : $time", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "종목 : $sport", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "운동구력 : $experience", fontSize = 14.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = content,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val listA = mutableListOf("김민재","알겠습니다!","2024.06.19")
                    listChatroom.add(listA)
                    navController.navigate("chatScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "채팅하기")
            }
        }
    }
}