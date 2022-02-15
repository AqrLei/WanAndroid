package com.cxz.wanandroid.compose.ui.page.home.recommend

import com.cxz.wanandroid.compose.ui.widget.BannerData

/**
 * created by AqrLei on 2022/2/15
 */
data class RecommendViewState(
    var isRefreshing: Boolean = false,
    var bannerList: List<BannerData> = emptyList()
)