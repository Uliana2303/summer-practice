package ru.epli.database.genres

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class GenresDTO(
    val id: Int,
    val genre_name: String
)