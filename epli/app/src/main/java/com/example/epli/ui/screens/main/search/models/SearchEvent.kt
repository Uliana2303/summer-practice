package com.example.epli.ui.screens.main.search.models

sealed class SearchEvent {
    data class SearchQueryChanged(
        val value: String
    ) : SearchEvent()

    data class GenreCheckBoxClicked(
        val genreId: Int
    ) : SearchEvent()
}