package com.bruce.tt.wealth.data.repository

import com.bruce.tt.datasource.local.LocalDataSource
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.wealth.data.remote.CoinApi
import com.bruce.tt.wealth.data.remote.dto.toCoin
import com.bruce.tt.wealth.data.remote.dto.toCoinDetail
import com.bruce.tt.datasource.local.entity.Coin
import com.bruce.tt.wealth.data.remote.dto.CoinTickerInformationDto
import com.bruce.tt.wealth.data.remote.dto.toCoinTickerInformation
import com.bruce.tt.wealth.domain.model.CoinDetail
import com.bruce.tt.wealth.domain.model.CoinTickerInformation
import com.bruce.tt.wealth.domain.repository.ICoinRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

class CoinRepositoryImple @Inject constructor(
    private val api: CoinApi,
    private val localDataSource: LocalDataSource
) : ICoinRepository {
    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coinListFromDB = getCoinListFromDB()
            coinListFromDB?.also {
                emit(Resource.Success(it))
                AppLogger.d(message = "Success coinListFromDB size is ${coinListFromDB.size}")
            }
            val coins = api.getCoins()
            insertCoinListInDatabase(coinList = coins.map { it.toCoin() })
            emit(Resource.Success(data = coins.map { it.toCoin() }))
            AppLogger.d(message = "Success coinList From Server size is ${coins.size}")
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach the server, check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }

    override suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = api.getCoinDetails( coinId = coinId)
            emit(Resource.Success(data = coinDetail.toCoinDetail()))
            AppLogger.d(message = "Success = getCoinDetails")
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach the server, check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }

    override suspend fun getCoinTickerInformation(coinId: String): Flow<Resource<CoinTickerInformation>> = flow {
        try {
            emit(Resource.Loading())
            val coinTickerInformationDto = api.getCoinTickerInformation(coinId)
            emit(Resource.Success(data = coinTickerInformationDto.toCoinTickerInformation()))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach the server, check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }


    private suspend fun insertCoinListInDatabase(coinList: List<Coin>) {
        withContext(Dispatchers.IO) {
            localDataSource.insertCoinListInDB(coinList = coinList)
            AppLogger.d(message = "Success insertCoinListInDatabase with size ${coinList.size}")
        }
    }

    private suspend fun getCoinListFromDB(): List<Coin>? =
        withContext(Dispatchers.IO) {
            val coinList = localDataSource.getCoinListFromDB()
            if (coinList.isEmpty()) {
                null
            } else {
                coinList
            }
        }

}