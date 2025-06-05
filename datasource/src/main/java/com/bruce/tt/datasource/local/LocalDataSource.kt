package com.bruce.tt.datasource.local

import com.bruce.tt.datasource.local.entity.Coin
import com.bruce.tt.datasource.local.entity.UserLocation

interface LocalDataSource {
    // Wealth Module
    fun insertCoinListInDB(coinList: List<Coin>)
    fun getCoinListFromDB(): List<Coin>
    fun deleteAllCoinsFromDB()

    // Weather Module
    fun insertUserLocationInDB(cityName: String)
    fun getUserLocationFromDB(): String
}
