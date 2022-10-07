package com.example.epli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import com.example.epli.ui.screens.NavHostInit
import com.example.epli.ui.theme.EpliTheme
import com.example.epli.ui.theme.Palette
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpliTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Palette.black,
                        darkIcons = false
                    )
                }


                NavHostInit()

                }
            }
        }
    }

