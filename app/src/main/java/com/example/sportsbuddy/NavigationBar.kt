package com.example.sportsbuddy

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sportsbuddy.screen.AddMatchingScreen
import com.example.sportsbuddy.screen.EditProfileScreen
import com.example.sportsbuddy.screen.PersonalMatchDetailScreen
import com.example.sportsbuddy.screen.ScreenA
import com.example.sportsbuddy.screen.ScreenB
import com.example.sportsbuddy.screen.ScreenC
import com.example.sportsbuddy.screen.TeamMatchDetailScreen

sealed class Screen(val route: String, val icon: Int, val label: String) {
    object A : Screen("screen_a", R.drawable.messages, "채팅")
    object B : Screen("screen_b",  R.drawable.right_actionable, "매칭")
    object C : Screen("screen_c", R.drawable.profile, "마이페이지")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationBar(userViewModel: UserViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(navController = navController, startDestination = Screen.B.route) {
            composable(Screen.A.route) {
                ScreenA(navController)
            }

            composable(Screen.B.route) {
                ScreenB(navController)
            }

            composable(Screen.C.route) {
                ScreenC(navController, userViewModel)
            }

            composable("edit_profile") {
                EditProfileScreen(navController,userViewModel)
            }

            composable("personalMatchDetail") {
                PersonalMatchDetailScreen(navController, listChatroom )
            }

            composable("teamMatchDetail") {
                TeamMatchDetailScreen(navController)
            }

            composable("add_individual_matching") {
                AddMatchingScreen(navController, false)
            }

            composable("add_team_matching") {
                AddMatchingScreen(navController, true)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(Screen.A, Screen.B, Screen.C)
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White // Set the background color to white
    ) {
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.label,
                        tint = if (currentRoute == screen.route) Color.Black else Color.Gray // Set the icon color based on selection
                    )
                },
                label = {
                    Text(
                        text = screen.label,
                        color = if (currentRoute == screen.route) Color.Black else Color.Gray // Set the label color based on selection
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


//
//
//sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
//    object A : Screen("screen_a", Icons.Default.Home, "Screen A")
//    object B : Screen("screen_b", Icons.Default.AccountBox, "Screen B")
//    object C : Screen("screen_c", Icons.Default.Settings, "Screen C")
//
//}
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun NavigationBar(viewModel: MainViewModel,
//                  conversation: State<List<ChatUiModel.Message>>,) {
//    val navController = rememberNavController()
//
//    val list0 = mutableListOf("최승진","네네! 그럼 그렇게하죠!","2024.03.12")
//    val list1 = mutableListOf("이승한","주로 머신 위주로 운동을...","2024.04.16")
//    val list2 = mutableListOf("김민관","저는 자양동 살고 있고...","2024.04.22")
//    val list3 = mutableListOf("김재연","네~ 좋은 분 찾으세요!","2024.04.28")
//    val list4 = mutableListOf("양석기","저는 머신 위주로 해요~","2024.05.07")
//    val list5 = mutableListOf("이명환","혹시 현재 어디서 운동을...","2024.05.15")
//    val list6 = mutableListOf("박민석","안녕하세요~ 올리신 글 보고...","2024.06.03")
//    val listChatroom = mutableListOf<List<String>>(list0,list1,list2,list3
//        ,list4,list5,list6)
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController) }
//    ) {
//        NavHost(navController = navController, startDestination = Screen.B.route) {
//
//            composable(Screen.A.route) {
//                //ScreenA(navController)
//                ChattingList(navController, Modifier, listChatroom)
//            }
//
//            composable(Screen.B.route) {
//                ScreenB(navController)
//            }
//
//            composable(Screen.C.route) {
//                ScreenC(navController)
//            }
//
//            composable("chatScreen") {
//                ChatScreen(
//                    model = ChatUiModel(
//                        messages = conversation.value,
//                        addressee = ChatUiModel.Author.bot
//                    ),
//                    onSendChatClickListener = { msg -> viewModel.sendChat(msg) },
//                    modifier = Modifier,
//                    interlocutor = ChatUiModel.Author.bot.name,
//                )
//            }
//
//            composable("edit_profile") {
//                EditProfileScreen(navController)
//            }
//
//            composable("personalMatchDetail") {
//                PersonalMatchDetailScreen(navController,listChatroom)
//            }
//
//            composable("teamMatchDetail") {
//                TeamMatchDetailScreen(navController,listChatroom)
//            }
//
//
//            composable("add_individual_matching") {
//                AddMatchingScreen(navController,false)
//            }
//
//            composable("add_team_matching") {
//                AddMatchingScreen(navController,true)
//            }
//
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val screens = listOf(Screen.A, Screen.B, Screen.C)
//    val navBackStackEntry = navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry.value?.destination?.route
//
//    BottomNavigation {
//        screens.forEach { screen ->
//            BottomNavigationItem(
//                icon = { Icon(screen.icon, contentDescription = screen.label) },
//                label = { Text(screen.label) },
//                selected = currentRoute == screen.route,
//                onClick = {
//                    navController.navigate(screen.route) {
//                        popUpTo(navController.graph.startDestinationId) {
////                            saveState = true
//                            inclusive = false
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//
//}
//
