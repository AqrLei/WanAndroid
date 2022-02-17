package com.cxz.wanandroid.compose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cxz.wanandroid.compose.data.bean.home.Article

/**
 * created by AqrLei on 2022/2/16
 */

@Composable
fun MultiStateItemView(
    modifier: Modifier = Modifier,
    data: Article,
    onItemSelected: (title:String, link:String) -> Unit = { _, _ ->},
    isTop:Boolean = false,
    isLoading: Boolean =false
) {
    Card(
        modifier = modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            .background(Color.White)
            .fillMaxWidth()
            .clickable(enabled = !isLoading) {
                onItemSelected.invoke(data.title, data.link)
            },
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.White
    ) {





    }

}