package com.bruce.tt.festival.domain.repository

import com.bruce.tt.datasource.local.entity.FestivalsData
import com.bruce.tt.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface IFestivalRepository {
    suspend fun getFestivalData(): Flow<Resource<FestivalsData?>>
}