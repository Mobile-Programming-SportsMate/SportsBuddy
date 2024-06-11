package com.example.sportsbuddy.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportsbuddy.R
import com.example.sportsbuddy.UserViewModel
import com.example.sportsbuddy.data.City
import com.example.sportsbuddy.data.District
import com.example.sportsbuddy.data.Neighborhood
import com.example.sportsbuddy.data.cities

@Composable
fun SignUpScreen(navController: NavController, userViewModel: UserViewModel) {
    val user by userViewModel.user.collectAsState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .addFocusCleaner2(focusManager)
    ) {
        item {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { navController.popBackStack() })
                Text(text = "회원가입", fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp))
            }
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(37.dp))

            DrawIdTextField(user.id, { userViewModel.onIdChange(it) }, { userViewModel.checkIdDuplicate(context) })

            Spacer(modifier = Modifier.height(37.dp))

            DrawNickNameTextField(user.nickname, { userViewModel.onNicknameChange(it) }, { userViewModel.checkNicknameDuplicate(context) })

            Spacer(modifier = Modifier.height(37.dp))

            DrawPasswordTextField(user.password) { userViewModel.onPasswordChange(it) }

            Spacer(modifier = Modifier.height(37.dp))

            DrawPasswordCheckTextField(user.passwordConfirm) { userViewModel.onPasswordConfirmChange(it) }

            Spacer(modifier = Modifier.height(37.dp))

            DrawGenderCard(user.gender) { userViewModel.onGenderChange(it) }

            Spacer(modifier = Modifier.height(37.dp))

            DrawBirthTextField(user.birthDate) { userViewModel.onBirthDateChange(it) }

            Spacer(modifier = Modifier.height(37.dp))

            DrawSportsCard(user.selectedInterests) { userViewModel.onInterestsChange(it) }

            Spacer(modifier = Modifier.height(37.dp))

            DrawEditLocation2(userViewModel = userViewModel)

            Spacer(modifier = Modifier.height(63.dp))

            Button(
                onClick = {
                    userViewModel.signUp(navController, context)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.theme_blue)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "회원가입")
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    showText: String,
    maxLength: Int = 12
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
                if (it.text.length <= maxLength) {
                    textState = it
                    onValueChange(it.text)
                }
            },
            modifier = modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.DarkGray
            ),
            placeholder = { Text(text = showText, fontSize = 14.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )
        Text(
            text = "${textState.text.length}/$maxLength",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 60.dp)
        )
    }
}

@Composable
fun DrawIdTextField(id: String, onValueChange: (String) -> Unit, onCheckDuplicate: () -> Unit) {
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
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "중복확인",
            fontSize = 14.sp,
            color = colorResource(id = R.color.lime50),
            modifier = Modifier.clickable { onCheckDuplicate() }
        )
    }

    CustomTextField(
        value = id,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "아이디 입력"
    )
}

@Composable
fun DrawNickNameTextField(nickname: String, onValueChange: (String) -> Unit, onCheckDuplicate: () -> Unit) {
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
        Text(text = "닉네임", fontSize = 20.sp)
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "중복확인",
            fontSize = 14.sp,
            color = colorResource(id = R.color.lime50),
            modifier = Modifier.clickable { onCheckDuplicate() }
        )
    }

    CustomTextField(
        value = nickname,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "닉네임 입력"
    )
}

@Composable
fun DrawPasswordTextField(password: String, onValueChange: (String) -> Unit) {
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

    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp)
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.DarkGray
            ),
            placeholder = { Text(text = "비밀번호 입력", fontSize = 14.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )
    }
}

@Composable
fun DrawPasswordCheckTextField(passwordConfirm: String, onValueChange: (String) -> Unit) {
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
        Text(text = "비밀번호 확인", fontSize = 20.sp)
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp)
    ) {
        OutlinedTextField(
            value = passwordConfirm,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.DarkGray
            ),
            placeholder = { Text(text = "비밀번호 확인", fontSize = 14.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )
    }
}

@Composable
fun DrawGenderCard(gender: String, onValueChange: (String) -> Unit) {
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
        Text(text = "성별", fontSize = 20.sp)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            GenderCard("여성", gender == "여성") { onValueChange("여성") }
            Spacer(modifier = Modifier.width(8.dp))
            GenderCard("남성", gender == "남성") { onValueChange("남성") }
        }
    }
}

@Composable
fun GenderCard(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 146.dp, height = 45.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Black else Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = if (isSelected) Color.Black else Color.LightGray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun DrawBirthTextField(birthDate: String, onValueChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
        Text(text = "생년월일", fontSize = 20.sp)
    }

    CustomTextField(
        value = birthDate,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "생년월일 입력",
        maxLength = 8
    )
}

@Composable
fun DrawSportsCard(selectedInterests: List<String>, onValueChange: (List<String>) -> Unit) {
    val sportsList = listOf("헬스", "축구", "농구", "야구", "런닝", "볼링")

    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.width(14.dp))
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = Color.DarkGray,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "관심종목", fontSize = 20.sp)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Center the sports cards
            modifier = Modifier.fillMaxWidth()
        ) {
            sportsList.chunked(2).forEach { rowItems ->
                Row {
                    rowItems.forEach { sport ->
                        SportsCard(text = sport, isSelected = sport in selectedInterests)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun SportsCard(text: String, isSelected: Boolean) {
    var selected by remember { mutableStateOf(isSelected) }

    Box(
        modifier = Modifier
            .size(width = 146.dp, height = 45.dp)
            .border(
                width = 1.dp,
                color = if (selected) Color.Black else Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { selected = !selected }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = if (selected) Color.Black else Color.LightGray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun LocationSpinner2(
    cities: List<City>,
    selectedCity: City?,
    onCitySelected: (City) -> Unit,
    selectedDistrict: District?,
    onDistrictSelected: (District) -> Unit,
    selectedNeighborhood: Neighborhood?,
    onNeighborhoodSelected: (Neighborhood) -> Unit
) {
    var isCityDropdownExpanded by remember { mutableStateOf(false) }
    var isDistrictDropdownExpanded by remember { mutableStateOf(false) }
    var isNeighborhoodDropdownExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        // 시 선택 드롭다운 메뉴
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clickable { isCityDropdownExpanded = !isCityDropdownExpanded }
                    .padding(5.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(selectedCity?.name ?: "'시' 선택", modifier = Modifier.align(Alignment.Center))
            }

            DropdownMenu(
                expanded = isCityDropdownExpanded,
                onDismissRequest = { isCityDropdownExpanded = false }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(onClick = {
                        onCitySelected(city)
                        isCityDropdownExpanded = false
                    }) {
                        Text(city.name)
                    }
                }
            }
        }

        // 구 선택 드롭다운 메뉴
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clickable {
                        if (selectedCity == null) {
                            Toast.makeText(context, "'시'를 먼저 선택하세요", Toast.LENGTH_SHORT).show()
                        } else {
                            isDistrictDropdownExpanded = !isDistrictDropdownExpanded
                        }
                    }
                    .padding(5.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(selectedDistrict?.name ?: "'구' 선택", modifier = Modifier.align(Alignment.Center))
            }

            DropdownMenu(
                expanded = isDistrictDropdownExpanded,
                onDismissRequest = { isDistrictDropdownExpanded = false }
            ) {
                selectedCity?.districts?.forEach { district ->
                    DropdownMenuItem(onClick = {
                        onDistrictSelected(district)
                        isDistrictDropdownExpanded = false
                    }) {
                        Text(district.name)
                    }
                }
            }
        }

        // 동 선택 드롭다운 메뉴
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clickable {
                        if (selectedDistrict == null) {
                            Toast.makeText(context, "'구'를 먼저 선택하세요", Toast.LENGTH_SHORT).show()
                        } else {
                            isNeighborhoodDropdownExpanded = !isNeighborhoodDropdownExpanded
                        }
                    }
                    .padding(5.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    selectedNeighborhood?.name ?: "'동' 선택",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            DropdownMenu(
                expanded = isNeighborhoodDropdownExpanded,
                onDismissRequest = { isNeighborhoodDropdownExpanded = false }
            ) {
                selectedDistrict?.neighborhoods?.forEach { neighborhood ->
                    DropdownMenuItem(onClick = {
                        onNeighborhoodSelected(neighborhood)
                        isNeighborhoodDropdownExpanded = false
                    }) {
                        Text(neighborhood.name)
                    }
                }
            }
        }
    }
}

@Composable
fun DrawEditLocation2(userViewModel: UserViewModel) {
    val user by userViewModel.user.collectAsState()

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
        Text(text = "지역설정", fontSize = 20.sp)
    }

    LocationSpinner2(
        cities = cities,
        selectedCity = cities.find { it.name == user.selectedCity },
        onCitySelected = { city ->
            userViewModel.onCityChange(city.name)
            userViewModel.onDistrictChange("")
            userViewModel.onNeighborhoodChange("")
        },
        selectedDistrict = cities.find { it.name == user.selectedCity }?.districts?.find { it.name == user.selectedDistrict },
        onDistrictSelected = { district ->
            userViewModel.onDistrictChange(district.name)
            userViewModel.onNeighborhoodChange("")
        },
        selectedNeighborhood = cities.find { it.name == user.selectedCity }?.districts?.find { it.name == user.selectedDistrict }?.neighborhoods?.find { it.name == user.selectedNeighborhood },
        onNeighborhoodSelected = { neighborhood ->
            userViewModel.onNeighborhoodChange(neighborhood.name)
        }
    )
}

fun Modifier.addFocusCleaner2(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}
