package com.example.sportsbuddy.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsbuddy.R

@Preview
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.img_login_background),
            contentDescription = "background"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(600.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .padding(end = 10.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.buttonBlue),
                    contentColor = colorResource(id = R.color.grey)
                )

            ) {
                Text(text = "로그인", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .padding(end = 10.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.buttonBlue),
                    contentColor = colorResource(id = R.color.grey)
                )
            ) {

                Text(text = "회원가입", fontSize = 14.sp)
            }
        }
    }
}



