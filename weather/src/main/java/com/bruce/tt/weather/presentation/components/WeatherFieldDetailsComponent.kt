package com.bruce.tt.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bruce.tt.design.components.TextComponent
import com.bruce.tt.design.ui.theme.lightPrimaryColor
import com.bruce.tt.design.ui.theme.whiteColor
import com.bruce.tt.weather.R

@Composable
fun WeatherFieldDetailsComponent(
    title: String,
    value: String,
    icon: Int?
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                lightPrimaryColor
            )
    ) {
        Spacer(modifier = Modifier.size(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
             icon?.also {
                Image(
                    modifier = Modifier
                        .size(28.dp),
                    painter = painterResource(icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(whiteColor)
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            TextComponent(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                textValue = title,
                fontSizeValue = 18.sp,
                textColorValue = whiteColor,
                fontWeightValue = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        TextComponent(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp),
            textValue = value,
            fontSizeValue = 34.sp,
            textColorValue = whiteColor,
            fontWeightValue = FontWeight.Normal
        )
        Spacer(modifier = Modifier.size(18.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_WeatherFieldDetailsComponent() {
    WeatherFieldDetailsComponent(
        title = "Air Quality",
        value = "146",
        icon = R.drawable.ic_air_quality
    )
}