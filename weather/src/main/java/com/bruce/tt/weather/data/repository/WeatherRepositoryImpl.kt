package com.bruce.tt.weather.data.repository

import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.contsants.AppConstants
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.weather.data.remote.WeatherApi
import com.bruce.tt.weather.data.remote.dto.toCurrentWeatherResponse
import com.bruce.tt.weather.data.remote.dto.toMarineWeatherForecast
import com.bruce.tt.weather.domain.model.CurrentWeatherResponse
import com.bruce.tt.weather.domain.model.MarineWeatherForecast
import com.bruce.tt.weather.domain.repository.IWeatherRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): IWeatherRepository {

    override suspend fun getCurrentWeather(
        accessKey: String,
        query: String
    ): Flow<Resource<CurrentWeatherResponse>> = flow {
        try {
            emit(Resource.Loading())
            val currentWeatherDto = api.getCurrentWeather(accessKey = accessKey, query = query)
            emit(Resource.Success(currentWeatherDto.toCurrentWeatherResponse()))
            AppLogger.d(message = "Inside Success of getCurrentWeather")
        } catch (e: HttpException) {
            AppLogger.d(message = "Inside Error HttpException ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            AppLogger.d(message = "Inside Error IOException ${e.localizedMessage}")
            emit(Resource.Error(message = AppConstants.IO_ERROR_MESSAGE))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }

    override suspend fun getMarineWeatherForecast(
        accessKey: String,
        query: String
    ): Flow<Resource<MarineWeatherForecast>> = flow {
        try {
            emit(Resource.Loading())
            val marineWeatherForecastDto = api.getMarineWeatherForecast(accessKey = accessKey, query = query)
            emit(Resource.Success(marineWeatherForecastDto.toMarineWeatherForecast()))
            AppLogger.d(message = "Inside Success of getMarineWeatherForecast")
        } catch (e: HttpException) {
            AppLogger.d(message = "Inside Error HttpException ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            AppLogger.d(message = "Inside Error IOException ${e.localizedMessage}")
            emit(Resource.Error(message = AppConstants.IO_ERROR_MESSAGE))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }
}