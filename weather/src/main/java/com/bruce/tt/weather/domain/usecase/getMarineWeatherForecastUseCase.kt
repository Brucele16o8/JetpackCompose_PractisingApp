package com.bruce.tt.weather.domain.usecase

import com.bruce.tt.utilities.Resource
import com.bruce.tt.weather.domain.model.MarineWeatherForecast
import com.bruce.tt.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarineWeatherForecastUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    suspend operator fun invoke(
        accessKey: String,
        query: String
    ): Flow<Resource<MarineWeatherForecast>> {
        return repository.getMarineWeatherForecast(
            accessKey = accessKey,
            query = query
        )
    }
}