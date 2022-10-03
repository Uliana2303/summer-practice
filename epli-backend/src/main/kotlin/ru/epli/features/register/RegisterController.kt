package ru.epli.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.epli.database.tokens.TokenDTO
import ru.epli.database.tokens.TokenModel
import ru.epli.database.users.UserDTO
import ru.epli.database.users.UserModel
import ru.epli.utils.isValidEmail
import java.util.*

class RegisterController(private val call: ApplicationCall){

    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Почта указана неверно.")
        }
        val userDTO = UserModel.fetchUser(registerReceiveRemote.email)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "Такой пользователь уже существует.")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                UserModel.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        id = null
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Такой пользователь уже существует.")
            }
            TokenModel.insert(
                TokenDTO(
                    id = null,
                    email = registerReceiveRemote.email,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }

    }
}