package com.example.sportsbuddy.screen

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportsbuddy.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(navController: NavController,) {
    var selectedTab by remember { mutableStateOf(0) }
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Select") }
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_matchlist_logo),
                    contentDescription = "SportsBuddy Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        },
        floatingActionButton = {
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp, bottom = 70.dp) // Adjust bottom padding to ensure FAB is above BottomNavigation
            ) {
                FloatingActionButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .size(width = 50.dp, height = 50.dp)
                        .clip(RoundedCornerShape(8.dp)),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(
                            colorResource(id = R.color.white),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    DropdownMenuItem(onClick = {
                        selectedOption = "개인"
                        expanded = false
                        Toast.makeText(context, "개인 메이트 등록", Toast.LENGTH_SHORT).show()
                        navController.navigate("add_individual_matching")
                    }
                    ) {
                        Text(
                            text = "개인",
                            modifier = Modifier.padding(8.dp),
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "팀"
                        expanded = false
                        Toast.makeText(context, "팀 메이트 등록", Toast.LENGTH_SHORT).show()
                        navController.navigate("add_team_matching")

                    }) {
                        Text(
                            text = "팀",
                            modifier = Modifier.padding(8.dp),
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(colorResource(id = R.color.theme_blue))
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
    ) {
        items(20) { index ->
            MatchItem(navController, "personal")
        }
    }
}

@Composable
fun TeamMatchList(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
    ) {
        items(20) { index ->
            MatchItem(navController, "team")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchItem(navController: NavController, matchType: String) {

    Box(
        modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clickable {
                    when (matchType) {
                        "personal" -> navController.navigate("personalMatchDetail")
                        "team" -> navController.navigate("teamMatchDetail")
                    }
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "닉네임",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "제목제목제목제목...",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "글 내용 글 내용 사이즈 넘어가면...",
                        fontSize = 14.sp, color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        text = "2024.05.20",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }
            }
        }
    }
}