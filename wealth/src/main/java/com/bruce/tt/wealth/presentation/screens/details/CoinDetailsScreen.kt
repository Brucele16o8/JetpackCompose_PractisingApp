package com.bruce.tt.wealth.presentation.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruce.tt.design.components.AppToolbar
import com.bruce.tt.design.components.ChipTextComponent
import com.bruce.tt.design.components.ImageComponent
import com.bruce.tt.design.components.TextComponent
import com.bruce.tt.design.ui.theme.greenColor
import com.bruce.tt.wealth.R
import com.bruce.tt.wealth.domain.model.CoinDetail
import com.bruce.tt.wealth.domain.model.CoinTickerInformation
import java.text.NumberFormat

@Composable
fun CoinDetailsScreen(
    coinId: String,
    backButtonClicked: () -> Unit,
    coinDetailsViewModel: CoinDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = coinId) {
        coinDetailsViewModel.fetchDetailsForCoin(coinId = coinId)
    }

    val state = coinDetailsViewModel.state.value
    val coinNumberFormat = coinDetailsViewModel.coinPriceNumberFormat()

    Scaffold(
        topBar = {
            AppToolbar(
                title = state.coinDetails?.name ?: stringResource(R.string.coin_details),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    backButtonClicked.invoke()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                state.coinDetails?.also { coinDetail ->
                    CoinDetailSuccessComponent(coinDetail = coinDetail)
                }
                CoinPriceInformationComponent(
                    state = state,
                    coinPriceNumberFormat = coinNumberFormat
                )

            }

            if (!state.error.isNullOrEmpty()) {
                Text(
                    text = state.error,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    textAlign = TextAlign.Center
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CoinDetailSuccessComponent(coinDetail: CoinDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
    ) {
        CoinPrimaryInfoComponent(coinDetail = coinDetail)
        CoinOrganisationDetailsComponent(coinDetail = coinDetail)
        TextComponent(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 12.dp),
            textValue = coinDetail.description,
            fontSizeValue = 16.sp,
        )
    }
}

@Composable
fun CoinPrimaryInfoComponent(coinDetail: CoinDetail) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(
                modifier = Modifier.size(80.dp),
                url = coinDetail.logo
            )

            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 12.dp),
                    textValue = coinDetail.name,
                    fontSizeValue = 25.sp
                )
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 12.dp),
                    textValue = coinDetail.symbol,
                    fontSizeValue = 18.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        coinDetail.author?.also { authorName ->
            TextComponent(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 12.dp),
                textValue = authorName,
                fontSizeValue = 18.sp,
                fontWeightValue = FontWeight.Medium,
                textColorValue = greenColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun CoinOrganisationDetailsComponent(coinDetail: CoinDetail) {
    Row(

    ) {
        ChipTextComponent(textValue = coinDetail.orgStructure)
        ChipTextComponent(textValue = coinDetail.hashAlgorithm)
    }
}

@Composable
fun CoinPriceInformationComponent(
    state: CoinDetailsHomeScreenState,
    coinPriceNumberFormat: NumberFormat
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(24.dp)
    ) {
        if (state.isLoadingCoinTickerInformation) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(40.dp)
                    .padding(all = 8.dp)
            )
        }
        state.coinTickerInformation?.also { coinTickerInformation ->
            TextComponent(
                modifier = Modifier.wrapContentSize(),
                textValue = " $ ${coinPriceNumberFormat.format(coinTickerInformation.priceInfo["USD"]?.price)}",
                fontSizeValue = 24.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_CoinDetailsScreen() {
    CoinDetailsScreen(
        coinId = "",
        backButtonClicked = { }
    )
}