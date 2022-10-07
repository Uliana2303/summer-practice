package com.example.epli.network.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchSeriesRequest(
    val searchQuery: String,
    val genresIdList: List<Int>
)
