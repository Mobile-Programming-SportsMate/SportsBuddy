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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportsbuddy.R
import com.example.sportsbuddy.UserViewModel
import com.example.sportsbuddy.data.City
import com.example.sportsbuddy.data.District
import com.example.sportsbuddy.data.Neighborhood
import com.example.sportsbuddy.data.cities

@Composable
fun EditProfileScreen(navController: NavHostController,userViewModel: UserViewModel) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val user by userViewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .addFocusCleaner(focusManager)
    ) {
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
            Text(text = "프로필 변경", fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp))
        }
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(37.dp))

        EditText_NickNameTextField(user.nickname, {
            if (it != ""){
                userViewModel.onNicknameChange(it)
            }
                                                  }, {
            userViewModel.checkNicknameDuplicate(context)
        })
        Spacer(modifier = Modifier.height(37.dp))

        DrawSportsCard(user.selectedInterests) { userViewModel.onInterestsChange(it) }

        Spacer(modifier = Modifier.height(37.dp))



        DrawEditLocation2(userViewModel = userViewModel)

        Spacer(modifier = Modifier.height(63.dp))

        Button(
            onClick = {
                userViewModel.editProfile( context)
                    navController.popBackStack()
                    Toast.makeText(context, "성공적으로 변경되었습니다!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp, end = 37.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.theme_blue)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "설정 완료")
        }
    }
}

@Composable
fun EditText_NickNameTextField(nickname: String, onValueChange: (String) -> Unit, onCheckDuplicate: () -> Unit) {
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
        showText = nickname
    )
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
fun DrawEditLocation() {
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