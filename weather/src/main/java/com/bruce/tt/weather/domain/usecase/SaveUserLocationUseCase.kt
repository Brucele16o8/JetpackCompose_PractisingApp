package com.bruce.tt.weather.domain.usecase

import com.bruce.tt.datasource.dao.LocationDao
import com.bruce.tt.datasource.local.LocalDataSource
import com.bruce.tt.utilities.logging.AppLogger
import javax.inject.Inject

class SaveUserLocationUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    operator fun invoke(cityName: String) {
        localDataSource.insertUserLocationInDB(cityName = cityName)
        AppLogger.d(message = "Inside SaveUserLocationUseCase Invoke function")
    }
}