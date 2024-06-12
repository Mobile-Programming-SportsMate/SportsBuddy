package com.example.sportsbuddy.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportsbuddy.R

@Composable
fun HomeScreen(navController: NavHostController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.main_image),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        id = R.color.theme_blue
                    )
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "로그인", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("signup") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        id = R.color.theme_blue
                    )
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "회원가입", color = Color.Black)
            }
        }
    }
}
