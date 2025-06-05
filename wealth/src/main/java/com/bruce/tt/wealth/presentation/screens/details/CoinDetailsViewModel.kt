package com.bruce.tt.wealth.presentation.screens.details

import androidx.compose.runtime.MutableState
import com.bruce.tt.wealth.domain.usecase.GetCoinDetailsUseCase
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.wealth.domain.usecase.GetCoinTickerInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val getCoinTickerInformationUseCase: GetCoinTickerInformationUseCase
): ViewModel() {
    private val _state = mutableStateOf(CoinDetailsHomeScreenState())
    val state: MutableState<CoinDetailsHomeScreenState> = _state

    fun fetchDetailsForCoin(coinId: String) {
        viewModelScope.launch {
            getCoinDetails(coinId = coinId)
        }
    }

    private suspend fun getCoinDetails(coinId: String) {
        AppLogger.d( message = "Api call function for getCoinDetails")
        getCoinDetailsUseCase(coinId = coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinDetailsHomeScreenState( isLoading = true )
                    AppLogger.d( message = "Loading stage for getCoinDetails")
                }
                is Resource.Success -> {
                    _state.value = CoinDetailsHomeScreenState(
                        coinDetails = result.data
                    )
                    AppLogger.d( message = "Success stage for getCoinDetails")
                    getCoinTickerInformation(coinId = coinId)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailsHomeScreenState(
                        error = result.message
                    )
                    AppLogger.d( message = "Error stage for getCoinDetails")
                }
            }

        }.launchIn(viewModelScope)
    }

    private suspend fun getCoinTickerInformation(coinId: String) {
        AppLogger.d( message = "Api call function for getCoinTickerInformation")
        getCoinTickerInformationUseCase(coinId = coinId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    AppLogger.d( message = "Loading stage for getCoinTickerInformation")
                    _state.value = _state.value.copy(
                        isLoadingCoinTickerInformation = true
                    )
                }
                is Resource.Success -> {
                    AppLogger.d( message = "Success stage for getCoinTickerInformation")
                    _state.value = _state.value.copy(
                        coinTickerInformation = result.data,
                        isLoadingCoinTickerInformation = false
                    )
                }
                is Resource.Error -> {
                    AppLogger.d( message = "Error stage for getCoinTickerInformation")
                    _state.value = _state.value.copy(
                        errorFetchingCoinTickerInformation = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun coinPriceNumberFormat(): NumberFormat {
        return NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }
    }
}