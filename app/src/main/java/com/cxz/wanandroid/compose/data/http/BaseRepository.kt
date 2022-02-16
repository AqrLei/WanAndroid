package com.cxz.wanandroid.compose.data.http

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cxz.wanandroid.compose.data.bean.BaseBean
import com.cxz.wanandroid.compose.data.bean.PageList
import com.cxz.wanandroid.compose.data.http.api.ApiService
import com.cxz.wanandroid.compose.data.http.pageing.BasePagingSource
import com.cxz.wanandroid.compose.data.http.pageing.PagingFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * created by AqrLei on 2022/2/14
 */

open class BaseRepository {

    protected val apiService by lazy { create(ApiService::class.java) }

    protected fun <T> flowable(call: suspend () -> BaseBean<T>): Flow<HttpResult<T>> {
        return flow {
            val result = try {
                val response = call()
                if (response.errorCode == 0) {
                    if (response.data != null) {
                        HttpResult.Success(response.data)
                    } else {
                        throw Exception("the result of remote's request is null")
                    }
                } else {
                    throw Exception("code: ${response.errorCode}, message:${response.errorMessage}")
                }
            } catch (e: Exception) {
                HttpResult.Error(e)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    protected fun <T : Any> pager(
        initKey: Int = 0,
        baseConfig: PagingConfig = PagingFactory().pageConfig,
        callAction: suspend (page: Int) -> BaseBean<PageList<T>>
    ): Flow<PagingData<T>> {
        return Pager(
            config = baseConfig,
            initialKey = initKey,
            pagingSourceFactory = {
                BasePagingSource {
                    try {

                        val response = callAction(it)
                        if (response.errorCode == 0) {
                            if (response.data != null) {
                                HttpResult.Success(callAction(it))
                            } else {
                                throw Exception("the result of remote's request is null")
                            }
                        } else {
                            throw Exception("code: ${response.errorCode}, message:${response.errorMessage}")
                        }
                    } catch (e: Exception) {
                        HttpResult.Error(e)
                    }
                }
            }
        ).flow
    }

    inline fun <reified T> create(clazz: Class<T>) = RetrofitFactory.instance.createService(clazz)
}



