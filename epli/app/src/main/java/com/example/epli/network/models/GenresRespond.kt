package com.example.epli.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GenresDTO(
    val id: Int = 0,
    val genre_name: String = ""
)

@Serializable
data class GenresRespond(
    val genresList: List<GenresDTO>
)