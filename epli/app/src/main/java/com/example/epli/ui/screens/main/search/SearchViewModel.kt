package com.example.epli.ui.screens.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epli.common.EventHandler
import com.example.epli.ui.screens.main.search.models.SearchEvent
import com.example.epli.ui.screens.main.search.models.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel(), EventHandler<SearchEvent> {

    val _viewState = MutableLiveData(SearchViewState())
    val viewState: LiveData<SearchViewState> = _viewState

    override fun obtainEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchQueryChanged -> searchQueryChanged(event.value)
        }
    }

    private fun searchQueryChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(
                searchQueryValue = value
            )
        )
    }
}