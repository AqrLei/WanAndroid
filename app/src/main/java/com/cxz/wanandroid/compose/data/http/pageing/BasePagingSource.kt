package com.cxz.wanandroid.compose.data.http.pageing

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cxz.wanandroid.compose.data.bean.BaseBean
import com.cxz.wanandroid.compose.data.bean.PageList
import com.cxz.wanandroid.compose.data.http.HttpResult

/**
 * created by AqrLei on 2022/2/16
 */
class BasePagingSource<T : Any>(
    private val requestData: suspend (page: Int) -> HttpResult<BaseBean<PageList<T>>>
) : PagingSource<Int, T>() {
    private var page: Int = 1
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        page = params.key ?: 0
        return when (val response = requestData(page)) {
            is HttpResult.Error -> {
                LoadResult.Error(response.e)
            }
            is HttpResult.Success -> {
                val data = response.result.data
                val hasNotNext = (data!!.datas.size < params.loadSize) && data.over

                LoadResult.Page(
                    data = response.result.data.datas,
                    prevKey = if (page - 1 > 0) page - 1 else null,
                    nextKey = if (hasNotNext) null else page + 1
                )
            }
        }

    }
}