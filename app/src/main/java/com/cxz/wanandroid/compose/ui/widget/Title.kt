package com.cxz.wanandroid.compose.ui.widget

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.google.accompanist.placeholder.material.placeholder

/**
 * created by AqrLei on 2022/2/17
 */

@Composable
fun TextContent(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
    maxLines: Int = 99,
    textAlign: TextAlign = TextAlign.Start,
    canCopy: Boolean = false,
    isLoading: Boolean = false
) {
    if (canCopy) {
        SelectionContainer {
            Title(
                title = text,
                modifier = modifier,
                fontSize = MaterialTheme.typography.h6.fontSize,
                color = color,
                maxLine = maxLines,
                textAlign = textAlign,
                isLoading = isLoading
            )
        }
    } else {
        Title(
            title = text,
            modifier = modifier,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = color,
            maxLine = maxLines,
            textAlign = textAlign,
            isLoading = isLoading
        )
    }

}

@Composable
fun MediumTiTle(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
    textAlign: TextAlign = TextAlign.Start,
    isLoading: Boolean = false
) {
    Title(
        title = title,
        fontSize = MaterialTheme.typography.h5.fontSize,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        isLoading = isLoading
    )

}

@Composable
fun MiniTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    isLoading: Boolean = false
) {
    Title(
        title = text,
        modifier = modifier,
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        color = color,
        maxLine = maxLines,
        textAlign = textAlign,
        isLoading = isLoading,
    )
}

@Composable
fun Title(
    title: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    color: Color = MaterialTheme.colors.onSurface,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLine: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    isLoading: Boolean = false
) {
    Text(
        text = title,
        modifier = modifier
            .placeholder(
                visible = isLoading,
                color = MaterialTheme.colors.surface
            ),
        fontSize = fontSize,
        color = color,
        maxLines = maxLine,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}