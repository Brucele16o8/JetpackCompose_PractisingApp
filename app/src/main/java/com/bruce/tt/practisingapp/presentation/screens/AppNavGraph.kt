package com.bruce.tt.practisingapp.presentation.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bruce.tt.festival.presentation.screens.FestivalHomeScreen
import com.bruce.tt.practisingapp.presentation.screens.home.HomeScreen
import com.bruce.tt.utilities.logging.AppLogger
import com.bruce.tt.wealth.presentation.screens.details.CoinDetailsScreen
import com.bruce.tt.wealth.presentation.screens.wealthhome.WealthHomeScreen
import com.bruce.tt.weather.presentation.screens.address.AddressScreen
import com.bruce.tt.weather.presentation.screens.weather_home.WeatherHomeScreen

@Composable
fun AppNavApp() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(
                route = Screen.HomeScreen.route,
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                }
            ) {
                HomeScreen(
                    primaryButtonClicked = {

                    },
                    wealthBannerClicked = {
                        navController.navigate(Screen.WealthHomeScreen.route)
                    },
                    festivalBannerClicked = {
                        navController.navigate(Screen.FestivalHomeScreen.route)
                    },
                    addAddressClicked = {
                        navController.navigate(Screen.AddressScreen.route)
                    },
                    weatherBannerClicked = {
                        navController.navigate(Screen.WeatherHomeScreen.route)
                    }
                )
            }
            composable(
                route = Screen.WealthHomeScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                popEnterTransition = null
            ) {
                WealthHomeScreen(
                    primaryButtonClicked = {
                        navController.popBackStack()
                    },
                    coinItemClicked = { coinId ->
                        navController.navigate(
                            Screen.CoinDetailsScreen.route + "/$coinId"
                        )
                    }
                )
            }
            composable(
                route = "${Screen.CoinDetailsScreen.route}/{${ScreenArguments.COIN_ID}}",
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                arguments = listOf(
                    navArgument(name = "${ScreenArguments.COIN_ID}") { type = NavType.StringType }
                )
            ) {
                it.arguments?.getString("${ScreenArguments.COIN_ID}")?.also { coinId ->
                    AppLogger.d(message = "CoinId is ====== $coinId")
                    CoinDetailsScreen(
                        coinId = coinId,
                        backButtonClicked = {
                            navController.popBackStack()
                        }
                    )
                }
            }
            composable(
                route = Screen.FestivalHomeScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                popEnterTransition = null
            ) {
                FestivalHomeScreen(
                    primaryButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = Screen.AddressScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                popEnterTransition = null
            ) {
                AddressScreen(
                    primaryButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = Screen.WeatherHomeScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                popEnterTransition = null
            ) {
                WeatherHomeScreen(
                    primaryButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}