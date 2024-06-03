package com.example.sportsbuddy.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SPORTSBUDDY") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Add action */ }) {
                Text("+")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("개인 매칭") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("팀 매칭") }
                )
            }
            when (selectedTab) {
                0 -> PersonalMatchList(navController)
                1 -> TeamMatchList(navController)
            }
        }
    }
}

@Composable
fun PersonalMatchList(navController: NavController) {
    LazyColumn {
        items(5) { index ->
            MatchItem(navController, "personal")
        }
    }
}

@Composable
fun TeamMatchList(navController: NavController) {
    LazyColumn {
        items(5) { index ->
            MatchItem(navController, "team")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchItem(navController: NavController, matchType: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                when (matchType) {
                    "personal" -> navController.navigate("personalMatchDetail")
                    "team" -> navController.navigate("teamMatchDetail")
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "닉네임", fontSize = 14.sp, color = Color.Gray)
                Text(text = "제목제목제목제목...", fontSize = 16.sp, color = Color.Black)
                Text(text = "글 내용 글 내용 사이즈 넘어가면...", fontSize = 14.sp, color = Color.Gray)
                Text(text = "2024.05.20", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}