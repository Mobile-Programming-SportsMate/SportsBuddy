package com.example.sportsbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportsbuddy.model.ChatUiModel
import com.example.sportsbuddy.screen.HomeScreen
import com.example.sportsbuddy.screen.LoginScreen
import com.example.sportsbuddy.screen.SignUpScreen
import com.example.sportsbuddy.ui.theme.SportsBuddyTheme



class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val conversation = viewModel.conversation.collectAsState()

            SportsBuddyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainApp(navController, viewModel, conversation)

                }
            }
        }
    }
}

@Composable
fun MainApp(navController: NavHostController,
            viewModel: MainViewModel,
            conversation: State<List<ChatUiModel.Message>>,
) {

    val userViewModel: UserViewModel = viewModel()

    NavHost(navController, startDestination = "home_screen") {
        composable("home_screen") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController, userViewModel) }
        composable("signup") { SignUpScreen(navController, userViewModel) }
        composable("navigation_bar") { NavigationBar(userViewModel,viewModel,conversation) }
    }
}