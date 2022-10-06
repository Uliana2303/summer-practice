package ru.epli.features.users_series

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.epli.features.series.SeriesController

fun Application.configureUsersSeriesRouting() {

    routing {
        post("/user/series/add") {
            val usersSeriesController = UsersSeriesController(call)
            usersSeriesController.insertUsersSeries()
        }
        post("/user/series/update") {
            val usersSeriesController = UsersSeriesController(call)
            usersSeriesController.updateUsersSeries()
        }

        post("/user/series") {
            val usersSeriesController = UsersSeriesController(call)
            usersSeriesController.fetchUsersSeries()
        }

    }
}