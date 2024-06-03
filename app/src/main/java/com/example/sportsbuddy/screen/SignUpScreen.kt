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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportsbuddy.R
import com.example.sportsbuddy.data.City
import com.example.sportsbuddy.data.District
import com.example.sportsbuddy.data.Neighborhood
import com.example.sportsbuddy.data.cities


@Composable
fun SignUpScreen(navController: NavController) {

//    var id by remember { mutableStateOf("") }
//    var nickname by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordConfirm by remember { mutableStateOf("") }
//    var gender by remember { mutableStateOf("") }
//    var birthDate by remember { mutableStateOf("") }
//    var selectedInterests by remember { mutableStateOf(listOf<String>()) }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .addFocusCleaner(focusManager)
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

            DrawIdTextField()

            Spacer(modifier = Modifier.height(37.dp))

            DrawNickNameTextField()

            Spacer(modifier = Modifier.height(37.dp))

            DrawPasswordTextField()

            Spacer(modifier = Modifier.height(37.dp))

            DrawPasswordCheckTextField()

            Spacer(modifier = Modifier.height(37.dp))

            DrawGenderCard()

            Spacer(modifier = Modifier.height(37.dp))

            DrawBirthTextField()

            Spacer(modifier = Modifier.height(37.dp))

            DrawSportsCard()

            Spacer(modifier = Modifier.height(37.dp))

            DrawEditLocation()

            Spacer(modifier = Modifier.height(63.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                    Toast.makeText(context, "성공적으로 가입되었습니다!", Toast.LENGTH_SHORT).show()
                    navController.navigate("login")
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
fun CustomTextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, showText : String) {
    var textState by remember { mutableStateOf(TextFieldValue(value)) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = {
                if (it.text.length <= 12) {
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
            placeholder = { Text(text = showText, fontSize = 14.sp) }
        )
        Text(
            text = "${textState.text.length}/12",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 60.dp)
        )
    }
}

@Composable
fun DrawIdTextField() {
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
        Text(text = "중복확인", fontSize = 14.sp, color = colorResource(id = R.color.lime50))
    }

    CustomTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "아이디 입력"
    )
}

@Composable
fun DrawNickNameTextField() {
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
        Text(text = "중복확인", fontSize = 14.sp, color = colorResource(id = R.color.lime50))
    }

    CustomTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "닉네임 입력"
    )
}

@Composable
fun DrawPasswordTextField() {
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

    CustomTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "비밀번호 입력"
    )
}


@Composable
fun DrawPasswordCheckTextField() {
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

    CustomTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "비밀번호 확인"
    )
}



@Composable
fun DrawGenderCard(modifier: Modifier = Modifier) {
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
        horizontalAlignment = Alignment.CenterHorizontally, // Center the sports cards
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            GenderCard("여성")
            Spacer(modifier = Modifier.width(8.dp))
            GenderCard("남성")
        }
    }
}


@Composable
fun DrawBirthTextField() {
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
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp)
            .padding(end = 37.dp)
            .height(55.dp),
        showText = "생년월일 입력"
    )
}



@Composable
fun DrawSportsCard(modifier: Modifier = Modifier) {
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
        Text(text = "관심종목", fontSize = 20.sp)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Center the sports cards
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            SportsCard("헬스")
            Spacer(modifier = Modifier.width(8.dp))
            SportsCard("축구")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            SportsCard("농구")
            Spacer(modifier = Modifier.width(8.dp))
            SportsCard("야구")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            SportsCard("런닝")
            Spacer(modifier = Modifier.width(8.dp))
            SportsCard("필라테스")
        }
    }
}

@Composable
fun SportsCard(text: String) {
    var isSelected by remember { mutableStateOf(false) }

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
            .clickable { isSelected = !isSelected }
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
fun GenderCard(text: String) {
    var isSelected by remember { mutableStateOf(false) }

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
            .clickable { isSelected = !isSelected }
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
fun LocationSpinner(
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
                            Toast
                                .makeText(context, "'시'를 먼저 선택하세요", Toast.LENGTH_SHORT)
                                .show()
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
                            Toast
                                .makeText(context, "'구'를 먼저 선택하세요", Toast.LENGTH_SHORT)
                                .show()
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
fun DrawEditLocation() {

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

    var selectedCity by remember { mutableStateOf<City?>(null) }
    var selectedDistrict by remember { mutableStateOf<District?>(null) }
    var selectedNeighborhood by remember { mutableStateOf<Neighborhood?>(null) }

    LocationSpinner(
        cities = cities,
        selectedCity = selectedCity,
        onCitySelected = { selectedCity = it },
        selectedDistrict = selectedDistrict,
        onDistrictSelected = { selectedDistrict = it },
        selectedNeighborhood = selectedNeighborhood,
        onNeighborhoodSelected = { selectedNeighborhood = it }
    )
}

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}