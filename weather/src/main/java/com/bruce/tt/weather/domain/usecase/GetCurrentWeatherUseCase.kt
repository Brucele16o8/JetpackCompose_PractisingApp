package com.bruce.tt.weather.domain.usecase

import androidx.compose.ui.input.key.Key
import com.bruce.tt.utilities.Resource
import com.bruce.tt.weather.domain.model.CurrentWeatherResponse
import com.bruce.tt.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    suspend operator fun invoke(accessKey: String, query: String): Flow<Resource<CurrentWeatherResponse>> {
        return repository.getCurrentWeather(
            accessKey = accessKey,
            query = query
        )
    }
}