package ru.epli.features.series

import kotlinx.serialization.Serializable

@Serializable
data class GetSeriesInfoRequest(
    val id: Int
)
