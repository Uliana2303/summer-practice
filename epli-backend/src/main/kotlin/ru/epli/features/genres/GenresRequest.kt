package ru.epli.features.genres

import kotlinx.serialization.Serializable

@Serializable
data class GenresRequest(
    val seriesId: Int
)
