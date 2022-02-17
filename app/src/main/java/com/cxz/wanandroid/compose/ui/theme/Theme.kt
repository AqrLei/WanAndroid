package com.cxz.wanandroid.compose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


/**
 * created by AqrLei on 2022/2/9
 */

private val LightColorPalette = lightColors(
    primary = GreenLightA700,
    secondary = GreenLightA400,
    primaryVariant = GreenA700,
    secondaryVariant = GreenA400
)

private val DarkColorPalette = darkColors(
    primary = GreenDarkA700,
    secondary = GreenDarkA400,
    primaryVariant = GreenA700,
    secondaryVariant = GreenA400
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes
    ) { content() }
}