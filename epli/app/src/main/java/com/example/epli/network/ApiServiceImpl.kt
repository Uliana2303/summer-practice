package com.example.epli.network


import com.example.epli.common.AuthResult
import com.example.epli.common.LoginResult
import com.example.epli.common.RegisterResult
import com.example.epli.network.models.*

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun tryLogin(email: String, password: String): LoginResult {
        try {
            val response = client.post {
                url(ApiRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                setBody(LoginRequestRemote(email, password))
            }

            return when (response.status) {
                HttpStatusCode.BadRequest -> LoginResult.IncorrectCredentials
                HttpStatusCode.OK -> LoginResult.Ok(token = response.body<LoginResponseRemote>().token)
                else -> LoginResult.SomethingWentWrong
            }
        } catch (e: IOException) {

            return LoginResult.SomethingWentWrong
        }
    }

    override suspend fun tryRegister(
        login: String,
        email: String,
        password: String,
    ): RegisterResult {
        try {
        val response = client.post {
            url(ApiRoutes.REGISTER)
            contentType(ContentType.Application.Json)
            setBody(RegisterRequestRemote(login, email, password))
        }


        return when (response.status) {
                HttpStatusCode.BadRequest -> RegisterResult.EmailIsNotValid
                HttpStatusCode.Conflict -> RegisterResult.EmailAlreadyExists
                HttpStatusCode.OK -> RegisterResult.Ok(response.body<RegisterResponseRemote>().token)
                else -> RegisterResult.SomethingWentWrong
            }
        } catch (e: IOException) {
            return RegisterResult.SomethingWentWrong
        }
    }

    override suspend fun tryAuth(token: String): AuthResult {
        return try {
        val response = client.post {
            url(ApiRoutes.AUTH)
            contentType(ContentType.Application.Json)
            setBody(AuthRequestRemote(token))
        }


            when (response.status){
                HttpStatusCode.OK -> AuthResult.Ok(response.body<AuthResponseRemote>().email)
                else -> AuthResult.Err
            }
        } catch (e: IOException) {
            AuthResult.Err
        }
    }

    override suspend fun fetchGenres(): GenresRespond {
        return try {
            val response = client.get{
                url(ApiRoutes.FETCH_GENRES)

            }

            when (response.status) {
                HttpStatusCode.OK -> response.body()
                else -> GenresRespond(emptyList())
            }
        } catch (e : IOException) {
            GenresRespond(emptyList())
        }
    }

    override suspend fun fetchSeriesByQueryAndId(
        query: String,
        genresIdList: List<Int>
    ): FetchSeriesResponse {
//        return try {
            val response = client.post {
                url(ApiRoutes.FETCH_SERIES)
                contentType(ContentType.Application.Json)
                headers {
                    append("Bearer-Authorization", "46737cbb-0071-42fa-8f4e-0c3ed7074957")
                }
                setBody(FetchSeriesRequest(query, genresIdList))
            }

            return when (response.status) {
                HttpStatusCode.OK -> response.body()
                else -> FetchSeriesResponse(seriesList = emptyList())
            }
//        } catch (e: IOException) {
//            FetchSeriesResponse(seriesList = emptyList())
//        }
    }

    override suspend fun getSeriesInfoById(id: Int): GetSeriesInfoRespond {
        val response = client.post{
            url(ApiRoutes.SERIES_INFO)
            contentType(ContentType.Application.Json)
            setBody(GetSeriesInfoRequest(id = id))
        }

        return when (response.status) {
            HttpStatusCode.OK -> response.body()
            else -> GetSeriesInfoRespond(seriesInfo = null)
        }
    }

    override suspend fun getUssSeriesInfoById(
        userId: Int,
        seriesId: Int
    ): GetUsersSeriesInfoResponse {
        val response = client.post{
            url(ApiRoutes.USERS_SERIES_INFO)
            contentType(ContentType.Application.Json)
            setBody(GetUsersSeriesInfoRequest(userId, seriesId))
        }

        return when (response.status) {
            HttpStatusCode.OK -> response.body()
            else -> GetUsersSeriesInfoResponse(usersSeriesInfo = null)
        }
    }

    override suspend fun getUserIdByToken(token: String): UserIdResponse {
        val response = client.post {
            url(ApiRoutes.GET_USER_ID)
            contentType(ContentType.Application.Json)
            setBody(TokenRequest(token))
        }

        return when (response.status) {
            HttpStatusCode.OK -> response.body()
            else -> UserIdResponse(null)
        }
    }

    override suspend fun addUsersSeries(userId: Int, seriesId: Int) {
        val response = client.post{
            url(ApiRoutes.ADD_USERS_SERIES)
            contentType(ContentType.Application.Json)
            setBody(AddUsersSeriesRequest(userId, seriesId))
        }

    }

    override suspend fun updateUsersSeries(userId: Int, seriesId: Int, viewed: Int?, rating: Int?, notes: String?) {
        val response = client.post{
            url(ApiRoutes.UPDATE_USERS_SERIES)
            contentType(ContentType.Application.Json)
            setBody(UpdateUsersSeriesRequest(
                userId = userId,
                seriesId = seriesId,
                viewed = viewed,
                rating = rating,
                notes = notes
            ))
        }
    }

    override suspend fun getEmailByToken(token: String): EmailResponse {
        val response = client.post{
            url(ApiRoutes.GET_USER_EMAIL_BY_TOKEN)
            contentType(ContentType.Application.Json)
            setBody(
                TokenRequest(token = token)
            )
        }

        return response.body()
    }

    override suspend fun getUsernameByToken(token: String): UsernameResponse {
        val response = client.post{
            url(ApiRoutes.GET_USERNAME_BY_TOKEN)
            contentType(ContentType.Application.Json)
            setBody(
                TokenRequest(token = token)
            )
        }

        return response.body()
    }

    override suspend fun fetchUsersSeries(userId: Int): FetchUsersSeriesResponse {
        val response = client.post{
            url(ApiRoutes.USERS_SERIES)
            contentType(ContentType.Application.Json)
            setBody(
                UserIdResponse(
                    userId = userId
                )
            )
        }

        return response.body()
    }
}