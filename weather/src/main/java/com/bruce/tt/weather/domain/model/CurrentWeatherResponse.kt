package com.bruce.tt.weather.domain.model

data class CurrentWeatherResponse(
    val locationName: String,
    val observationTime: String,
    val temperatureC: Double,
    val weatherIcon: String?,
    val weatherDescription: String? = null,
    val airQualityO3: Double,
    val feelsLikeC: Double,
    val windSpeedKmh: Double
)