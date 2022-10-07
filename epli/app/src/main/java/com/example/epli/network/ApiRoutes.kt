package com.example.epli.network

object ApiRoutes {
    private const val BASE_URL = "http://10.0.2.2:8080"
    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"
    const val AUTH = "$BASE_URL/auth"
    const val FETCH_GENRES = "$BASE_URL/genres"
    const val FETCH_SERIES = "$BASE_URL/series/search"
    const val GET_POSTER = "$BASE_URL/series/poster/"
    const val SERIES_INFO = "$BASE_URL/series/info"
    const val USERS_SERIES_INFO = "$BASE_URL/user/series/info"
    const val GET_USER_ID = "$BASE_URL/get_user_id"
    const val ADD_USERS_SERIES = "$BASE_URL/user/series/add"
    const val UPDATE_USERS_SERIES = "$BASE_URL/user/series/update"

    const val USERS_SERIES = "$BASE_URL/user/series"

    const val GET_USER_EMAIL_BY_TOKEN = "$BASE_URL/get_user_email_by_token"
    const val GET_USERNAME_BY_TOKEN = "$BASE_URL/get_username_by_token"

}
