package com.example.epli.network.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUsersSeriesRequest(
    val userId: Int,
    val seriesId: Int,
    val viewed: Int?,
    val rating: Int?,
    val notes: String?
)
