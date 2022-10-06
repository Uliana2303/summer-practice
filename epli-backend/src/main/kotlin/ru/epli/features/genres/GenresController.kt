package ru.epli.features.genres

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.epli.database.genres.GenresModel

class GenresController(private val call: ApplicationCall) {
    suspend fun fetchExistingGenres() {
        val genresList = GenresModel.fetchGenres()

        if (genresList.isNotEmpty()) {
            call.respond(GenresResponse(genresList))
        } else {
            call.respond(HttpStatusCode.BadGateway, "Genres list is empty somehow")
        }
    }
}