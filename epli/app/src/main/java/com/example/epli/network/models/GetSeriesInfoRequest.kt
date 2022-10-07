package com.example.epli.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GetSeriesInfoRequest(
    val id: Int
)

