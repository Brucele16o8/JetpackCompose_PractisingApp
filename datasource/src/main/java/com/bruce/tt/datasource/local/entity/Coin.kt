package com.bruce.tt.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bruce.tt.datasource.local.TableConstants

@Entity(tableName = TableConstants.COIN_LIST)
data class Coin (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
)