package ru.epli.database.series_has_genres

import kotlinx.serialization.Serializable

@Serializable
data class SeriesHasGenresDTO(
    val genre_id: Int,
    val series_id: Int
)