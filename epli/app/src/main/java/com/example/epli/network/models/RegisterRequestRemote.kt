package com.example.epli.network.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RegisterRequestRemote(
    val email: String,
    val name: String,
    val secondName: String,
    val patronymicName: String,
    val password: String
)

