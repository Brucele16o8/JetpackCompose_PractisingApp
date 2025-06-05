package com.bruce.tt.wealth.domain.usecase

import com.bruce.tt.utilities.Resource
import com.bruce.tt.datasource.local.entity.Coin
import com.bruce.tt.wealth.domain.repository.ICoinRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class getCoinsUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }
}