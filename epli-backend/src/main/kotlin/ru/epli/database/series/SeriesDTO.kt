package ru.epli.database.series

import kotlinx.serialization.Serializable
import ru.epli.features.series.CreateSeriesRequest
import ru.epli.features.series.CreateSeriesResponse

@Serializable
data class SeriesDTO(
    val series_id: Int,
    val name: String,
    val series_count: Int,
    val description: String,
    val year: Int,
    val seria_time: Int,
    val poster: String
)

fun CreateSeriesRequest.mapToSeriesDTO(): SeriesDTO =
    SeriesDTO(
        series_id = series_id,
        name = name,
        series_count = series_count,
        description = description,
        year = year,
        seria_time = seria_time,
        poster = poster
    )

fun SeriesDTO.mapToCreateSeriesResponse(): CreateSeriesResponse =
    CreateSeriesResponse(
        series_id = series_id,
        name = name,
        series_count = series_count,
        description = description,
        year = year,
        seria_time = seria_time,
        poster = poster
    )