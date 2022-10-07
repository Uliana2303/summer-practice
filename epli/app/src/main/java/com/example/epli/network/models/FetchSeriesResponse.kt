package com.example.epli.network.models

import kotlinx.serialization.Serializable


@Serializable
data class SeriesDTO(
    val series_id: Int,
    val name: String,
    val series_count: Int,
    val description: String,
    val year: Int,
    val seria_time: Int,
    val poster: String
)

@Serializable
data class FetchSeriesResponse(
    val seriesList: List<SeriesDTO>
)