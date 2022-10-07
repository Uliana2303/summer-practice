package com.example.epli.ui.screens.series.models

import com.example.epli.network.models.SeriesDTO

data class SeriesViewState(
    val seriesDTO: SeriesDTO? = null,
//    TODO
//    val genresList: List<String> = emptyList(),
    val viewed: Int = 0,
    val ratingValue: Int = 0,
    val notesValue: String = ""
)