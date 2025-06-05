package com.bruce.tt.wealth.presentation.screens.wealthhome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bruce.tt.design.components.TextComponent
import com.bruce.tt.design.ui.theme.primaryColor
import com.bruce.tt.design.ui.theme.whiteColor
import com.bruce.tt.datasource.local.entity.Coin
import com.bruce.tt.design.ui.theme.greenColor
import com.bruce.tt.design.ui.theme.lightBlackColor

@Composable
fun CoinListItem(
    coin: Coin,
    coinItemClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .clickable {
                coinItemClicked.invoke(coin.id)
            }
            .padding(top = 18.dp, start = 18.dp, end = 18.dp)
            .border(
                width = 1.dp,
                color = primaryColor,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(whiteColor)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                TextComponent(
                    modifier = Modifier.wrapContentSize(),
                    textValue = coin.name,
                    fontSizeValue = 18.sp,
                    fontWeightValue = FontWeight.Bold,
                    textColorValue = primaryColor
                )
                TextComponent(
                    modifier = Modifier.wrapContentSize(),
                    textValue = coin.symbol,
                    fontSizeValue = 18.sp
                )
            }
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                textValue = if (coin.isActive) "active" else "inactive",
                textColorValue = if (coin.isActive) greenColor else lightBlackColor
            )
        }
    }
}