package com.example.epli.ui.screens.main.profile.models

data class ProfileSeriesInfo(
    val seriesId: Int,
    val seriesName: String,
    val seriesCount: Int,
    val viewedCount: Int,
    val rating: Int,
    val poster: String
)

data class ProfileViewState(
    val usernameValue: String = "",
    val emailValue: String = "",
    val seriesList: List<ProfileSeriesInfo> = emptyList()
)
