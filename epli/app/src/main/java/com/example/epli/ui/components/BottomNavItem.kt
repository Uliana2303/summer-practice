package com.example.epli.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(val title: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector, val screenRoute: String) {
    Collections("Коллекции", Icons.Filled.Favorite, Icons.Default.FavoriteBorder,"Collections"),
    Search("Поиск", Icons.Filled.Search, Icons.Outlined.Search,"Search"),
    Profile("Профиль", Icons.Filled.Person, Icons.Outlined.Person,"Profile")
}