package com.example.epli.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.epli.ui.theme.AppTheme

@Composable
fun BottomNavComposable(navController: NavController) {
    val items = listOf(
        BottomNavItem.Collections,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = AppTheme.colors.blue,
        contentColor = AppTheme.colors.white
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    when (currentRoute == item.screenRoute) {
                        true -> Icon(item.selectedIcon, "")
                        else -> Icon(item.unselectedIcon, "")
                    }
                },
                label = {
                    Text(
                        text = item.title
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}