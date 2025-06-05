package com.bruce.tt.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bruce.tt.datasource.local.TableConstants
import com.bruce.tt.datasource.local.entity.Coin

@Dao
interface CoinDao: BaseDao<Coin> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinListInDB(coinList: List<Coin>)

    @Query("SELECT * from ${TableConstants.COIN_LIST}")
    fun getCoinListFromDB(): List<Coin>

    @Query("DELETE from ${TableConstants.COIN_LIST}")
    fun deleteAllCoinsFromDB()
}