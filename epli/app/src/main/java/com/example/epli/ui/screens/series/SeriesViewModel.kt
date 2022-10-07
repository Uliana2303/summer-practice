package com.example.epli.ui.screens.series

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.epli.common.EventHandler
import com.example.epli.data.Token
import com.example.epli.data.tokenStore
import com.example.epli.network.ApiService
import com.example.epli.network.models.GetUsersSeriesInfoResponse
import com.example.epli.ui.screens.series.models.SeriesEvent
import com.example.epli.ui.screens.series.models.SeriesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application),EventHandler<SeriesEvent> {

    private val _viewState = MutableLiveData(SeriesViewState())
    val viewState: LiveData<SeriesViewState> = _viewState

    private val context = application.applicationContext

    val tokenFlow: Flow<Token> = context.tokenStore.data.catch { exception ->
        if (exception is IOException) {
            Log.e("E", "Error reading sort order preferences.", exception)
            emit(Token.getDefaultInstance())
        } else {
            throw exception
        }
    }

    private val apiService by lazy {
        ApiService.create()
    }

    private fun initSeriesInfo(seriesId: Int) {
        viewModelScope.launch {
            val getSeriesInfoRespond = apiService.getSeriesInfoById(seriesId)

            _viewState.postValue(
                _viewState.value?.copy(
                    seriesDTO = getSeriesInfoRespond.seriesInfo
                )
            )
        }
    }



    private fun initUsersSeriesInfo(seriesId: Int) {
        viewModelScope.launch {
            val userId = apiService.getUserIdByToken(context.tokenStore.data.first().token).userId

            val usersSeriesInfoRespond = if (userId != null) {
                apiService.getUssSeriesInfoById(userId, seriesId)
            } else {
                GetUsersSeriesInfoResponse(null)
            }

            _viewState.postValue(
                _viewState.value?.copy(
                    is_watching = usersSeriesInfoRespond.usersSeriesInfo != null,
                    ratingValue = usersSeriesInfoRespond.usersSeriesInfo?.rating ?: 0,
                    viewed = usersSeriesInfoRespond.usersSeriesInfo?.series_viewed ?: 0,
                    notesValue = usersSeriesInfoRespond.usersSeriesInfo?.notes ?: ""
                )
            )
        }
    }

    init {
        val argument = savedStateHandle.get<Int>("series_id")

        initSeriesInfo(argument!!)
        initUsersSeriesInfo(argument)
    }



    override fun obtainEvent(event: SeriesEvent) {
        when (event) {
            is SeriesEvent.NotesChanged -> notesChanged(event.value)
            is SeriesEvent.RatingClicked -> ratingChanged(event.rating)
            SeriesEvent.DecreaseViewedClicked -> onDereaseViewedClicked()
            SeriesEvent.IncreaseViewedClicked -> onInsreaseViewedClicked()
            SeriesEvent.StartViewingClicked -> onStartViewingClicked()
            SeriesEvent.ScreenClosed -> updateUsersSeries()
        }
    }

    private fun updateUsersSeries(){
        viewModelScope.launch {
            if (_viewState.value!!.is_watching) {
                val userId =
                    apiService.getUserIdByToken(context.tokenStore.data.first().token).userId

                apiService.updateUsersSeries(
                    seriesId = _viewState.value!!.seriesDTO!!.series_id,
                    userId = userId!!,
                    viewed = _viewState.value!!.viewed,
                    rating = _viewState.value!!.ratingValue,
                    notes = _viewState.value!!.notesValue
                )
            }
        }
    }

    private fun onStartViewingClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = apiService.getUserIdByToken(context.tokenStore.data.first().token).userId
            apiService.addUsersSeries(userId!!, _viewState.value!!.seriesDTO!!.series_id)

            initUsersSeriesInfo(_viewState.value!!.seriesDTO!!.series_id)

            _viewState.postValue(
                _viewState.value?.copy(
                    is_watching = true
                )
            )
        }
    }

    private fun onInsreaseViewedClicked() {
        if (_viewState.value!!.viewed < _viewState.value!!.seriesDTO!!.series_count) {
            _viewState.postValue(
                _viewState.value?.copy(
                    viewed = _viewState.value!!.viewed + 1
                )
            )
        }
    }

    private fun onDereaseViewedClicked() {
        if (_viewState.value!!.viewed > 0) {
            _viewState.postValue(
                _viewState.value?.copy(
                    viewed = _viewState.value!!.viewed - 1
                )
            )
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