package ru.epli.features.series

import kotlinx.serialization.Serializable

@Serializable
data class CreateSeriesRequest(
    val series_id: Int,
    val name: String,
    val series_count: Int,
    val description: String,
    val year: Int,
    val seria_time: Int,
    val poster: String
)

@Serializable
data class CreateSeriesResponse(
    val series_id: Int,
    val name: String,
    val series_count: Int,
    val description: String,
    val year: Int,
    val seria_time: Int,
    val poster: String
)