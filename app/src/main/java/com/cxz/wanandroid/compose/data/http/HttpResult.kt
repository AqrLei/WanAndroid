package com.cxz.wanandroid.compose.data.http

/**
 * created by AqrLei on 2022/2/15
 */
sealed class HttpResult<out T> {
    data class Success<T>(val result: T) : HttpResult<T>()
    data class Error(val e: Exception) : HttpResult<Nothing>()
}