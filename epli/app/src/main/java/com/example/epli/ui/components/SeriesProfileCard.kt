package com.example.epli.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.epli.network.ApiRoutes
import com.example.epli.ui.theme.AppTheme

@Composable
fun SeriesProfileCard(
    seriesName: String,
    seriesCount: Int,
    viewedCount: Int,
    rating: Int,
    poster: String,
    modifier: Modifier = Modifier

) {
    ProvideTextStyle(value = TextStyle(color = AppTheme.colors.white)) {


        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(160.dp)
                .width(90.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "${ApiRoutes.GET_POSTER}${poster}"),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp),
                contentScale = ContentScale.Crop
            )
//        Image(
//            bitmap = seriesInfo.poster.asImageBitmap(),
//            contentDescription = "",
//            modifier = Modifier.fillMaxHeight(),
//            contentScale = ContentScale.Crop
//        )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = seriesName,
                    fontSize = 24.sp
                )
                Text(
                    text = "Просмотрено: $viewedCount/$seriesCount",
                )
                RatingWidget(ratingValue = rating, onRatingClicked = {})
            }
        }
    }
}