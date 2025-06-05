package com.bruce.tt.weather.presentation.screens.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bruce.tt.datasource.local.entity.CityItem
import com.bruce.tt.design.components.AppToolbar
import com.bruce.tt.design.components.TextComponent
import com.bruce.tt.design.ui.theme.backgroundColor
import com.bruce.tt.design.ui.theme.blackColor
import com.bruce.tt.design.ui.theme.greenColor
import com.bruce.tt.design.ui.theme.lightColor
import com.bruce.tt.design.ui.theme.lightWhiteColor
import com.bruce.tt.design.ui.theme.whiteColor
import com.bruce.tt.weather.R
import java.nio.file.WatchEvent

@Composable
fun AddressScreen(
    primaryButtonClicked: () -> Unit = { },
    viewModel: AddressScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppToolbar(
                title = if (state.selectedCity.isNotEmpty()) state.selectedCity else "Add Address",
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked.invoke()
                }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(innerPadding)
        ) {
            AddressInstruction()
            DetectUserCurrentLocation()
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .background(backgroundColor)
            )
            PopularCitiesComponent(
                popularCities = state.popularCities,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun DetectUserCurrentLocation() {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .background(whiteColor)
            .padding(all = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = "Location icon"
        )
        TextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
                .wrapContentHeight(),
            textValue = stringResource(id = R.string.auto_detect_my_location),
            fontSizeValue = 16.sp,
            textColorValue = blackColor
        )
    }
}

@Composable
fun AddressInstruction() {
    Column {
        TextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .background(whiteColor)
                .padding(all = 18.dp)
                .wrapContentHeight(),
            textValue = stringResource(id = R.string.add_address_hint),
            fontSizeValue = 18.sp,
            textColorValue = greenColor,
            fontWeightValue = FontWeight.Light
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(backgroundColor)
        )
    }
}

@Composable
fun PopularCitiesComponent(
    popularCities: List<CityItem>,
    viewModel: AddressScreenViewModel
) {
    Column {
        TextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
                .wrapContentHeight(),
            textValue = stringResource(id = R.string.popular_cities),
            fontSizeValue = 14.sp,
            textColorValue = blackColor
        )

        LazyVerticalGrid(
            modifier = Modifier
                .wrapContentSize()
                .background(whiteColor),
            columns = GridCells.Adaptive(100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                all = 16.dp
            ),
            content = {
                items(popularCities) { city ->
                    CityItemComponent(
                        city = city,
                        viewModel = viewModel
                    )
                }
            }
        )
    }
}

@Composable
fun CityItemComponent(
    city: CityItem,
    viewModel: AddressScreenViewModel
) {
    Column(
        modifier = Modifier
            .clickable {
                viewModel.onEvent(event = LocationUIEvent.CityItemClicked(cityName = city.name))
            }
            .wrapContentSize()
            .defaultMinSize(100.dp)
            .background(
                color = if (city.isSelected) lightWhiteColor else whiteColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if (city.isSelected) greenColor else backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(all = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = city.icon),
            contentDescription = city.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(100.dp)
                .clip(
                    RoundedCornerShape(18.dp)
                )
        )
        TextComponent(
            modifier = Modifier.wrapContentSize(),
            textValue = city.name,
            textColorValue = if (city.isSelected) greenColor else blackColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_AddressScreen() {
    AddressScreen()
}