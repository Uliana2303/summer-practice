package com.example.epli.network.models

import kotlinx.serialization.Serializable


@Serializable
data class GetUsersSeriesInfoRequest(
    val userId: Int,
    val seriesId: Int
)
