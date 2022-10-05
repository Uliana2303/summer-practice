package com.example.epli.ui.screens.series

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.R
import com.example.epli.ui.components.DFilledTextField
import com.example.epli.ui.components.RatingWidget
import com.example.epli.ui.screens.series.models.SeriesEvent
import com.example.epli.ui.screens.series.models.SeriesViewState
import com.example.epli.ui.theme.AppTheme

@Composable
fun SeriesScreen(
    viewModel: SeriesViewModel
) {

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
                        modifier = Modifier.width(136.dp)
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.poster_placeholder),
                            contentDescription = "poster",
                        )
                    }
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Магическая битва",
                            fontSize = 24.sp
                        )
                        Text(
                            text = "Год: 2020",
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = "Жанры: аниме, боевик, фэнтези, ужасы",
                            modifier = Modifier.padding(top = 8.dp)

                        )
                        Text(
                            text = "Время: 23 мин.",
                            modifier = Modifier.padding(top = 8.dp)

                        )
                        Text(
                            text = "Серий: 24",
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
                        text = "Мир, в котором демоны питаются людьми, а те об этом даже не догадываются. Когда-то давно самый могущественный демон был повержен, а части его тела разбросаны по свету. Тот, кто сможет их собрать и поглотить, получит безграничную власть и даже сможет уничтожить человечество.\n" +
                                "\n" +
                                "Физически развитого старшеклассника Юдзи Итадтори волнуют насущные проблемы — почти всё время парень проводит в больнице с дедушкой, поэтому, чтобы отвязаться от настырных предложений вступить в спортивные клубы, записывается в оккультный. И внезапно оказывается в эпицентре борьбы за людские судьбы, когда его приятели снимают заклятье с некоего магического артефакта.",

                        )
                    Text(
                        text = "Просмотрено: 3/24",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Ваша оценка:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    RatingWidget(
                        ratingValue = viewState.value.ratingValue,
                        onRatingClicked = {
                            viewModel.obtainEvent(SeriesEvent.RatingClicked(it))
                        }
                    )
                    Text(
                        text = "Заметки:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    DFilledTextField(
                        value = viewState.value.notesValue,
                        placeholder = "",
                        onValueChange = {
                                        viewModel.obtainEvent(SeriesEvent.NotesChanged(it))
                        },
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                        singleLine = false,
                    )
                }

            }

        }
    }
}


