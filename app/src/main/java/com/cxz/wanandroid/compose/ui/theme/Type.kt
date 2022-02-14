package com.cxz.wanandroid.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with


private val wanAndroidFontFamily = FontFamily.Default


val captionTextStyle = TextStyle(
    fontFamily = wanAndroidFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 96.sp
    ),
    h2 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = wanAndroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)