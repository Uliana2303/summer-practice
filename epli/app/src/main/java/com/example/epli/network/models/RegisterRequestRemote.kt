package com.example.epli.network.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RegisterRequestRemote(
    val login: String,
    val email: String,
    val password: String
)

