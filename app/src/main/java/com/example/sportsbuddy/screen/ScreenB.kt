package com.example.sportsbuddy.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportsbuddy.R


@Composable
fun ScreenB(navController: NavHostController) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "매칭 페이지",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.Center)
        )

        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("Select") }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(bottom = 60.dp)
        ) {
            Button(
                onClick = { expanded = true },
                modifier = Modifier
                    .size(width = 50.dp, height = 50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.light_gray)
                )
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
//                        colorResource(id = R.color.theme_blue02),
                        colorResource(id = R.color.white),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                DropdownMenuItem(onClick = {
                    selectedOption = "개인"
                    expanded = false
                    Toast.makeText(context,"개인 메이트 등록",Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(context,"팀 메이트 등록",Toast.LENGTH_SHORT).show()
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
}