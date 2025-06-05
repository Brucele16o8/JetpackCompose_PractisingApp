package com.bruce.tt.festival.domain.usecase

import com.bruce.tt.datasource.local.entity.FestivalsData
import com.bruce.tt.festival.domain.repository.IFestivalRepository
import com.bruce.tt.utilities.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFestivalDataUseCase @Inject constructor(
    private val repository: IFestivalRepository
) {
    suspend operator fun invoke(): Flow<Resource<FestivalsData?>> {
        return repository.getFestivalData()
    }
}