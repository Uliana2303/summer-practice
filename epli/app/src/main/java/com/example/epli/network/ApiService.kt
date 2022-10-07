package com.example.epli.network

import com.example.epli.common.AuthResult
import com.example.epli.common.LoginResult
import com.example.epli.common.RegisterResult
import com.example.epli.network.models.*
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

interface ApiService {

    suspend fun tryLogin(
        email: String,
        password: String
    ): LoginResult

    suspend fun tryRegister(
        login: String,
        email: String,
        password: String,

    ): RegisterResult

    suspend fun tryAuth(token: String) : AuthResult

    suspend fun fetchGenres() : GenresRespond

    suspend fun fetchSeriesByQueryAndId(
        query: String,
        genresIdList: List<Int>
    ) : FetchSeriesResponse

    suspend fun addUsersSeries(
        userId: Int,
        seriesId: Int
    )

    suspend fun updateUsersSeries(
        userId: Int,
        seriesId: Int,
        viewed: Int?,
        rating: Int?,
        notes: String?
    )

    suspend fun getUserIdByToken(
        token : String
    ) : UserIdResponse

    suspend fun getEmailByToken(
        token: String
    ) : EmailResponse

    suspend fun fetchUsersSeries(
        userId: Int
    ) : FetchUsersSeriesResponse

    suspend fun getUsernameByToken(
        token: String
    ) : UsernameResponse

    suspend fun getUssSeriesInfoById(
        userId: Int,
        seriesId: Int
    ) : GetUsersSeriesInfoResponse

    suspend fun getSeriesInfoById(
        id: Int
    ) : GetSeriesInfoRespond

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    // Logging
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    // JSON
                    install(ContentNegotiation) {
                        json()
                        //or serializer = KotlinxSerializer()
                    }
                    // Timeout
                    install(HttpTimeout) {
                        requestTimeoutMillis = 15000L
                        connectTimeoutMillis = 15000L
                        socketTimeoutMillis = 15000L
                    }
                    // Apply to all requests
                    defaultRequest {
                        // Parameter("api_key", "some_api_key")
                        // Content Type
//                        if (method != HttpMethod.Get)
//                        contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                    }
                }
            )
        }

        private val json = kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    }
}