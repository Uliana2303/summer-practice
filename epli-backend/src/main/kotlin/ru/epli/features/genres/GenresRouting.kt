package ru.epli.features.genres

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.epli.features.register.RegisterController

fun Application.configureGenresRouting() {

    routing {
        get("/genres") {
            val genresController = GenresController(call)
            genresController.fetchExistingGenres()
        }
    }
}