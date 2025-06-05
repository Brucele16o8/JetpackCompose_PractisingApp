package com.bruce.tt.weather.domain.usecase

import com.bruce.tt.datasource.local.entity.CityItem
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.weather.domain.repository.IAddressRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPopularCitiesUseCase @Inject constructor(
    private val repository: IAddressRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<CityItem>>> {
        AppLogger.d(message = "Inside GetPopularCitiesUseCase")
        return repository.getPopularCities()
    }
}