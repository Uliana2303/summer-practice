package ru.epli.utils

import ru.epli.database.tokens.TokenModel


object TokenCheck {
    fun isTokenValid(token: String): Boolean = TokenModel.fetchTokens().firstOrNull { it.token == token } != null

    fun isTokenAdmin(token: String): Boolean = token == "46737cbb-0071-42fa-8f4e-0c3ed7074957"
}