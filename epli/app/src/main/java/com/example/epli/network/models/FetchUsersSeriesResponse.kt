package com.example.epli.network.models

@kotlinx.serialization.Serializable
data class FetchUsersSeriesResponse(
    val usersSeries: List<UsersHasSeriesDTO>
)
