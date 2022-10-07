package com.example.epli.ui.screens.series

import androidx.lifecycle.*
import com.example.epli.common.EventHandler
import com.example.epli.network.ApiService
import com.example.epli.ui.screens.series.models.SeriesEvent
import com.example.epli.ui.screens.series.models.SeriesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel(),EventHandler<SeriesEvent> {

    private val _viewState = MutableLiveData(SeriesViewState())
    val viewState: LiveData<SeriesViewState> = _viewState

    private val apiService by lazy {
        ApiService.create()
    }

    private fun initViewState(seriesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val getSeriesInfoRespond = apiService.getSeriesInfoById(seriesId)

            _viewState.postValue(
                _viewState.value?.copy(
                    seriesDTO = getSeriesInfoRespond.seriesInfo
                )
            )
        }
    }

    init {
        val argument = savedStateHandle.get<Int>("series_id")

        initViewState(argument!!)
    }



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