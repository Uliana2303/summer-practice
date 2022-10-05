package com.example.epli.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.epli.navigation.BottomNavHost
import com.example.epli.ui.components.BottomNavComposable

@Composable
fun MainScreen(
    
) {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavComposable(navController = navController)
        }
    ) {
       Box(modifier = Modifier.padding(it))  {
           BottomNavHost(navController = navController)
       }
    }
}