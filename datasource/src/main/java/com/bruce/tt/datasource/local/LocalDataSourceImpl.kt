package com.bruce.tt.datasource.local

import android.content.Context
import com.bruce.tt.datasource.local.entity.Coin
import com.bruce.tt.datasource.local.entity.UserLocation
import dagger.hilt.android.qualifiers.ApplicationContext

class LocalDataSourceImpl(
    @ApplicationContext private val context: Context
): LocalDataSource {
    val appDB: AppDB = AppDB.getDatabase(context = context)

    override fun insertCoinListInDB(coinList: List<Coin>) {
        appDB.getCoinsDao().insertCoinListInDB(coinList)
    }

    override fun getCoinListFromDB(): List<Coin> {
        return appDB.getCoinsDao().getCoinListFromDB()
    }

    override fun deleteAllCoinsFromDB() {
        appDB.getCoinsDao().deleteAllCoinsFromDB()
    }

    override fun insertUserLocationInDB(cityName: String) {
        appDB.getLocationDao().insertUserLocationInDB(
            userLocation = UserLocation(cityName = cityName)
        )
    }

    override fun getUserLocationFromDB(): String {
        return appDB.getLocationDao().getUserLocationFromDB()?.cityName ?: ""
    }
}