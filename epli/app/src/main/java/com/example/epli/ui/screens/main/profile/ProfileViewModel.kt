package com.example.epli.ui.screens.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.epli.common.EventHandler
import com.example.epli.data.tokenStore
import com.example.epli.network.ApiService
import com.example.epli.ui.screens.main.profile.models.ProfileEvent
import com.example.epli.ui.screens.main.profile.models.ProfileSeriesInfo
import com.example.epli.ui.screens.main.profile.models.ProfileViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(application: Application)
    : AndroidViewModel(application), EventHandler<ProfileEvent>{

    private val _viewState = MutableLiveData(ProfileViewState())
    val viewState: LiveData<ProfileViewState> = _viewState

    private val apiService by lazy {
        ApiService.create()
    }

    private val context = application.applicationContext

    private fun initProfileInfo() {
        viewModelScope.launch {
            val username = apiService.getUsernameByToken(context.tokenStore.data.first().token)
            val email = apiService.getEmailByToken(context.tokenStore.data.first().token)

            _viewState.postValue(
                _viewState.value?.copy(
                    usernameValue = username.username,
                    emailValue = email.email
                )
            )
        }
    }

    private fun initSeriesList() {
        viewModelScope.launch {
            val userId = apiService.getUserIdByToken(context.tokenStore.data.first().token).userId
            val seriesList = apiService.fetchUsersSeries(userId!!).usersSeries.map {
                ProfileSeriesInfo(
                    seriesId = it.series_id,
                    seriesName = apiService.getSeriesInfoById(it.series_id).seriesInfo!!.name,
                    seriesCount = apiService.getSeriesInfoById(it.series_id).seriesInfo!!.series_count,
                    viewedCount = it.series_viewed,
                    rating = it.rating,
                    poster = apiService.getSeriesInfoById(it.series_id).seriesInfo!!.poster
                )
            }

            _viewState.postValue(
                _viewState.value?.copy(
                    seriesList = seriesList
                )
            )
        }
    }

    init {
        initProfileInfo()
        initSeriesList()
    }



    override fun obtainEvent(event: ProfileEvent) {
        TODO("Not yet implemented")
    }
}