package ru.epli.features.series

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.epli.database.series.SeriesModel
import ru.epli.database.series.mapToCreateSeriesResponse
import ru.epli.database.series.mapToSeriesDTO
import ru.epli.utils.TokenCheck

class SeriesController(private val call: ApplicationCall) {

    suspend fun getSeriesInfoById() {
        val request = call.receive<GetSeriesInfoRequest>()
        call.respond(GetSeriesInfoRespond ( SeriesModel.getSeriesById(request.id)))
    }

    suspend fun performSearch() {
        val request = call.receive<FetchSeriesRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())) {
//            if (request.searchQuery.isBlank()) {
//                call.respond(SeriesModel.fetchAllSeries())
//            } else {
            call.respond(
                FetchSeriesResponse(
                    SeriesModel.fetchSeriesByQueryAndGenresIds(request.searchQuery, request.genresIdList)
                )
                /*.filter { it.name.contains(request.searchQuery, ignoreCase = true) }*/
            )
//            }
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    //"login": "uta",
    //"password": "renmin_23031308",
    //"email": "uliana"
    suspend fun addSeries() {
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateSeriesRequest>()
            val game = request.mapToSeriesDTO()
            SeriesModel.insert(game)
            call.respond(game.mapToCreateSeriesResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}