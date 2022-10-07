package ru.epli.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.epli.database.tokens.TokenDTO
import ru.epli.database.tokens.TokenModel
import ru.epli.database.users.UserModel
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin(){
        val recieve = call.receive<LoginReceiveRemote>()
        val userDTO = UserModel.fetchUser(recieve.email)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "Такого пользователя не существует.")
        } else {
            if (userDTO.password == recieve.password) {
                val token = UUID.randomUUID().toString()
                TokenModel.insert(
                    TokenDTO(
                        id = null,
                        email = recieve.email,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Неверный пароль.")
            }
        }
    }

    suspend fun getEmailByToken() {
        val request = call.receive<TokenReceiveRemote>()

        val email = TokenModel.getEmailByToken(request.token)

        call.respond(EmailResponseRemote(email = email!!))
    }

    suspend fun getUsernameByToken() {
        val request = call.receive<TokenReceiveRemote>()

        val email = TokenModel.getEmailByToken(request.token)
        val username = UserModel.getUsernameByEmail(email!!)


        call.respond(UsernameResponseRemote(username = username))
    }

    suspend fun getUserIdByToken() {
        val request = call.receive<TokenReceiveRemote>()

        val email = TokenModel.getEmailByToken(request.token)
        val userId = if (email == null) {
            null
        } else {
            UserModel.getUserIdByEmail(email)
        }

        call.respond(UserIdResponse(userId))
    }
}