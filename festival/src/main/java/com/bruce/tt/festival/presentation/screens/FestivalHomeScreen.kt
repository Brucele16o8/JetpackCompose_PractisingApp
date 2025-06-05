package com.bruce.tt.festival.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bruce.tt.design.components.AppToolbar
import com.bruce.tt.design.components.TextComponent
import com.bruce.tt.design.ui.theme.greenColor
import com.bruce.tt.design.ui.theme.holiColor2
import com.bruce.tt.design.ui.theme.holiColor4
import com.bruce.tt.design.ui.theme.primaryColor
import com.bruce.tt.design.ui.theme.whiteColor
import com.bruce.tt.festival.R
import com.bruce.tt.festival.presentation.components.FestivalLottieAnimationComponent
import com.bruce.tt.festival.presentation.components.FestivalMessagesComponent
import com.bruce.tt.utilities.SuperUtilities

@Composable
fun FestivalHomeScreen(
    primaryButtonClicked: () -> Unit = { },
    viewModel: FestivalViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppToolbar(
                title = state.festivalName ?: "",
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked.invoke()
                },
                backgroundColor = Color.Transparent
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(holiColor4, whiteColor, holiColor2)
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FestivalLottieAnimationComponent(
                    rawResource = R.raw.icon_king_birthday
                )
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(all = 12.dp),
                    textValue = "\"${state.festivalDescription}\"",
                    fontSizeValue = 20.sp,
                    fontWeightValue = FontWeight.Normal,
                    textColorValue = primaryColor
                )
                state.festivalDate?.also {
                    TextComponent(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterHorizontally)
                            .padding(all = 12.dp),
                        textValue = SuperUtilities.toDate(it),
                        fontSizeValue = 24.sp,
                        fontWeightValue = FontWeight.Normal,
                        textColorValue = greenColor
                    )
                }
                FestivalMessagesComponent()
            }
        }
    }
}