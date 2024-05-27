package com.example.sportsbuddy.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun EditProfileScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = Color.DarkGray,
            )
            Text(text = "닉네임 변경")
        }
        TextField(value = "", onValueChange = {})
    }
}