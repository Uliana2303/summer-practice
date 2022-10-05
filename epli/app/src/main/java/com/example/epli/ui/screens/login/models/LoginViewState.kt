package com.example.epli.ui.screens.login.models

enum class LoginSubState {
    SignIn, SignUp
}

data class DialogOptions(
    val openDialog: Boolean,
    val titleId: Int?,
    val textId: Int?,
)

data class LoginViewState(
    val loginSubState: LoginSubState = LoginSubState.SignIn,
    val emailValue: String = "",
    val passwordValue: String = "",
    val passwordRepeatValue: String = "",
    val loginValue: String = "",
    val isLoginProgress: Boolean = false,
    val dialogOptions: DialogOptions = DialogOptions(
        openDialog = false,
        titleId = null,
        textId = null
    ),
    val authenticationPassed: Boolean = false
)