package com.example.epli.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.epli.ui.components.BottomNavItem
import com.example.epli.ui.screens.main.collections.CollectionsScreen
import com.example.epli.ui.screens.main.profile.ProfileScreen
import com.example.epli.ui.screens.main.search.SearchScreen
import com.example.epli.ui.screens.main.search.SearchViewModel
import com.example.epli.ui.screens.series.SeriesScreen
import com.example.epli.ui.screens.series.SeriesViewModel

@Composable
fun BottomNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Search.screenRoute) {
        composable(BottomNavItem.Profile.screenRoute){
            ProfileScreen()
        }
        composable(BottomNavItem.Search.screenRoute){
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = searchViewModel,
                onSeriesClicked = {
                    navController.navigate(
                        NavigationTree.SeriesDetails.name
                    )
                }
            )
        }
        composable(BottomNavItem.Collections.screenRoute){
            CollectionsScreen()
        }
        composable(
            NavigationTree.SeriesDetails.name
        ) {
            val seriesViewModel = hiltViewModel<SeriesViewModel>()

            SeriesScreen(
                viewModel = seriesViewModel
            )
        }
    }
}