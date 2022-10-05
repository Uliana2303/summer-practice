package com.example.epli.common

sealed class AuthResult {
    data class Ok(val email:String) : AuthResult()
    object Err : AuthResult()
}