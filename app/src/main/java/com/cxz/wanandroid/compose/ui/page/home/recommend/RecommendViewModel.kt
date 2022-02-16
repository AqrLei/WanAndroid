package com.cxz.wanandroid.compose.ui.page.home.recommend

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.cxz.wanandroid.compose.data.bean.home.Article
import com.cxz.wanandroid.compose.data.bean.home.BannerBean
import com.cxz.wanandroid.compose.data.http.repository.HomeRepository
import com.cxz.wanandroid.compose.data.http.repository.PageArticle
import com.cxz.wanandroid.compose.ui.BaseViewModel
import com.cxz.wanandroid.compose.ui.widget.BannerData

/**
 * created by AqrLei on 2022/2/15
 */
class RecommendViewModel : BaseViewModel() {

    val imageList = mutableStateListOf<BannerData>()
    val refreshing = mutableStateOf(false)

    val pagingData = MutableLiveData<PageArticle>(null)
    val topArticles = mutableStateListOf<Article>()

    val currentIndex = mutableStateOf(0)

    private val repo = HomeRepository

    init {
        refresh()
    }

    fun refresh() {
        refreshing.value = true
        fetchBannerList()
        fetchTopArticles()
        fetchPageArticle()
    }

    private fun fetchBannerList() {
        async<List<BannerBean>> {
            api { repo.getBanners() }
            onSuccess { result ->
                imageList.addAll(result.map {
                    BannerData(
                        imageUrl = it.imagePath ?: "",
                        linkUrl = it.url ?: "",
                        title = it.title ?: ""
                    )
                }.toMutableList())
            }
            onFailure { imageList.clear() }
        }
    }

    private fun fetchTopArticles() {
        async<List<Article>> {
            api { repo.getTopArticle() }
            onSuccess {
                topArticles.clear()
                topArticles.addAll(it)
            }
            onFailure { topArticles.clear() }
        }
    }

    private fun fetchPageArticle() {
        asyncPage<Article> {
            api { repo.getPageArticle() }
            onSuccess { pagingData.value = it }
            onComplete { refreshing.value = false }
        }
    }
}