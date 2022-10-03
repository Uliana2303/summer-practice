package ru.epli.features.series

import kotlinx.serialization.Serializable

@Serializable
data class FetchSeriesRequest(
    val searchQuery: String
)