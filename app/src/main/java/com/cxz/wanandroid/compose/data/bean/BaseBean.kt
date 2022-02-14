package com.cxz.wanandroid.compose.data.bean

import androidx.compose.runtime.Immutable

/**
 * created by AqrLei on 2022/2/14
 */
@Immutable
data class BaseBean<T>(
    val errorCode: Int,
    val errorMessage: String,
    val data: T?
)




