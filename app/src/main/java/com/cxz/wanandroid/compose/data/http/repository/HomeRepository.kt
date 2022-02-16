package com.cxz.wanandroid.compose.data.http.repository

import androidx.paging.PagingData
import com.cxz.wanandroid.compose.data.bean.home.Article
import com.cxz.wanandroid.compose.data.bean.home.BannerBean
import com.cxz.wanandroid.compose.data.http.BaseRepository
import com.cxz.wanandroid.compose.data.http.HttpResult
import kotlinx.coroutines.flow.Flow

/**
 * created by AqrLei on 2022/2/15
 */
typealias Banner = Flow<HttpResult<List<BannerBean>>>
typealias PageArticle = Flow<PagingData<Article>>
typealias ArticleList = Flow<HttpResult<List<Article>>>

object HomeRepository : BaseRepository() {

    suspend fun getBanners(): Banner = flowable { apiService.getBanners() }

    suspend fun getTopArticle(): ArticleList = flowable { apiService.getTopArticles() }

    suspend fun getPageArticle(): PageArticle = pager { page -> apiService.getArticles(page) }

}