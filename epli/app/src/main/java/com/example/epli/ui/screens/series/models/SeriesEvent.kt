package com.example.epli.ui.screens.series.models

sealed class SeriesEvent {
    data class RatingClicked(
        val rating: Int
    ) : SeriesEvent()
    data class NotesChanged(
        val value: String
    ) : SeriesEvent()

    object IncreaseViewedClicked : SeriesEvent()
    object DecreaseViewedClicked : SeriesEvent()

    object StartViewingClicked : SeriesEvent()

    object ScreenClosed : SeriesEvent()
}