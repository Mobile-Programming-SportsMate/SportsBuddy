package com.example.sportsbuddy.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsbuddy.R
import com.example.sportsbuddy.data.City
import com.example.sportsbuddy.data.District
import com.example.sportsbuddy.data.Neighborhood
import com.example.sportsbuddy.data.cities

@Preview
@Composable
fun EditProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .padding(start = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null, modifier = Modifier.align(Alignment.CenterVertically))
            Text(text = "프로필 변경", fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp))
        }
        // 구분선 추가
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(37.dp))

        DrawNickNameTextField()

        Spacer(modifier = Modifier.height(37.dp))

        DrawSportsCard()

        Spacer(modifier = Modifier.height(37.dp))

        DrawEditLocation()

        Spacer(modifier = Modifier.height(63.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp)
                .padding(end = 37.dp)
        ) {
            Text(text = "설정 완료")
        }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var textState by remember { mutableStateOf(TextFieldValue(value)) }

    Box(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = textState,
            onValueChange = {
                if (it.text.length <= 12) { // Check if the length is within 12 characters
                    textState = it
                    onValueChange(textState.text)
                }
            },
            modifier = modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            placeholder = { Text(text = "변경할 닉네임", fontSize = 14.sp) }
        )
        Text(
            text = "${textState.text.length}/12", modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 60.dp)
        )
    }
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
                    .height(60.dp)
            ) {
                Text(selectedCity?.name ?: "선택하세요", modifier = Modifier.align(Alignment.Center))
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
                    .clickable { isDistrictDropdownExpanded = !isDistrictDropdownExpanded }
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
                    .height(60.dp)
            ) {
                Text(selectedDistrict?.name ?: "선택하세요", modifier = Modifier.align(Alignment.Center))
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
                    .clickable { isNeighborhoodDropdownExpanded = !isNeighborhoodDropdownExpanded }
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
                    .height(60.dp)
            ) {
                Text(
                    selectedNeighborhood?.name ?: "선택하세요",
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
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var selectedDistrict by remember { mutableStateOf<District?>(null) }
    var selectedNeighborhood by remember { mutableStateOf<Neighborhood?>(null) }
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