package ru.epli.features.series

import kotlinx.serialization.Serializable
import ru.epli.database.series.SeriesDTO

@Serializable
data class GetSeriesInfoRespond(
    val seriesInfo: SeriesDTO?
)
