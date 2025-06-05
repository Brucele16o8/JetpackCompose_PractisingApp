package com.bruce.tt.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bruce.tt.datasource.local.TableConstants
import com.bruce.tt.datasource.local.entity.UserLocation

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLocationInDB(userLocation: UserLocation)

    @Query("SELECT * from ${TableConstants.USER_LOCATION}")
    fun getUserLocationFromDB(): UserLocation?
}