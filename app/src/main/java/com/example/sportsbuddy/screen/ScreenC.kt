package com.example.sportsbuddy.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportsbuddy.UserViewModel
import com.example.sportsbuddy.data.User

@Composable
fun ScreenC(navController: NavHostController, userViewModel: UserViewModel) {
    val user by userViewModel.user.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "마이페이지", fontSize = 40.sp, fontWeight = FontWeight.Medium)
            UserInfo(user)
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(bottom = 60.dp)
        ) {
            Row(modifier = Modifier.clickable {
                navController.navigate("edit_profile")
            }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "프로필 변경", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun UserInfo(user: User) {
    Text(text = "아이디: ${user.id}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
    Text(text = "닉네임: ${user.nickname}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
    Text(text = "성별: ${user.gender}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
    Text(text = "생년월일: ${user.birthDate}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
    Text(text = "관심종목: ${user.selectedInterests.joinToString(", ")}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
    Text(text = "지역: ${user.selectedCity} ${user.selectedDistrict} ${user.selectedNeighborhood}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
}
