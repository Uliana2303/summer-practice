package com.example.epli.common

sealed class LoginResult {
    data class Ok(val token: String) : LoginResult()
    object IncorrectCredentials : LoginResult()
    object SomethingWentWrong : LoginResult()
}