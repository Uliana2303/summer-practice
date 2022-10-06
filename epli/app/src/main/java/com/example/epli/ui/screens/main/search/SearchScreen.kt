package com.example.epli.ui.screens.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.epli.ui.components.DFilledTextField
import com.example.epli.ui.screens.main.search.models.SearchEvent
import com.example.epli.ui.screens.main.search.models.SearchViewState
import com.example.epli.ui.theme.AppTheme

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onSeriesClicked: () -> Unit
) {

    val viewState = viewModel.viewState.observeAsState(SearchViewState())

    ProvideTextStyle(value = TextStyle(color = AppTheme.colors.white)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.black)
        ) {
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

            val list = listOf(
                "Сёнен",
                "Экшен",
                "Фэнтези",
                "Сверхъестестве",
                "Школа",
                "Драма",
                "Музыка",
                "Приключения",
                "Комедия",
                "Спорт",
                "Сёдзё",
                "Романтика",
                "Повседневность",
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f)
            ) {
                items(list) { genre ->
                    Row(
                        modifier = Modifier.height(40.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {},
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = AppTheme.colors.white,
                                checkedColor = AppTheme.colors.blue,
                                checkmarkColor = AppTheme.colors.white,
                            )
                        )
                        Text(
                            text = genre
                        )
                    }
                }
            }


        }
    }

    LaunchedEffect(key1 = Unit) {
//        onSeriesClicked()
    }
}