package com.cxz.wanandroid.compose.data.bean.home

import androidx.compose.runtime.Immutable

/**
 * created by AqrLei on 2022/2/15
 */
@Immutable
data class BannerBean(
    val desc: String?,
    val id: Int?,
    val imagePath: String?,
    val isVisible: Int?,
    val order: Int?,
    val title: String?,
    val type: Int?,
    val url: String?
)