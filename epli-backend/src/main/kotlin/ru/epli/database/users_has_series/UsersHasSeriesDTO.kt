package ru.epli.database.users_has_series

import kotlinx.serialization.Serializable

@Serializable
data class UsersHasSeriesDTO(
    val user_id: Int,
    val series_id: Int,
    val series_viewed: Int = 0,
    val rating: Int = 0,
    val notes: String? = null
)
