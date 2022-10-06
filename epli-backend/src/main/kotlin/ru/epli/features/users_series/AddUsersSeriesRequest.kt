package ru.epli.features.users_series

import kotlinx.serialization.Serializable

@Serializable
data class AddUsersSeriesRequest(
    val userId: Int,
    val seriesId: Int
)