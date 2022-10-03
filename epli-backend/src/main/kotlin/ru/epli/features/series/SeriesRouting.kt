package ru.epli.features.series

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.epli.cache.TokenCache
import ru.epli.cache.inMemoryCache
import java.util.*

fun Application.configureSeriesRouting() {

    routing {
        post("/games/search") {
            val seriesController = SeriesController(call)
            seriesController.performSearch()
        }

        post("/series/add") {
            val seriesController = SeriesController(call)
            seriesController.addSeries()
        }
    }
}