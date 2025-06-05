package com.bruce.tt.weather.presentation.screens.address

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.weather.domain.usecase.GetPopularCitiesUseCase
import com.bruce.tt.weather.domain.usecase.GetUserLocationUseCase
import com.bruce.tt.weather.domain.usecase.SaveUserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class AddressScreenViewModel @Inject constructor(
    private val getPopularCitiesUseCase: GetPopularCitiesUseCase,
    private val saveUserLocationUseCase: SaveUserLocationUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase
): ViewModel() {
    private val _state = mutableStateOf(AddressScreenState())
    val state: State<AddressScreenState> = _state


    init {
        AppLogger.d(message = "Inside AddressScreenViewModel")
        getPopularCities()
        getUserLastLocationFromDB()
    }

    private fun getPopularCities() {
        AppLogger.d(message = "Inside getPopularCities")
        viewModelScope.launch {
            AppLogger.d(message = "Inside coroutine - getPopularCities")
            getPopularCitiesUseCase().onEach { result ->
                AppLogger.d(message = "Start state - getPopularCities")
                when (result) {
                    is Resource.Loading -> {
                        _state.value = AddressScreenState(
                            isLoading = true
                        )
                        AppLogger.d(message = "Loading state - Inside getPopularCities")
                    }
                    is Resource.Success -> {
                        _state.value = AddressScreenState(
                            isLoading = false,
                            popularCities = result.data ?: emptyList()
                        )
                        AppLogger.d(message = "Success state - Size ${result.data?.size} - Inside getPopularCities")
                    }
                    is Resource.Error -> {
                        _state.value = AddressScreenState(
                            isLoading = false,
                            errorMessage = result.message ?: ""
                        )
                        AppLogger.d(message = "Error state - ${result.message}- Inside getPopularCities")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: LocationUIEvent) {
        when (event) {
            is LocationUIEvent.CityItemClicked -> {
                AppLogger.d(message = "Inside event - LocationUIEvent.CityItemClicked ")
                AppLogger.d(message = "Selected city: ${event.cityName}")
                saveUserLocationUseCase.invoke(cityName = event.cityName)
                getUserLastLocationFromDB()
            }
        }
    }

    private fun updateCitiesState(cityName: String) {
        val updatedCities = _state.value.popularCities.map { city ->
            if (cityName == city.name) {
                city.copy(isSelected = true)
            } else {
                city.copy(isSelected = false)
            }
        }

        _state.value = _state.value.copy(
            isLoading = false,
            popularCities = updatedCities,
            selectedCity = cityName
        )
    }

    private fun getUserLastLocationFromDB() {
        val cityName = getUserLocationUseCase.invoke()
        AppLogger.d(message = "Fetched UserLastLocationFromDB = $cityName")
        if (cityName.isNotEmpty()) {
            _state.value = _state.value.copy(
                selectedCity = cityName
            )
            AppLogger.d(message = "Saved fetched UserLastLocationFromDB to state = ${state.value.selectedCity}")
        }
        updateCitiesState(cityName = cityName)
    }
}