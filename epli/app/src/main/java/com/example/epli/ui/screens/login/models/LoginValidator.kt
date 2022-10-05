package com.example.epli.ui.screens.login.models

import com.example.epli.R

val EMAIL_PATTERN = android.util.Patterns.EMAIL_ADDRESS

fun validateEmail(email: String): Boolean {
    return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

object LoginValidator {
    fun getUsernameErrorOrNull(input: String): Int? {
        val regex = Regex("^[a-zA-Z0-9_.!]*\$")
        val matches = regex.matches(input)

        return when {
            input.length > 25 || input.length < 2 -> R.string.username_wrong_length
            !matches -> R.string.username_not_valid
            else -> null
        }

    }

    fun getEmailErrorOrNull(input: String): Int? {
        return when {
            !validateEmail(input) -> R.string.email_not_valid
            else -> null
        }
    }

    fun getPasswordErrorOrNull(input: String): Int? {
        val matches = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%\\.*#?&]{7,}$").matches(input)

        return when {
            input.isEmpty() -> R.string.password_required
            input.length > 25 || input.length < 7 -> R.string.password_wrong_length
            !matches -> R.string.password_not_valid
            else -> null
        }
    }

    fun getPasswordRepeatErrorOrNull(input: String, inputRepeat: String): Int? {
        return when {
            input != inputRepeat -> R.string.different_passwords
            else -> null
        }
    }
}