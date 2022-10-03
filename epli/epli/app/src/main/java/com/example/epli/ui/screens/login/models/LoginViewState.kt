package com.example.epli.ui.screens.login.models

enum class LoginSubState {
    SingIn, SingUp
}

data class LoginViewState(
    val loginSubState: LoginSubState = LoginSubState.SingIn,
    val emailValue: String = "",
    val passwordValue: String = "",
    val loginValue: String = "",
    val isLoginProgress: Boolean = false
)