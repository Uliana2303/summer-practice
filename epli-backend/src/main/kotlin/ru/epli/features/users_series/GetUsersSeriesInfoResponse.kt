package ru.epli.features.users_series

import kotlinx.serialization.Serializable
import ru.epli.database.users_has_series.UsersHasSeriesDTO

@Serializable
data class GetUsersSeriesInfoResponse(
    val usersSeriesInfo: UsersHasSeriesDTO?
)