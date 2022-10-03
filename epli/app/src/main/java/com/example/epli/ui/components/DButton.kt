package com.example.epli.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.epli.ui.theme.Palette

@Composable
fun DButton(){
    Button(onClick = { /*TODO*/ },
    modifier = Modifier.background(color = Palette.blue, shape = RoundedCornerShape(4.dp)),
    enabled = false) {
        Text( text = "Войти",
        style = TextStyle(
            color = Palette.white
        )
        )
    }
}