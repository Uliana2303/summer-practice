package ru.epli.features.users_series

import kotlinx.serialization.Serializable

@Serializable
data class FetchUsersSeriesRequest(
    val userId: Int
)
