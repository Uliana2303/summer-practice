package com.example.epli.ui.screens.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.epli.ui.components.SeriesProfileCard
import com.example.epli.ui.screens.main.profile.models.ProfileViewState
import com.example.epli.ui.theme.Palette

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onSeriesClicked: (seriesId: Int) -> Unit
) {

    val viewState = viewModel.viewState.observeAsState(ProfileViewState())

    ProvideTextStyle(value = TextStyle(color = Palette.white)) {

        Column(
            modifier = Modifier.fillMaxSize().background(Palette.black)
        ) {
            Column(
//            modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = viewState.value.usernameValue,
                    fontSize = 24.sp
                )
                Text(
                    text = viewState.value.emailValue
                )
            }
            LazyColumn{
                viewState.value.seriesList.forEach {
                    item {
                        SeriesProfileCard(
                            seriesName = it.seriesName,
                            seriesCount = it.seriesCount,
                            viewedCount = it.viewedCount,
                            rating = it.rating,
                            poster = it.poster,
                            modifier = Modifier.clickable{onSeriesClicked(it.seriesId)}
                        )
                    }
                }
            }
        }


    }
}