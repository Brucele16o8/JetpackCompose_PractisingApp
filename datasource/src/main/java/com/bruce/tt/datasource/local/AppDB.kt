package com.bruce.tt.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bruce.tt.datasource.dao.CoinDao
import com.bruce.tt.datasource.dao.LocationDao
import com.bruce.tt.datasource.local.entity.Coin
import com.bruce.tt.datasource.local.entity.UserLocation

@Database(
    entities = [Coin::class, UserLocation::class],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract fun getCoinsDao(): CoinDao
    abstract fun getLocationDao(): LocationDao


    companion object {
        @Volatile
        private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context = context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDB {
            return Room
                .databaseBuilder(
                    context = context,
                    klass = AppDB::class.java,
                    name = TableConstants.APP_DB_NAME
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        fun destroyDatabase() {
            instance = null
        }
    }
}

object TableConstants {
    const val APP_DB_NAME = "PractisingApp_DB"
    const val COIN_LIST = "Coin_List"
    const val USER_LOCATION = "User_Location"
}