package com.bruce.tt.design.components

import android.R.attr.contentDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bruce.tt.design.ui.theme.blackColor
import com.bruce.tt.design.ui.theme.holiColor1
import com.bruce.tt.design.ui.theme.holiColor2
import com.bruce.tt.design.ui.theme.primaryColor
import com.bruce.tt.design.ui.theme.whiteColor

@Composable
fun BannerComponent(
    title: String? = null,
    description: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null,
    bannerClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                bannerClicked.invoke()
            }
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        primaryColor,
                        blackColor
                    )
                )
            ),
    ) {
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                model = imageUrl,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Fit
            )
        }
        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .size(120.dp)
                    .padding(18.dp)
                    .align(Alignment.CenterEnd),
                resourceValue = resourceValue
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            title?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = title,
                    fontSizeValue = 24.sp,
                    textColorValue = whiteColor,
                )
            }
            description?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = description,
                    textColorValue = whiteColor,
                )
            }
        }
    }
}

@Composable
fun FestivalBannerComponent(
    title: String? = null,
    description: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null,
    bannerClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                bannerClicked.invoke()
            }
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        holiColor1,
                        holiColor2
                    )
                )
            ),
    ) {
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                model = imageUrl,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Fit
            )
        }
        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .size(120.dp)
                    .padding(18.dp)
                    .align(Alignment.CenterEnd),
                resourceValue = resourceValue
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            title?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = title,
                    fontSizeValue = 24.sp,
                    textColorValue = whiteColor,
                )
            }
            description?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = description,
                    textColorValue = whiteColor,
                )
            }
        }
    }
}

@Composable
fun WeatherBannerComponent(
    cityName: String? = null,
    temperature: String? = null,
    airQuality03: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null,
    bannerClicked: () -> Unit = { }
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultMinSize(minHeight = 138.dp)
            .clickable {
                bannerClicked.invoke()
            }
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        primaryColor,
                        blackColor
                    )
                )
            ),
    ) {
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier
                    .wrapContentSize()
                    .defaultMinSize(minHeight = 68.dp, minWidth = 68.dp)
                    .align(Alignment.BottomEnd),
                model = imageUrl,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Fit
            )
        }
        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .size(120.dp)
                    .padding(18.dp)
                    .align(Alignment.CenterEnd),
                resourceValue = resourceValue
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize()
                .padding(18.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                cityName?.let {
                    TextComponent(
                        modifier = Modifier
                            .wrapContentSize(),
                        textValue = cityName,
                        fontSizeValue = 24.sp,
                        textColorValue = whiteColor,
                    )
                }
                airQuality03?.also {
                    TextComponent(
                        modifier = Modifier
                            .wrapContentSize(),
                        textValue = "Air Quality: $it",
                        fontSizeValue = 16.sp,
                        textColorValue = whiteColor,
                    )
                }
            }
            temperature?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = temperature,
                    textColorValue = whiteColor,
                    fontSizeValue = 28.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_BannerComponent() {
    BannerComponent(
        title = "Hello world",
        description = "This is a preview",
        imageUrl = "https://www.planetware.com/wpimages/2020/02/france-in-pictures-beautiful-places-to-photograph-eiffel-tower.jpg",
        resourceValue = null,
        bannerClicked = { }
    )
}


