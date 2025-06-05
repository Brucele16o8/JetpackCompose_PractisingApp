package com.bruce.tt.practisingapp.presentation.screens.home

import android.R.id.message
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruce.tt.festival.domain.usecase.GetFestivalDataUseCase
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.contsants.AppConstants
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.weather.domain.usecase.GetCurrentWeatherUseCase
import com.bruce.tt.weather.domain.usecase.GetUserLocationUseCase
import com.bruce.tt.weather.presentation.screens.weather_home.WeatherHomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach
import kotlin.math.ceil

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFestivalDataUseCase: GetFestivalDataUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HomeUiState())
    val state: State<HomeUiState> = _state

    init {
        AppLogger.d(message = "Inside Home ViewModel")
//        getFestivalsData()
        getUserLastLocationFromDB()
    }

    private fun getFestivalsData() {
        viewModelScope.launch {
            getFestivalDataUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeUiState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = HomeUiState(
                                isLoading = false,
                                festivalName = result.data?.festivalName ?: "",
                                festivalDate = result.data?.festivalDate ?: "",
                                festivalDescription = result.data?.festivalDescription ?: "",
                            )
                        } else {
                            _state.value = HomeUiState(
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.value = HomeUiState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getUserLastLocationFromDB() {
        val cityName = getUserLocationUseCase.invoke()
        AppLogger.d(message = "Fetched UserLastLocationFromDB = $cityName")
        if (cityName.isNotEmpty()) {
            _state.value = _state.value.copy(
                selectedCity = cityName
            )
            AppLogger.d(message = "Saved fetched UserLastLocationFromDB to state = ${state.value.selectedCity}")
            fetchCurrentLocationWeather(location = cityName)
        }
    }

    fun refresh() {
        getUserLastLocationFromDB()
    }

    private fun fetchCurrentLocationWeather(location: String) {
        viewModelScope.launch {
            getCurrentWeather(
                accessKey = AppConstants.WEATHER_ACCESS_KEY,
                query = location
            )
        }
    }

    private suspend fun getCurrentWeather(accessKey: String, query: String) {
        getCurrentWeatherUseCase(accessKey, query).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                    AppLogger.d(message = "Inside Loading - getCurrentWeather")
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = null,
                        weatherHomeUIState = WeatherHomeUIState(
                            locationName = result.data?.locationName,
                            observationTime = result.data?.observationTime,
                            temperature = "${ceil(result.data?.temperatureC ?: 0.0).toInt()}°C",
                            weatherIcon = result.data?.weatherIcon,
                            airQualityO3 = ceil(result.data?.airQualityO3 ?: 0.0).toInt().toString(),
                        )
                    )
                    AppLogger.d(message = "Inside getCurrentWeather Success")
                    AppLogger.d(message = "Fetched WeatherHomeUIState = ${result.data}")
                    AppLogger.d(message = "Inside Success - getCurrentWeather - temperature ${state.value.weatherHomeUIState?.temperature}")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                    AppLogger.d(message = "Inside Error - getCurrentWeather - error = ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }
}