package com.bruce.tt.wealth.presentation.screens.details

import com.bruce.tt.wealth.domain.model.CoinDetail
import com.bruce.tt.wealth.domain.model.CoinTickerInformation

data class CoinDetailsHomeScreenState (
    val isLoading: Boolean = false,
    val coinDetails: CoinDetail? = null,
    val error: String? = null,

    val isLoadingCoinTickerInformation: Boolean = false,
    val coinTickerInformation: CoinTickerInformation? = null,
    val errorFetchingCoinTickerInformation: String? = null
)