package com.example.epli.ui.screens.main.search.models

import com.example.epli.network.models.GenresDTO
import com.example.epli.network.models.SeriesDTO

data class SearchViewState(
    val searchQueryValue: String = "",
    val genresCheckBoxes: Map<GenresDTO, Boolean> = emptyMap(),
    val seriesList: List<SeriesDTO> = emptyList()
)
