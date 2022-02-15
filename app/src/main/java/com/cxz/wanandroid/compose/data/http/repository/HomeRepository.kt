package com.cxz.wanandroid.compose.data.http.repository

import com.cxz.wanandroid.compose.data.bean.home.BannerBean
import com.cxz.wanandroid.compose.data.http.BaseRepository
import com.cxz.wanandroid.compose.data.http.HttpResult
import kotlinx.coroutines.flow.Flow

/**
 * created by AqrLei on 2022/2/15
 */
typealias Banner = Flow<HttpResult<List<BannerBean>>>

object HomeRepository : BaseRepository() {

    suspend fun getBanners(): Banner = flowable { apiService.getBanners() }

}