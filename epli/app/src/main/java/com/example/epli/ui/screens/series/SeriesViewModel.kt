package com.example.epli.ui.screens.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epli.common.EventHandler
import com.example.epli.ui.screens.series.models.SeriesEvent
import com.example.epli.ui.screens.series.models.SeriesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor() : ViewModel(),EventHandler<SeriesEvent> {

    private val _viewState = MutableLiveData(SeriesViewState())
    val viewState: LiveData<SeriesViewState> = _viewState

    override fun obtainEvent(event: SeriesEvent) {
        when (event) {
            is SeriesEvent.NotesChanged -> notesChanged(event.value)
            is SeriesEvent.RatingClicked -> ratingChanged(event.rating)
        }
    }

    private fun ratingChanged(rating: Int) {
        _viewState.postValue(
            _viewState.value?.copy(
                ratingValue = rating
            )
        )
    }

    private fun notesChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(
                notesValue = value
            )
        )
    }
}