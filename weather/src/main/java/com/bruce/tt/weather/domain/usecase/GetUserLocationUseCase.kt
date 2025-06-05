package com.bruce.tt.weather.domain.usecase

import com.bruce.tt.datasource.local.LocalDataSource
import com.bruce.tt.utilities.logging.AppLogger
import javax.inject.Inject

class GetUserLocationUseCase  @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    operator fun invoke(): String {
        AppLogger.d(message = "Inside GetUserLocationUseCase Invoke function - ${localDataSource.getUserLocationFromDB()}")
        return localDataSource.getUserLocationFromDB()
    }
}