package com.cxz.wanandroid.compose.data.http

import com.cxz.wanandroid.compose.data.bean.BaseBean
import com.cxz.wanandroid.compose.data.http.api.ApiService
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

    inline fun <reified T> create(clazz: Class<T>) = RetrofitFactory.instance.createService(clazz)
}



