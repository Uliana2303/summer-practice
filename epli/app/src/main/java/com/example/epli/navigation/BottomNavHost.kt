package com.example.epli.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.epli.ui.components.BottomNavItem
import com.example.epli.ui.screens.main.collections.CollectionsScreen
import com.example.epli.ui.screens.main.profile.ProfileScreen
import com.example.epli.ui.screens.main.search.SearchScreen

@Composable
fun BottomNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Profile.screenRoute) {
        composable(BottomNavItem.Profile.screenRoute){
            ProfileScreen()
        }
        composable(BottomNavItem.Search.screenRoute){
            SearchScreen()
        }
        composable(BottomNavItem.Collections.screenRoute){
            CollectionsScreen()
        }
    }
}