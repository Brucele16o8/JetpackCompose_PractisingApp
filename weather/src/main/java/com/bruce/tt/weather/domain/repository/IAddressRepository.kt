package com.bruce.tt.weather.domain.repository

import com.bruce.tt.datasource.local.entity.CityItem
import com.bruce.tt.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface IAddressRepository {
    suspend fun getPopularCities(): Flow<Resource<List<CityItem>>>
}