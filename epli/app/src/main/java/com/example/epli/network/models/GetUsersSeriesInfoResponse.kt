package com.example.epli.network.models

import kotlinx.serialization.Serializable

@Serializable
data class UsersHasSeriesDTO(
    val user_id: Int,
    val series_id: Int,
    val series_viewed: Int = 0,
    val rating: Int = 0,
    val notes: String? = null
)


@Serializable
data class GetUsersSeriesInfoResponse(
    val usersSeriesInfo: UsersHasSeriesDTO?
)