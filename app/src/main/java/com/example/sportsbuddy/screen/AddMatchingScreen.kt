package com.example.sportsbuddy.screen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportsbuddy.UserViewModel
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.sportsbuddy.data.City
import com.example.sportsbuddy.data.District
import com.example.sportsbuddy.data.Neighborhood
import com.example.sportsbuddy.data.cities


@Composable
fun AddMatchingScreen(navController: NavHostController, isTeamMatching: Boolean, userViewModel: UserViewModel) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var sport by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    var selectedRecruitNumberItem by remember {
        mutableStateOf("1")
    }

    val recruitNumbers = (1..99).map { "$it 명" }


    var expandedRecruit by remember { mutableStateOf(false) }

    val user by userViewModel.user.collectAsState()
    val nickname = user.nickname

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .addFocusCleaner(focusManager),
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "매치 글 작성", fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "저장",
                modifier = Modifier.clickable {
//                    userViewModel.addMatchingPost(navController, context )
                    val currentTime = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(Date())
                    val matchData = MatchData(
                        nickname, title, time, content, currentTime, sport, experience,
                        if (isTeamMatching) "team" else "personal"
                    )
                    saveMatchData(matchData)
                    navController.popBackStack()
                }
            )
        }

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(text = "제목", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
        TextField(
            value = title,
            onValueChange = { title = it
//                            userViewModel.onMatchingPostTitleChange(it)
                            },
            placeholder = { Text("제목을 입력하세요") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(text = "종목 / 경력 입력", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawSportsDropdown(selectedSport = { sport = it }, selectedExperience = { experience = it })

        Spacer(modifier = Modifier.height(20.dp))
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        )
        if (isTeamMatching) {
            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Text(
                    text = "모집 인원",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(80.dp))
                Column {
                    Row {
                        Text(
                            text = selectedRecruitNumberItem,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    expandedRecruit = true
                                }
                        )

                        // 드롭다운 아이콘
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .clickable { expandedRecruit = true },
                            tint = Color.Black
                        )
                    }

                    // 드롭다운 메뉴
                    DropdownMenu(
                        expanded = expandedRecruit,
                        onDismissRequest = { expandedRecruit = false }
                    ) {
                        // 각 항목에 대한 아이템
                        recruitNumbers.forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedRecruitNumberItem = item
                                    expandedRecruit = false
//                                    userViewModel.onMatchingPostPeopleChange(item)
                                }
                            ) {
                                Text(text = item)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(text = "동네 입력", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }

        DrawEditLocation()

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(text = "운동 시간대", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
        TextField(
            value = time,
            onValueChange = { time = it },
            placeholder = { Text("원하는 시간대를 입력해주세요") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.LightGray
            )
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(text = "내용", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }

        TextField(
            value = content,
            onValueChange = { content = it
//                userViewModel.onMatchingPostDetailChange(it)
                            },
            placeholder = { Text("내용을 입력하세요") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
                .padding(horizontal = 18.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.LightGray
            )
        )


    }
}

@Composable
fun DrawSportsDropdown(userViewModel: UserViewModel) {
    var selectedSportsMenuItem by remember { mutableStateOf("종목을 선택하세요") }
    var selectedExperienceMenuItem by remember { mutableStateOf("구력을 선택하세요") }

    // 드롭다운 메뉴가 열려 있는지 여부를 저장할 MutableState
    var expandedSports by remember { mutableStateOf(false) }
    var expandedExperience by remember { mutableStateOf(false) }



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        // 선택된 항목을 보여주는 텍스트
        Column {
            Row {
                Text(
                    text = selectedSportsMenuItem,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable {
                            expandedSports = true
                        }
                )

                // 드롭다운 아이콘
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            expandedSports = true
                        },
                    tint = Color.Black
                )
            }

            // 드롭다운 메뉴
            DropdownMenu(
                expanded = expandedSports,
                onDismissRequest = { expandedSports = false }
            ) {
                // 각 항목에 대한 아이템
                listOf("축구", "야구", "농구", "헬스", "런닝", "볼링").forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedSportsMenuItem = item
                        expandedSports = false
//                        userViewModel.onMatchingPostSportChange(item)
                        selectedSport(item)
                    }) {
                        Text(text = item)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Experience selection dropdown
        Column {
            Row {
                Text(
                    text = selectedExperienceMenuItem,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable {
                            expandedExperience = true
                        }
                )

                // 드롭다운 아이콘
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { expandedExperience = true },
                    tint = Color.Black
                )
            }

            // 드롭다운 메뉴
            DropdownMenu(
                expanded = expandedExperience,
                onDismissRequest = { expandedExperience = false }
            ) {
                // 각 항목에 대한 아이템
                listOf("무경험", "1년이하", "1년~3년", "3년~5년", "5년~10년", "10년 이상").forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedExperienceMenuItem = item
                        expandedExperience = false
//                        userViewModel.onMatchingPostCareerChange(item)
                        selectedExperience(item)
                    }) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}

private fun saveMatchData(matchData: MatchData) {
    val database = FirebaseDatabase.getInstance()
    val matchesRef = database.getReference("matches")

    matchesRef.push().setValue(matchData)
}

data class MatchData(
    val nickname: String = "",
    val title: String = "",
    val time: String = "",
    val content: String = "",
    val current: String = "",
    val sport: String = "",
    val experience: String = "",
    val type: String = ""
)



@Composable
fun LocationSpinner3(
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
                Text(
                    selectedDistrict?.name ?: "'구' 선택",
                    modifier = Modifier.align(Alignment.Center)
                )
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
fun DrawEditLocation3(userViewModel: UserViewModel) {
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

    LocationSpinner3(
        cities = cities,
        selectedCity = cities.find { it.name == user.matchingPost.selectedCity },
        onCitySelected = { city ->
            userViewModel.onMatchingPostCityChange(city.name)
            userViewModel.onMatchingPostDistrictChange("")
            userViewModel.onMatchingPostNeighborhoodChange("")
        },
        selectedDistrict = cities.find { it.name == user.matchingPost.selectedCity }?.districts?.find { it.name == user.matchingPost.selectedDistrict },
        onDistrictSelected = { district ->
            userViewModel.onMatchingPostDistrictChange(district.name)
            userViewModel.onMatchingPostNeighborhoodChange("")
        },
        selectedNeighborhood = cities.find { it.name == user.matchingPost.selectedCity }?.districts?.find { it.name == user.matchingPost.selectedDistrict }?.neighborhoods?.find { it.name == user.matchingPost.selectedNeighborhood },
        onNeighborhoodSelected = { neighborhood ->
            userViewModel.onMatchingPostNeighborhoodChange(neighborhood.name)
        }
    )
}