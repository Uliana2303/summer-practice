package com.example.epli.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GetSeriesInfoRespond(
    val seriesInfo: SeriesDTO?
)

