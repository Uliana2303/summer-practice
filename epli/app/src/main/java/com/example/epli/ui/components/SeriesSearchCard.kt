package com.example.epli.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epli.R
import com.example.epli.ui.screens.main.search.models.SeriesInfo
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.epli.network.ApiRoutes
import com.example.epli.network.models.SeriesDTO
import com.example.epli.ui.theme.AppTheme

@Composable
fun SeriesSearchCard(
    seriesInfo: SeriesDTO,
    modifier: Modifier = Modifier
) {
    ProvideTextStyle(value = androidx.compose.ui.text.TextStyle(color = AppTheme.colors.white)) {


        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(160.dp)
                .width(90.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "${ApiRoutes.GET_POSTER}${seriesInfo.poster}"),
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
                    text = seriesInfo.name,
                    fontSize = 24.sp
                )
                Text(
                    text = "Серий: ${seriesInfo.series_count}"
                )
                Text(
                    text = "Год выхода: ${seriesInfo.year}"
                )
                Text(
                    text = "Время: ${seriesInfo.seria_time} мин."
                )
            }
        }
    }
}