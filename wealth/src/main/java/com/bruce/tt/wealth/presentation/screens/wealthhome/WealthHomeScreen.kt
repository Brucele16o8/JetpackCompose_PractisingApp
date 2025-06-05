package com.bruce.tt.wealth.presentation.screens.wealthhome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruce.tt.design.components.AppToolbar
import com.bruce.tt.design.ui.theme.whiteColor
import com.bruce.tt.wealth.R
import com.bruce.tt.wealth.presentation.screens.wealthhome.components.CoinListItem

@Composable
fun WealthHomeScreen(
    primaryButtonClicked: () -> Unit,
    viewModel: WealthViewModel = hiltViewModel(),
    coinItemClicked: (String) -> Unit
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppToolbar(
                title = stringResource(R.string.wealth),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked.invoke()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .background(whiteColor)
                    .padding(innerPadding)
            ) {
                if (!state.error.isNullOrEmpty()) {
                    Text(
                        text = state.error,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(),
                        textAlign = TextAlign.Center
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.coins) { coin ->
                        CoinListItem(
                            coin = coin,
                            coinItemClicked = { coinId ->
                                coinItemClicked.invoke(coinId)
                            }
                        )
                    }
                }
            }


            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_WealthHomeScreen() {
    WealthHomeScreen(
        primaryButtonClicked = { },
        coinItemClicked = { }
    )
}