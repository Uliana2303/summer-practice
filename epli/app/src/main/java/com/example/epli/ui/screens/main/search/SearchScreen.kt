package com.example.epli.ui.screens.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.epli.ui.components.DCheckBox
import com.example.epli.ui.components.DFilledTextField
import com.example.epli.ui.components.SeriesSearchCard
import com.example.epli.ui.screens.main.search.models.SearchEvent
import com.example.epli.ui.screens.main.search.models.SearchViewState
import com.example.epli.ui.theme.AppTheme

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onSeriesClicked: (seriesId: Int) -> Unit
) {

    val viewState = viewModel.viewState.observeAsState(SearchViewState())

    ProvideTextStyle(value = TextStyle(color = AppTheme.colors.white)) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.black)
        ) {
            item{
                DFilledTextField(
                    value = viewState.value.searchQueryValue,
                    placeholder = "Введите название",
                    onValueChange = {
                        viewModel.obtainEvent(
                            SearchEvent.SearchQueryChanged(it)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            "",
                            tint = AppTheme.colors.white
                        )
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
            item {
                if (viewState.value.genresCheckBoxes.size > 1) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Column {
                            viewState.value.genresCheckBoxes.toList().let {
                                it.subList(0, it.size / 2 + 1).forEach { checkboxEntry ->
                                    DCheckBox(
                                        checked = checkboxEntry.second,
                                        onCheckedChange = {
                                            viewModel.obtainEvent(
                                                SearchEvent.GenreCheckBoxClicked(
                                                    checkboxEntry.first.id
                                                )
                                            )
                                        },
                                        title = checkboxEntry.first.genre_name,
                                        modifier = Modifier.height(32.dp)
                                    )
                                }
                            }
                        }
                        Column {
                            viewState.value.genresCheckBoxes.toList().let {
                                it.subList(it.size / 2 + 1, it.size).forEach { checkboxEntry ->
                                    DCheckBox(
                                        checked = checkboxEntry.second,
                                        onCheckedChange = {
                                            viewModel.obtainEvent(
                                                SearchEvent.GenreCheckBoxClicked(
                                                    checkboxEntry.first.id
                                                )
                                            )
                                        },
                                        title = checkboxEntry.first.genre_name,
                                        modifier = Modifier.height(32.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            viewState.value.seriesList.forEach { seriesInfo ->
                item {
                    SeriesSearchCard(
                        seriesInfo = seriesInfo,
                        modifier = Modifier.clickable(onClick = { onSeriesClicked(seriesInfo.series_id) })
                    )
                }

            }
//            LazyColumn(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                item {
//
//                }
//            }


        }
    }

    LaunchedEffect(key1 = Unit) {
//        onSeriesClicked()
    }
}