package com.bruce.tt.weather.presentation.screens.weather_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.bruce.tt.design.components.AppToolbar
import com.bruce.tt.design.components.TextComponent
import com.bruce.tt.weather.R
import com.bruce.tt.weather.presentation.components.WeatherFieldDetailsComponent

@Composable
fun WeatherHomeScreen(
    weatherHomeViewModel: WeatherHomeViewModel = hiltViewModel(),
    primaryButtonClicked: () -> Unit = { }
) {
    val state = weatherHomeViewModel.state.value

    Scaffold(
        topBar = {
            AppToolbar(
                title = if (!state.locationName.isNullOrEmpty()) state.locationName else stringResource(R.string.weather_details),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked.invoke()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            state.temperature?.also {
                TextComponent(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(all = 18.dp)
                        .align(Alignment.CenterHorizontally),
                    textValue = it,
                    fontSizeValue = 34.sp
                )
            }
            state.weatherIcon?.also {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    model = it,
                    contentDescription = stringResource(R.string.weather_current_status_image),
                    contentScale = ContentScale.Fit
                )
            }
            state.summaryOfTheDay?.also {
                TextComponent(
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                    textValue = it,
                    fontSizeValue = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                state.summaryIconOfTheDay?.also {
                    AsyncImage(
                        modifier = Modifier
                            .size(36.dp),
                        model = it,
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                TextComponent(
                    modifier = Modifier.wrapContentSize(),
                    textValue = "Day summary: "
                )
            }
            state
            state.airQualityO3?.also {
                WeatherFieldDetailsComponent(
                    title = stringResource(R.string.air_details),
                    value = it,
                    icon = R.drawable.ic_air_quality
                )
            }
            state.avgHumidity?.also {
                WeatherFieldDetailsComponent(
                    title = stringResource(R.string.average_humidity),
                    value = it,
                    icon = null
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_WeatherHomeScreen() {
    WeatherHomeScreen()
}