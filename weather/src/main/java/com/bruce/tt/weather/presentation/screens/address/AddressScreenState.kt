package com.bruce.tt.weather.presentation.screens.address

import com.bruce.tt.datasource.local.entity.CityItem

data class AddressScreenState(
    val isLoading: Boolean = false,
    val popularCities: List<CityItem> = emptyList(),
    val errorMessage: String = "",
    val selectedCity: String = ""
)
