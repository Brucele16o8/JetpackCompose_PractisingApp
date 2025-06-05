package com.bruce.tt.practisingapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.bruce.tt.practisingapp.R
import com.bruce.tt.design.components.AppToolbar
import com.bruce.tt.design.components.BannerComponent
import com.bruce.tt.design.components.FestivalBannerComponent
import com.bruce.tt.design.components.WeatherBannerComponent

@Composable
fun HomeScreen(
    primaryButtonClicked: () -> Unit = { },
    wealthBannerClicked: () -> Unit = { },
    viewModel: HomeViewModel = hiltViewModel(),
    festivalBannerClicked: () -> Unit = { },
    addAddressClicked: () -> Unit = { },
    weatherBannerClicked: () -> Unit = { }

) {
    val state = viewModel.state.value

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.refresh()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
            .background(com.bruce.tt.design.ui.theme.whiteColor)
    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = if (state.selectedCity.isNotEmpty()) state.selectedCity else stringResource( id = R.string.add_address),
                    isNotificationVisible =  true,
                    primaryButtonClicked = {
                        primaryButtonClicked.invoke()
                    },
                    primaryTextClicked = {
                        addAddressClicked.invoke()
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
                    .background(com.bruce.tt.design.ui.theme.whiteColor)
                    .padding(innerPadding)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                if (state.festivalName != null) {
                    FestivalBannerComponent(
                        title = state.festivalName,
                        description = state.festivalDescription,
                        imageUrl = null,
                        resourceValue = R.drawable.icon_festival,
                        bannerClicked = {
                            festivalBannerClicked.invoke()
                        }
                    )
                }


                BannerComponent(
                    title = stringResource(R.string.wealth),
                    description = stringResource(R.string.investment_ideas_for_you),
                    imageUrl = null,
                    resourceValue = R.drawable.icon_wealth,
                    bannerClicked = {
                        wealthBannerClicked.invoke()
                    }
                )

                state.weatherHomeUIState?.also {
                    WeatherBannerComponent(
                        cityName = it.locationName,
                        temperature = it.temperature?.toString(),
                        airQuality03 = it.airQualityO3?.toString(),
                        imageUrl = it.weatherIcon,
                        bannerClicked = {
                            weatherBannerClicked.invoke()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_HomeScreen() {
    HomeScreen(
        primaryButtonClicked = { },
        wealthBannerClicked = { }
    )
}