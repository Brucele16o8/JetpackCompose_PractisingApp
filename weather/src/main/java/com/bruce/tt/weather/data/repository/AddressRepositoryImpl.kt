package com.bruce.tt.weather.data.repository

import android.content.Context
import com.bruce.tt.datasource.local.entity.CityItem
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.weather.R
import com.bruce.tt.weather.domain.repository.IAddressRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddressRepositoryImpl @Inject constructor(
    private val context: Context
): IAddressRepository {

    override suspend fun getPopularCities(): Flow<Resource<List<CityItem>>> = flow {
        AppLogger.d(message = "Inside AddressRepositoryImpl")
        try {
            emit(Resource.Loading())
            val cities = mutableListOf<CityItem>()
            cities.apply {
                add(
                    CityItem(
                        icon = R.drawable.ic_melbourne,
                        name = context.getString(R.string.melbourne)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.ic_sydney,
                        name = context.getString(R.string.sydney)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.ic_adelaide,
                        name = context.getString(R.string.adelaide)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.ic_brisbane,
                        name = context.getString(R.string.brisbane)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.ic_perth,
                        name = context.getString(R.string.perth)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.ic_hobart,
                        name = context.getString(R.string.hobart)
                    )
                )
            }
            emit(Resource.Success(cities))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}