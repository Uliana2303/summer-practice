package com.example.epli.common

sealed class RegisterResult {
    data class Ok(val token: String) : RegisterResult()
    object EmailAlreadyExists : RegisterResult()
    object EmailIsNotValid : RegisterResult()
    object SomethingWentWrong : RegisterResult()
}
