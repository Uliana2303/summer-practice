package ru.epli.features.users_series

import kotlinx.serialization.Serializable

@Serializable
data class GetUsersSeriesInfoRequest(
    val userId: Int,
    val seriesId: Int
)