package com.bruce.tt.weather.presentation.screens.address

sealed class LocationUIEvent {
    data class CityItemClicked(val cityName: String): LocationUIEvent()
}