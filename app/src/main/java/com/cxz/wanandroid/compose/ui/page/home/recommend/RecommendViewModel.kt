package com.cxz.wanandroid.compose.ui.page.home.recommend

import androidx.compose.runtime.mutableStateOf
import com.cxz.wanandroid.compose.data.bean.home.BannerBean
import com.cxz.wanandroid.compose.data.http.repository.HomeRepository
import com.cxz.wanandroid.compose.ui.BaseViewModel
import com.cxz.wanandroid.compose.ui.widget.BannerData

/**
 * created by AqrLei on 2022/2/15
 */
class RecommendViewModel : BaseViewModel() {

    val imageList = mutableStateOf(mutableListOf<BannerData>())
    val refreshing = mutableStateOf(false)

    init {
        fetchData()
    }

    fun fetchData() {
        async<List<BannerBean>> {
            api { HomeRepository.getBanners() }
            onBeforeCall { refreshing.value = true }
            onSuccess {
                imageList.value = it.map {
                    BannerData(
                        imageUrl = it.imagePath ?: "",
                        linkUrl = it.url ?: "",
                        title = it.title ?: ""
                    )
                }.toMutableList()
            }
            onFailure { imageList.value.clear() }
            onComplete { refreshing.value = false }
        }
    }

}