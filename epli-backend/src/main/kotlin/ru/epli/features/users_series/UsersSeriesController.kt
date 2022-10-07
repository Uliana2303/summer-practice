package ru.epli.features.users_series

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.utils.io.errors.*
import ru.epli.database.users_has_series.UsersHasSeriesDTO
import ru.epli.database.users_has_series.UsersHasSeriesModel

class UsersSeriesController(private val call: ApplicationCall) {
    suspend fun insertUsersSeries() {
        val request = call.receive<AddUsersSeriesRequest>()

        try {
            UsersHasSeriesModel.insert(
                UsersHasSeriesDTO(
                    user_id = request.userId,
                    series_id = request.seriesId,
                )
            )
            call.respond(HttpStatusCode.OK)
        } catch (e: IOException) {
            call.respond(HttpStatusCode.InternalServerError)
        }

    }

    suspend fun updateUsersSeries() {
        val request = call.receive<UpdateUsersSeriesRequest>()

        try {
            if (request.viewed != null) {
                UsersHasSeriesModel.updateViewed(request.userId, request.seriesId, request.viewed)
            }
            if (request.rating != null) {
                UsersHasSeriesModel.updateRating(request.userId, request.seriesId, request.rating)
            }
            if (request.notes != null) {
                UsersHasSeriesModel.updateNotes(request.userId, request.seriesId, request.notes)
            }
            call.respond(HttpStatusCode.OK)
        } catch (e : IOException) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    suspend fun getUsersSeriesInfoById() {
        val request = call.receive<GetUsersSeriesInfoRequest>()

        call.respond(GetUsersSeriesInfoResponse(
            usersSeriesInfo = UsersHasSeriesModel.getUsersSeriesInfoById(request.userId, request.seriesId)
        ))
    }

    suspend fun fetchUsersSeries() {
        val request = call.receive<FetchUsersSeriesRequest>()

        call.respond(FetchUsersSeriesResponse(
            usersSeries = UsersHasSeriesModel.fetchUsersSeries(request.userId)
        ))
    }

}