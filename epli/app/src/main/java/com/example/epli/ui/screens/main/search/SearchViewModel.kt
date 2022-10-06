package com.example.epli.ui.screens.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epli.common.EventHandler
import com.example.epli.network.ApiService
import com.example.epli.network.models.GenresDTO
import com.example.epli.ui.screens.main.search.models.SearchEvent
import com.example.epli.ui.screens.main.search.models.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel(), EventHandler<SearchEvent> {

    val _viewState = MutableLiveData(SearchViewState())
    val viewState: LiveData<SearchViewState> = _viewState

    private val apiService by lazy {
        ApiService.create()
    }

    private fun fetchGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val genresMap: MutableMap<GenresDTO, Boolean> = mutableMapOf()
            val list = apiService.fetchGenres()
            list.genresList.forEach {
                genresMap[it] = false
            }

            _viewState.postValue(
                _viewState.value?.copy(
                    genresCheckBoxes = genresMap
                )
            )
        }
    }

    init {
        fetchGenres()
    }

    override fun obtainEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchQueryChanged -> searchQueryChanged(event.value)
            is SearchEvent.GenreCheckBoxClicked -> genreBoxClicked(event.genreId)
        }
    }

    private fun searchQueryChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(
                searchQueryValue = value
            )
        )
    }

    private fun genreBoxClicked(id: Int) {
        val genreMap = _viewState.value!!.genresCheckBoxes.toMutableMap()
        val genreKey = genreMap.keys.single{
            it.id == id
        }

        genreMap[genreKey] = !genreMap[genreKey]!!

        _viewState.postValue(
            _viewState.value?.copy(
                genresCheckBoxes = genreMap
            )
        )


    }
}