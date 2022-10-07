package ru.epli.features.series

import kotlinx.serialization.Serializable
import ru.epli.database.series.SeriesDTO

@Serializable
data class FetchSeriesResponse(
    val seriesList: List<SeriesDTO>
)