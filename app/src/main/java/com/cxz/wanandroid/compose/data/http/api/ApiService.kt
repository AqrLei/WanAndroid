package com.cxz.wanandroid.compose.data.http.api

import com.cxz.wanandroid.compose.data.bean.BaseBean
import com.cxz.wanandroid.compose.data.bean.home.BannerBean
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * created by AqrLei on 2022/2/15
 */
interface ApiService {


    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    suspend fun getBanners(): BaseBean<List<BannerBean>>

    suspend fun getTopArticles(): BaseBean<Any>

    suspend fun getArticles(
        @Path("pageNum") pageNum: Int
    ): BaseBean<Any>

    suspend fun getKnowledgeTree(): BaseBean<Any>

    suspend fun getKnowledgeList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseBean<Any>

    suspend fun getNavigationList(): BaseBean<Any>

    suspend fun getProjectTree(): BaseBean<Any>

    suspend fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseBean<Any>


    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseBean<Any>

    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): BaseBean<Any>

    suspend fun logout(): BaseBean<Any>


    suspend fun getCollectList(@Path("page") page: Int): BaseBean<Any>

    suspend fun addCollectArticle(@Path("id") id: Int): BaseBean<Any>

    suspend fun addCollectOutsideArticle(
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("link") link: String
    ): BaseBean<Any>

    suspend fun cancelCollectArticle(@Path("id") id: Int): BaseBean<Any>

    suspend fun removeCollectArticle(
        @Path("id") id: Int,
        @Field("originId") originId: Int = -1
    ): BaseBean<Any>


    suspend fun getHotSearchKeyWord(): BaseBean<Any>

    suspend fun queryBySearchKey(
        @Path("page") page: Int,
        @Field("k") key: String
    ): BaseBean<Any>


    suspend fun getTodoList(
        @Path("page") page: Int,
        @QueryMap map: MutableMap<String, Any>
    ): BaseBean<Any>

    suspend fun getNoTodoList(
        @Path("page") page: Int,
        @Path("type") type: Int
    ): BaseBean<Any>

    suspend fun getDoneTodoList(
        @Path("page") page: Int,
        @Path("type") type: Int
    ): BaseBean<Any>

    suspend fun updateTodoById(
        @Path("id") id: Int,
        @Field("status") status: Int
    ): BaseBean<Any>

    suspend fun deleteTodoById(
        @Path("id") id: Int
    ): BaseBean<Any>

    suspend fun addTodo(@FieldMap map: MutableMap<String, Any>): BaseBean<Any>

    suspend fun updateTodo(
        @Path("id") id: Int,
        @FieldMap map: MutableMap<String, Any>
    ): BaseBean<Any>

    suspend fun getWXChapters(): BaseBean<Any>

    suspend fun getWXArticles(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): BaseBean<Any>

    suspend fun queryWXArticles(
        @Path("id") id: Int,
        @Query("k") key: String,
        @Path("page") page: Int
    ): BaseBean<Any>


    suspend fun getUserInfo(): BaseBean<Any>


    suspend fun getUserScoreList(@Path("page") page: Int): BaseBean<Any>


    suspend fun getRankList(@Path("page") page: Int): BaseBean<Any>


    suspend fun getShareList(@Path("page") page: Int): BaseBean<Any>


    suspend fun shareArticle(@FieldMap map: MutableMap<String, Any>): BaseBean<Any>


    suspend fun deleteShareArticle(@Path("id") id: Int): BaseBean<Any>


    suspend fun getSquareList(@Path("page") page: Int): BaseBean<Any>

}