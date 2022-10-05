package com.example.epli.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.epli.navigation.NavigationTree
import com.example.epli.ui.screens.login.LoginScreen
import com.example.epli.ui.screens.login.LoginViewModel
import com.example.epli.ui.screens.main.MainScreen
import com.example.epli.ui.screens.splash.SplashScreen

@Composable
fun NavHostInit(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationTree.Splash.name) {
        composable(NavigationTree.Splash.name) { SplashScreen(navController) }
        composable(NavigationTree.Login.name) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                loginViewModel = loginViewModel,
                onAuthenticationPassed = {
                    navController.navigate(NavigationTree.Main.name) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(NavigationTree.Main.name) {
            MainScreen()
        }
    }
}