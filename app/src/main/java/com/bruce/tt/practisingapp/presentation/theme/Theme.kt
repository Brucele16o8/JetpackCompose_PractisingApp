package com.bruce.tt.practisingapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = com.bruce.tt.design.ui.theme.primaryColor,
    secondary = com.bruce.tt.design.ui.theme.secondaryColor,
    tertiary = com.bruce.tt.design.ui.theme.whiteColor
)

private val LightColorScheme = lightColorScheme(
    primary = com.bruce.tt.design.ui.theme.primaryColor,
    secondary = com.bruce.tt.design.ui.theme.secondaryColor,
    tertiary = com.bruce.tt.design.ui.theme.whiteColor

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PractisingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}