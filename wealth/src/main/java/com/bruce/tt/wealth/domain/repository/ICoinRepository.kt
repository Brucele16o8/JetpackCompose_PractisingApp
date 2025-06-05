package com.bruce.tt.wealth.domain.repository

import com.bruce.tt.utilities.Resource
import com.bruce.tt.datasource.local.entity.Coin
import com.bruce.tt.wealth.domain.model.CoinDetail
import com.bruce.tt.wealth.domain.model.CoinTickerInformation
import kotlinx.coroutines.flow.Flow

interface ICoinRepository {
    suspend fun getCoins(): Flow<Resource<List<Coin>>>
    suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetail>>
    suspend fun getCoinTickerInformation(coinId: String): Flow<Resource<CoinTickerInformation>>
}