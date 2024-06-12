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

