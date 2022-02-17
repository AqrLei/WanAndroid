package com.cxz.wanandroid.compose.ui.widget

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cxz.wanandroid.R
import com.google.accompanist.placeholder.material.placeholder

/**
 * created by AqrLei on 2022/2/17
 */
@Composable
fun TimerIcon(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_time),
        contentDescription = "",
        tint = MaterialTheme.colors.onSurface,
        modifier = modifier
            .width(15.dp)
            .height(15.dp)
            .clip(RoundedCornerShape(15.dp / 2))
            .placeholder(
                visible = isLoading,
                color = MaterialTheme.colors.surface
            )
    )
}