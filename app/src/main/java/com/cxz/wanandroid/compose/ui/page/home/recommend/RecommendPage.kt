package com.cxz.wanandroid.compose.ui.page.home.recommend

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
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


    val bannerList by remember { viewModel.imageList }
    val refreshing by remember { viewModel.refreshing }

    val swipeRefreshState = rememberSwipeRefreshState(refreshing)

    SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.fetchData() }) {
        Banner(list = bannerList, onClick = { link, title ->
            Log.d("AqrLei", "$title, $link")
        })
    }
}