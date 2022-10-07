package ru.epli.features.login

import kotlinx.serialization.Serializable

@Serializable
data class TokenReceiveRemote(
    val token: String
)

@Serializable
data class EmailResponseRemote(
    val email: String
)

@Serializable
data class UsernameResponseRemote(
    val username: String
)

@Serializable
data class UserIdResponse(
    val userId: Int?
)

@Serializable
data class LoginReceiveRemote(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String
)