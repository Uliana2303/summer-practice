package com.example.epli.network


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import com.example.epli.common.AuthResult
import com.example.epli.common.LoginResult
import com.example.epli.common.RegisterResult
import com.example.epli.network.models.*

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import io.ktor.utils.io.errors.*
import java.io.File


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
        email: String,
        password: String,
        name: String,
        secondName: String,
        patronymicName: String?
    ): RegisterResult {
        try {
        val response = client.post {
            url(ApiRoutes.REGISTER)
            contentType(ContentType.Application.Json)
            setBody(RegisterRequestRemote(email, name, secondName, patronymicName ?: "", password))
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
}