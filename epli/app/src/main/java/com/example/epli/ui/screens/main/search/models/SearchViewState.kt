package com.example.epli.ui.screens.main.search.models

import android.graphics.Bitmap
import android.graphics.Picture
import androidx.compose.ui.graphics.ImageBitmap
import com.example.epli.network.models.GenresDTO
import com.example.epli.network.models.SeriesDTO
import kotlinx.serialization.Serializable
import java.time.Year

//@Serializable
//data class SeriesDTO(
//    val series_id: Int,
//    val name: String,
//    val series_count: Int,
//    val description: String,
//    val year: Int,
//    val seria_time: Int,
//    val poster: String
//)

data class SeriesInfo(
    val series_id: Int,
    val name: String,
    val series_count: Int,
    val year: Int,
    val seria_time: Int,
    val poster: Bitmap
)

data class SearchViewState(
    val searchQueryValue: String = "",
    val genresCheckBoxes: Map<GenresDTO, Boolean> = emptyMap(),
    val seriesList: List<SeriesDTO> = emptyList()
)
