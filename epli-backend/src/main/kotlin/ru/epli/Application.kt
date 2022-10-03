package ru.epli

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.epli.features.login.configureLoginRouting
import ru.epli.features.register.configureRegisterRouting
import ru.epli.features.series.configureSeriesRouting
import ru.epli.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/epli", driver = "org.postgresql.Driver",
        user = "postgres", password = "admin")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureSeriesRouting()
        configureSerialization()
    }.start(wait = true)
}
