package com.example.sportsbuddy.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportsbuddy.R

@Composable
fun LoginScreen(navController: NavController) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val idFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        IdInputFieldWithIcon(id, onValueChange = { id = it }, idFocusRequester, passwordFocusRequester)

        Spacer(modifier = Modifier.height(37.dp))

        PasswordInputFieldWithIcon(password, onValueChange = { password = it }, passwordFocusRequester)

        Spacer(modifier = Modifier.height(100.dp))

        LoginButton(id, password, context, navController)
    }
}

@Composable
fun IdInputFieldWithIcon(value: String, onValueChange: (String) -> Unit, focusRequester: FocusRequester, nextFocusRequester: FocusRequester) {
    Column {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.width(14.dp))
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = Color.DarkGray,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "아이디", fontSize = 20.sp)
        }

        IdTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp, end = 37.dp)
                .height(55.dp),
            showText = "아이디 입력",
            focusRequester = focusRequester,
            nextFocusRequester = nextFocusRequester
        )
    }
}

@Composable
fun IdTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    showText: String,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester
) {
    var textState by remember { mutableStateOf(TextFieldValue(value)) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
                onValueChange(it.text)
            },
            modifier = modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .focusRequester(focusRequester),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.DarkGray
            ),
            placeholder = { Text(text = showText, fontSize = 14.sp) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    nextFocusRequester.requestFocus()
                }
            )
        )
    }
}

@Composable
fun PasswordInputFieldWithIcon(value: String, onValueChange: (String) -> Unit, focusRequester: FocusRequester) {
    Column {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.width(14.dp))
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = Color.DarkGray,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "비밀번호", fontSize = 20.sp)
        }

        PasswordTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp, end = 37.dp)
                .height(55.dp),
            showText = "비밀번호 입력",
            focusRequester = focusRequester
        )
    }
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    showText: String,
    focusRequester: FocusRequester
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var textState by remember { mutableStateOf(TextFieldValue(value)) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
                onValueChange(it.text)
            },
            modifier = modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .focusRequester(focusRequester),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.DarkGray
            ),
            placeholder = { Text(text = showText, fontSize = 14.sp) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
    }
}

@Composable
fun LoginButton(id: String, password: String, context: Context, navController: NavController) {
    Button(
        onClick = {
            if (id == "admin" && password == "1234") {
                navController.popBackStack()
                Toast.makeText(context, "성공적으로 로그인되었습니다!", Toast.LENGTH_SHORT).show()
                navController.navigate("navigation_bar")
            } else {
                Toast.makeText(context, "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 100.dp, end = 100.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.theme_blue)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(text = "로그인")
    }
}
