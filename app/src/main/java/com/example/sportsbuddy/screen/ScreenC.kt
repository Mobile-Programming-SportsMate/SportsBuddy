package com.example.sportsbuddy.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sportsbuddy.UserViewModel

@Composable
fun ScreenC(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val user by userViewModel.user.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "마이 페이지",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(text = "아이디: ${user.id}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "닉네임: ${user.nickname}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "성별: ${user.gender}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "생년월일: ${user.birthDate}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "관심종목: ${user.selectedInterests.joinToString(", ")}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "지역설정: ${user.selectedCity} ${user.selectedDistrict} ${user.selectedNeighborhood}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
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
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "프로필 변경", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
