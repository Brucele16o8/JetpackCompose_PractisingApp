package com.bruce.tt.weather.presentation.screens.weather_home

import android.text.BoringLayout

data class WeatherHomeUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val locationName: String? = null,
    val observationTime: String? = null,
    val temperature: String? = null,
    val weatherIcon: String? = null,
    val airQualityO3: String? = null,

    // Forecast Data
    val forecastDayDate: String? = null,
    val maxTempC: Double? = null,
    val minTempC: Double? = null,
    val avgHumidity: String? = null,
    val summaryOfTheDay: String? = null,
    val summaryIconOfTheDay: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
    val moonrise: String? = null,
    val moonset: String? = null
)
