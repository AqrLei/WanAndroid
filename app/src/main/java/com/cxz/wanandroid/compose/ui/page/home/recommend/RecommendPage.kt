package com.cxz.wanandroid.compose.ui.page.home.recommend

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.cxz.wanandroid.compose.ui.widget.Banner
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * created by AqrLei on 2022/2/15
 */
// 推荐
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun RecommendPage(
    viewModel: RecommendViewModel = viewModel()
) {

    val bannerList = remember { viewModel.imageList }
    val refreshing by remember { viewModel.refreshing }
    val currentPosition by remember { viewModel.currentIndex }
    val topArticle = remember { viewModel.topArticles }


    val pageArticleData = viewModel.pagingData.value?.collectAsLazyPagingItems()
    val isLoaded = pageArticleData?.loadState?.prepend?.endOfPaginationReached ?: false


    val listState = rememberLazyListState(currentPosition)
    val swipeRefreshState = rememberSwipeRefreshState(refreshing)
    val coroutineScope = rememberCoroutineScope()

    SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.refresh() }) {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            if (bannerList.isNotEmpty()) {
                item {
                    Banner(list = bannerList, onClick = { link, title ->
                        Log.d("AqrLei", "$title, $link")
                    })
                }
            }

            if (topArticle.isNotEmpty()) {
                itemsIndexed(topArticle) { index, item ->

                }

            }
            if (isLoaded) {
                pageArticleData?.takeIf { it.itemCount > 0 }?.let {

                } ?: let {
                    //TODO EmptyView
                }
            }
        }
    }
}