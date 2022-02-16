package com.cxz.wanandroid.compose.data.http.pageing

import androidx.paging.PagingConfig

/**
 * created by AqrLei on 2022/2/16
 */
class PagingFactory {
    val pageConfig = PagingConfig(
        pageSize = 20,
        enablePlaceholders = true,
        prefetchDistance = 4,
        initialLoadSize = 20
    )
}