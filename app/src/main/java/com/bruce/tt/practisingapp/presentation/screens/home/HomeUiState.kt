package com.bruce.tt.practisingapp.presentation.screens.home

import com.bruce.tt.weather.presentation.screens.weather_home.WeatherHomeUIState

// UI State for home screen
data class HomeUiState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val festivalName: String? = null,
    val festivalDate: String? = null,
    val festivalDescription: String? = null,
    val selectedCity: String = "",
    val weatherHomeUIState: WeatherHomeUIState? = null,

)