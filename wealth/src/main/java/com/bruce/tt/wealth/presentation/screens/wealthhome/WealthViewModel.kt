package com.bruce.tt.wealth.presentation.screens.wealthhome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.wealth.domain.usecase.getCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class WealthViewModel @Inject constructor(
    private val getCoinsUseCase: getCoinsUseCase
): ViewModel() {
    private val _state = mutableStateOf(WealthHomeScreenState())
    val state: State<WealthHomeScreenState> = _state

    init {
        viewModelScope.launch {
            getCoins()
        }
    }

    private suspend fun getCoins() {
        AppLogger.d( message = "getCoins")
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = WealthHomeScreenState( isLoading = true )
                    AppLogger.d( message = "Loading")
                }
                is Resource.Success -> {
                    _state.value = WealthHomeScreenState(
                        coins = result.data ?: emptyList()
                    )
                    AppLogger.d( message = "Success")
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message
                    )
                    AppLogger.d( message = "Error")
                }
            }

        }.launchIn(viewModelScope)
    }

}