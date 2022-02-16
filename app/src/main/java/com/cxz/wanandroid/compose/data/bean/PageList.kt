package com.cxz.wanandroid.compose.data.bean

/**
 * created by AqrLei on 2022/2/16
 */
data class PageList<T>(
    val curPage: Int,
    val offset:Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total:Int,
    val datas: MutableList<T>
)