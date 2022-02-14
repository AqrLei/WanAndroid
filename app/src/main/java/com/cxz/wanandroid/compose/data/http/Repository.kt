package com.cxz.wanandroid.compose.data.http

/**
 * created by AqrLei on 2022/2/14
 */

interface IRepository

inline fun <reified T> IRepository.create(clazz: Class<T> ) = RetrofitFactory.instance.createService(clazz)


class HttpRepository : IRepository {

}