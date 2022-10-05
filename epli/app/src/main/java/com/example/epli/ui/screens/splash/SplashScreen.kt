package com.example.epli.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.epli.R
import com.example.epli.navigation.NavigationTree
import com.example.epli.ui.theme.Palette

@Composable
fun SplashScreen(navController: NavController) {
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.black)
            ){
        item{
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_icon_1024),
                contentDescription = null,
                modifier = Modifier.padding(top = 200.dp)
                    .height(190.dp)
                    .width(190.dp),
                contentScale = ContentScale.FillBounds,
            )
        }
        item{
            Text(modifier = Modifier.padding(top = 1.dp),
                text = "epli",
                style = TextStyle(
                    color = Palette.blue,
                    fontSize = 60.sp,
                    fontStyle = FontStyle.Italic,
                )
            )
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        navController.navigate(NavigationTree.Login.name) {
            popUpTo(0)
        }
    })
}