package ru.epli.features.users_series

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUsersSeriesRequest(
    val userId: Int,
    val seriesId: Int,
    val viewed: Int?,
    val rating: Int?,
    val notes: String?
)