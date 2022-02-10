package com.cxz.wanandroid.base.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.appcompattheme.AppCompatTheme

/**
 * created by AqrLei on 2022/2/9
 */

private val LightColorPalette = lightColors(
    primary = ColorPrimary,
    secondary = ColorAccent,
    primaryVariant = ColorPrimaryDark,
    onSurface = TextColorPrimary
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = LightColorPalette, typography = Typography) { content() }
}