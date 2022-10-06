package ru.epli.features.genres

import kotlinx.serialization.Serializable
import ru.epli.database.genres.GenresDTO

@Serializable
data class GenresResponse(
    val genresList: List<GenresDTO>
)