package com.example.epli.ui.screens.login.models

sealed class LoginEvent {
    data class UsernameChanged(val value : String) : LoginEvent()
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    data class PasswordRepeatChanged(val value: String) : LoginEvent()
    object SignInClicked : LoginEvent()
    object SignUpClicked : LoginEvent()
    object DialogOkClicked : LoginEvent()
}