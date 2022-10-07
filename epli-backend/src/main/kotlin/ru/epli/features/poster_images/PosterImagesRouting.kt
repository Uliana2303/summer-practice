package ru.epli.features.poster_images

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import ru.epli.features.series.SeriesController
import java.io.File
import java.io.File.*

const val POSTER_IMAGES_BASE = "/home/mitrofanov-alexey-bvt2002/Android/Projects/summer-practice/epli-backend/src/main/resources/poster_images"

fun Application.configurePosterImagesRouting() {

    routing {
        static("/series/poster/") {
//            staticRootFolder = File("/home/mitrofanov-alexey-bvt2002/Android/Projects/summer-practice/epli-backend/src/main/resources/poster_images")
            staticRootFolder = File(POSTER_IMAGES_BASE)
            files(".")
        }

    }
}