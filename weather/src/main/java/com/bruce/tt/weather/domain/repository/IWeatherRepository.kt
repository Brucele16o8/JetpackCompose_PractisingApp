package com.bruce.tt.weather.domain.repository

import com.bruce.tt.utilities.Resource
import com.bruce.tt.weather.domain.model.CurrentWeatherResponse
import com.bruce.tt.weather.domain.model.MarineWeatherForecast
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun getCurrentWeather(
        accessKey: String,
        query: String
    ): Flow<Resource<CurrentWeatherResponse>>

    suspend fun getMarineWeatherForecast(
        accessKey: String,
        query: String
    ): Flow<Resource<MarineWeatherForecast>>
}