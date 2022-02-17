package com.cxz.wanandroid.compose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.material.placeholder
import org.jetbrains.annotations.NotNull

/**
 * created by AqrLei on 2022/2/17
 */
@Composable
fun LabelTextButton(
    @NotNull text: String,
    modifier: Modifier = Modifier,
    isSelect: Boolean = true,
    specTextColor: Color? = null,
    cornerValue: Dp = 25.dp / 2,
    isLoading: Boolean = false,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null
) {
}