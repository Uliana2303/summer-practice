package com.example.epli.network.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestRemote(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String
)