package ru.epli.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.epli.cache.TokenCache
import ru.epli.cache.inMemoryCache
import java.util.*
import kotlin.math.log

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
        post("/get_user_id") {
            val loginController = LoginController(call)
            loginController.getUserIdByToken()
        }
        post("/get_user_email_by_token") {
            val loginController = LoginController(call)
            loginController.getEmailByToken()
        }
        post("/get_username_by_token") {
            val loginController = LoginController(call)
            loginController.getUsernameByToken()
        }
    }
}