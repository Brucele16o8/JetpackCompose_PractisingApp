package com.bruce.tt.wealth.data.remote

import com.bruce.tt.wealth.data.remote.dto.CoinDetailDto
import com.bruce.tt.wealth.data.remote.dto.CoinDto
import com.bruce.tt.wealth.data.remote.dto.CoinTickerInformationDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coin_id}")
    suspend fun getCoinDetails(@Path("coin_id") coinId: String): CoinDetailDto

    @GET("/v1/tickers/{coin_id}")
    suspend fun getCoinTickerInformation(@Path("coin_id") coinId: String): CoinTickerInformationDto

}
