package com.bruce.tt.wealth.domain.usecase

import com.bruce.tt.utilities.Resource
import com.bruce.tt.wealth.domain.model.CoinDetail
import com.bruce.tt.wealth.domain.repository.ICoinRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    suspend operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> {
        return repository.getCoinDetails(coinId = coinId)
    }
}