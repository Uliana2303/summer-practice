package ru.epli.cache

import ru.epli.features.register.RegisterReceiveRemote

data class TokenCache(
    val email: String,
    val token: String
)
object inMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}