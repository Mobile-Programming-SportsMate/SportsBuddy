package com.example.sportsbuddy.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun AddMatchingScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .addFocusCleaner(focusManager),
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "매치 글 작성", fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "저장")
        }

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)){
            Text(text = "제목", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
        TextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text("제목을 입력하세요") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)){
            Text(text = "종목 / 경력 입력", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawSportsDropdown()
        Spacer(modifier = Modifier.height(20.dp))
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)){
            Text(text = "동네 입력", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }

        DrawEditLocation()

        Spacer(modifier = Modifier.height(20.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)){
            Text(text = "운동 시간대", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
        TextField(
            value = time,
            onValueChange = { time = it },
            placeholder = { Text("원하는 시간대를 입력해주세요") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.LightGray
            )
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)){
            Text(text = "내용", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }

        TextField(
            value = content,
            onValueChange = { content = it },
            placeholder = { Text("내용을 입력하세요") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
                .padding(horizontal = 18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.LightGray
            )
        )


    }
}

@Composable
fun DrawSportsDropdown() {
    var selectedSportsMenuItem by remember {
        mutableStateOf<String?>(null)
    }
    var selectedExperienceMenuItem by remember {
        mutableStateOf<String?>(null)
    }

    // 드롭다운 메뉴가 열려 있는지 여부를 저장할 MutableState
    var expandedSports by remember { mutableStateOf(false) }
    var expandedExperience by remember { mutableStateOf(false) }



    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 18.dp)){
        // 선택된 항목을 보여주는 텍스트
        Column {
            Row {
                Text(
                    text = selectedSportsMenuItem ?: "종목을 선택하세요",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // 드롭다운 아이콘
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { expandedSports = true },
                    tint = Color.Black
                )
            }

            // 드롭다운 메뉴
            DropdownMenu(
                expanded = expandedSports,
                onDismissRequest = { expandedSports = false }
            ) {
                // 각 항목에 대한 아이템
                listOf("축구", "야구", "농구", "헬스", "런닝", "볼링").forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedSportsMenuItem = item
                        expandedSports = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }




        Column {
            Row {
                Text(
                    text = selectedExperienceMenuItem ?: "구력을 선택하세요",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // 드롭다운 아이콘
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { expandedExperience = true },
                    tint = Color.Black
                )
            }

            // 드롭다운 메뉴
            DropdownMenu(
                expanded = expandedExperience,
                onDismissRequest = { expandedExperience = false }
            ) {
                // 각 항목에 대한 아이템
                listOf("무경험", "1년이하", "1년~3년", "3년~5년", "5년~10년", "10년 이상").forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedExperienceMenuItem = item
                        expandedExperience = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}

