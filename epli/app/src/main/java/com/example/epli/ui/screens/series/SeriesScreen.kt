package com.example.epli.ui.screens.series

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.epli.R
import com.example.epli.network.ApiRoutes
import com.example.epli.ui.screens.series.models.SeriesEvent
import com.example.epli.ui.screens.series.models.SeriesViewState
import com.example.epli.ui.screens.series.views.UsersSeriesInfoView
import com.example.epli.ui.theme.AppTheme

@Composable
fun SeriesScreen(
    viewModel: SeriesViewModel,
    onBackPressed: () -> Unit
) {

    BackHandler {
        viewModel.obtainEvent(
            SeriesEvent.ScreenClosed
        )
        onBackPressed()
    }

    val viewState = viewModel.viewState.observeAsState(SeriesViewState())

    ProvideTextStyle(TextStyle(color = AppTheme.colors.white)) {


        LazyColumn(
            modifier = Modifier
                .background(AppTheme.colors.black)
                .fillMaxSize()
        ) {
            item {
                Row() {
                    Box(
                        modifier = Modifier
                            .width(136.dp)
                            .aspectRatio(9f / 16f)

                    ) {
                        AsyncImage(
                            model = "${ApiRoutes.GET_POSTER}${viewState.value.seriesDTO?.poster}",
                            contentDescription = "poster",
                            modifier = Modifier
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "${viewState.value.seriesDTO?.name}",
                            fontSize = 24.sp
                        )
                        Text(
                            text = "Год: ${viewState.value.seriesDTO?.year}",
                            modifier = Modifier.padding(top = 16.dp)
                        )
//                       TODO
//                        Text(
//                            text = "Жанры: аниме, боевик, фэнтези, ужасы",
//                            modifier = Modifier.padding(top = 8.dp)
//
//                        )
                        Text(
                            text = "Время: ${viewState.value.seriesDTO?.seria_time} мин.",
                            modifier = Modifier.padding(top = 8.dp)

                        )
                        Text(
                            text = "Серий: ${viewState.value.seriesDTO?.series_count}",
                            modifier = Modifier.padding(top = 8.dp)

                        )
                    }
                }
            }

            item(
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Описание: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "${viewState.value.seriesDTO?.description}"
                        )
                    when (viewState.value.is_watching) {
                        true -> UsersSeriesInfoView(
                            viewedValue = viewState.value.viewed,
                            seriesCount = viewState.value.seriesDTO!!.series_count,
                            onViewedIncreaseClicked = {
                                                      viewModel.obtainEvent(SeriesEvent.IncreaseViewedClicked)
                            },
                            onViewedDecreaseClicked = {
                                                      viewModel.obtainEvent(SeriesEvent.DecreaseViewedClicked)
                            },
                            ratingValue = viewState.value.ratingValue,
                            notesValue = viewState.value.notesValue,
                            onRatingClicked = {
                                              viewModel.obtainEvent(SeriesEvent.RatingClicked(it))
                            },
                            onNotesChanged = {
                                viewModel.obtainEvent(SeriesEvent.NotesChanged(it))
                            }
                        )
                        false -> {
                            Button(
                                onClick = { viewModel.obtainEvent(SeriesEvent.StartViewingClicked) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.start_watching)
                                )
                            }
                        }
                    }
                }

            }

        }
    }
}


