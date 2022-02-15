package com.cxz.wanandroid.compose.data.http

import com.cxz.wanandroid.BuildConfig
import com.cxz.wanandroid.app.App
import com.cxz.wanandroid.constant.Constant
import com.cxz.wanandroid.constant.HttpConstant
import com.cxz.wanandroid.http.interceptor.CacheInterceptor
import com.cxz.wanandroid.http.interceptor.HeaderInterceptor
import com.cxz.wanandroid.http.interceptor.SaveCookieInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

/**
 * created by AqrLei on 2022/1/18
 */
class RetrofitFactory private constructor() {

    companion object {

        val instance = Holder.getInstance()

        private var isDebug: Boolean = BuildConfig.DEBUG
        private var baseUrl: String = Constant.BASE_URL
    }


    private val loggingInterceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().also {
            it.level = if (isDebug) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    private val cache: Cache
        get() {
            val cacheFile = File(App.context.cacheDir, "cache")
            return Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)
        }

    private val gson: Gson
        get() = GsonBuilder()
            .serializeSpecialFloatingPointValues()
            .setLenient()
            .disableHtmlEscaping()
            .create()

    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(SaveCookieInterceptor())
            .addInterceptor(CacheInterceptor())
            .cache(cache)
            .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    fun <T> createService(service: Class<T>): T = retrofit.create(service)

    private object Holder {
        fun getInstance() = RetrofitFactory()
    }
}