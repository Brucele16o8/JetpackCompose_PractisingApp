package com.bruce.tt.wealth.presentation.screens.wealthhome

import com.bruce.tt.datasource.local.entity.Coin

data class WealthHomeScreenState (
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String? = null
)