package com.cxz.wanandroid.compose.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChangeConsumed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.cxz.wanandroid.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

/**
 * created by AqrLei on 2022/2/14
 */

@Immutable
data class BannerData(
    val title: String,
    val linkUrl: String,
    val imageUrl: String
)

private const val MAX_VALUE = 500

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun Banner(
    list: List<BannerData>?,
    timeMillis: Long = 3000,
    @DrawableRes loadImage: Int = R.drawable.no_banner,
    indicatorAlignment: Alignment = Alignment.BottomCenter,
    onClick: (link: String, title: String) -> Unit
) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        list?.takeIf { it.isNotEmpty() }?.let { bannerList ->
            val pageCount = bannerList.size
            val startIndex = MAX_VALUE / 2
            val pagerState = rememberPagerState(initialPage = startIndex)

            var executeChangePage by remember { mutableStateOf(false) }
            var currentPageIndex = startIndex

            LaunchedEffect(pagerState.currentPage, executeChangePage) {
                if (pagerState.pageCount > 0) {
                    delay(timeMillis)
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }

            HorizontalPager(
                count = MAX_VALUE,
                state = pagerState,
                modifier = Modifier
                    .pointerInput(pagerState.currentPage) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent(PointerEventPass.Initial)
                                event.changes
                                    .firstOrNull()
                                    ?.let { dragEvent ->
                                        when {
                                            //当前移动手势是否已被消费
                                            dragEvent.positionChangeConsumed() -> return@awaitPointerEventScope
                                            //是否已经按下(忽略按下手势已消费标记)
                                            dragEvent.changedToDownIgnoreConsumed() -> {
                                                currentPageIndex = pagerState.currentPage
                                            }
                                            //是否已经抬起(忽略按下手势已消费标记)
                                            dragEvent.changedToUpIgnoreConsumed() -> {

                                                //当pageCount大于1，且手指抬起时如果页面没有改变，就手动触发动画
                                                if (currentPageIndex == pagerState.currentPage && pagerState.pageCount > 1) {
                                                    executeChangePage = !executeChangePage
                                                }
                                            }

                                        }
                                    }
                            }
                        }
                    }
                    .clickable {
                        val page = getRealCurIndex(pagerState.currentPage, startIndex, pageCount)
                        with(list[page]) {
                            onClick.invoke(linkUrl, title)
                        }
                    }
                    .fillMaxWidth()
                    .height(200.dp)
            ) { index ->
                val page = getRealCurIndex(index, startIndex, pageCount)
                Image(
                    painter = rememberImagePainter(data = list[page].imageUrl),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .align(indicatorAlignment)
                    .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
            ) {

                //指示点
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in list.indices) {
                        //大小
                        var size by remember { mutableStateOf(5.dp) }
                        size = if (pagerState.currentPage == i) 7.dp else 5.dp


                        val curIndex =
                            getRealCurIndex(pagerState.currentPage, startIndex, pageCount)
                        //颜色
                        val color =
                            if (curIndex == i) MaterialTheme.colors.primary else Color.Gray

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(color)
                                //当size改变的时候以动画的形式改变
                                .animateContentSize()
                                .size(size)
                        )
                        //指示点间的间隔
                        if (i != list.lastIndex) Spacer(
                            modifier = Modifier
                                .height(0.dp)
                                .width(4.dp)
                        )
                    }
                }

            }


        } ?: Image(
            painterResource(loadImage),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

    }

}

private fun getRealCurIndex(curIndex: Int, startIndex: Int, pageCount: Int): Int {
    return (curIndex - startIndex).floorMod(pageCount)
}

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}
