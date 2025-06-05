package com.bruce.tt.weather.presentation.screens.weather_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.contsants.AppConstants
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.weather.domain.usecase.GetCurrentWeatherUseCase
import com.bruce.tt.weather.domain.usecase.GetMarineWeatherForecastUseCase
import com.bruce.tt.weather.domain.usecase.GetUserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.String
import kotlin.math.ceil

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getMarineWeatherForecastUseCase: GetMarineWeatherForecastUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WeatherHomeUIState())
    val state: State<WeatherHomeUIState> = _state

    init {
        AppLogger.d(message = "Inside init block of WeatherViewModel")
        getUserLastLocationFromDB()
    }

    private fun getUserLastLocationFromDB() {
        val cityName = getUserLocationUseCase.invoke()
        AppLogger.d(message = "Fetched UserLastLocationFromDB = $cityName")
        if (cityName.isNotEmpty()) {
            _state.value = _state.value.copy(
                locationName = cityName
            )
            AppLogger.d(message = "Saved fetched UserLastLocationFromDB to state = ${state.value.locationName}")
            fetchCurrentLocationWeather(location = cityName)
        }
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
                        locationName = result.data?.locationName,
                        observationTime = result.data?.observationTime,
                        temperature = "${ceil(result.data?.temperatureC ?: 0.0).toInt()}°C",
                        weatherIcon = result.data?.weatherIcon,
                        airQualityO3 = ceil(result.data?.airQualityO3 ?: 0.0).toInt().toString()
                    )
                    AppLogger.d(message = "Inside WeatherViewModel getCurrentWeather Success")
                    AppLogger.d(message = "Fetched WeatherHomeUIState = ${_state.value}")
                    getMarineWeatherForecast(accessKey = accessKey, query = query)
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

    private suspend fun getMarineWeatherForecast(
        accessKey: String,
        query: String
    ) {
        getMarineWeatherForecastUseCase(accessKey = accessKey, query = query).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        forecastDayDate = result.data?.forecastDayDate,
                        maxTempC = result.data?.maxTempC,
                        minTempC = result.data?.minTempC,
                        avgHumidity = ceil(result.data?.avgHumidity ?: 0.0).toInt().toString(),
                        summaryOfTheDay = result.data?.summaryOfTheDay,
                        summaryIconOfTheDay = result.data?.summaryIconOfTheDay,
                        sunrise = result.data?.sunrise,
                        sunset = result.data?.sunset,
                        moonrise = result.data?.moonrise,
                        moonset = result.data?.moonset
                    )
                    AppLogger.d(message = "Inside WeatherViewModel getMarineWeatherForecastUseCase Success")
                    AppLogger.d(message = "Fetched WeatherHomeUIState = ${_state.value}")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}