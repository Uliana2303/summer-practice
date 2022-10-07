package com.example.epli.ui.screens.main.collections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.epli.ui.theme.Palette

@Composable
fun CollectionsScreen(

) {
    Column(
        modifier = Modifier.fillMaxSize().background(Palette.black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Этот экран еще не реализован :(",
            color = Palette.white,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black
        )
    }
}