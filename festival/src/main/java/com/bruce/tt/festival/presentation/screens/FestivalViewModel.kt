package com.bruce.tt.festival.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruce.tt.festival.domain.usecase.GetFestivalDataUseCase
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class FestivalViewModel @Inject constructor(
    private val getFestivalDataUseCase: GetFestivalDataUseCase
): ViewModel() {
    private val _state = mutableStateOf(FestivalHomeScreenState())
    val state: State<FestivalHomeScreenState> = _state

    init {
        AppLogger.d(message = "Inside Home ViewModel")
        getFestivalsData()
    }

    private fun getFestivalsData() {
        viewModelScope.launch {
            getFestivalDataUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = FestivalHomeScreenState(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        if (result.data != null ) {
                            _state.value = FestivalHomeScreenState(
                                isLoading = false,
                                festivalName = result.data?.festivalName ?: "",
                                festivalDate = result.data?.festivalDate ?: "",
                                festivalDescription = result.data?.festivalDescription ?: "",
                            )
                        } else {
                            _state.value = FestivalHomeScreenState(
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = FestivalHomeScreenState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}