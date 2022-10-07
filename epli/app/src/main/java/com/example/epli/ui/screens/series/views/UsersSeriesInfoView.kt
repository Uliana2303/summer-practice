package com.example.epli.ui.screens.series.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.ui.components.DFilledTextField
import com.example.epli.ui.components.RatingWidget

@Composable
fun UsersSeriesInfoView(
    viewedValue: Int,
    seriesCount: Int,
    onViewedIncreaseClicked: () -> Unit,
    onViewedDecreaseClicked: () -> Unit,
    ratingValue: Int,
    notesValue: String,
    onRatingClicked: (Int) -> Unit,
    onNotesChanged: (String) -> Unit
) {
    Text(
        text = "Просмотрено: $viewedValue/$seriesCount",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Button(onClick = onViewedDecreaseClicked, modifier = Modifier.weight(1f)) {
            Text(
                text = "-",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
        }
        Button(onClick = onViewedIncreaseClicked, modifier = Modifier.weight(1f)) {
            Text(
                text = "+",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
    Text(
        text = "Ваша оценка:",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
    RatingWidget(
        ratingValue = ratingValue,
        onRatingClicked = onRatingClicked
    )
    Text(
        text = "Заметки:",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
    DFilledTextField(
        value = notesValue,
        placeholder = "",
        onValueChange = onNotesChanged,
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        singleLine = false,
    )
}