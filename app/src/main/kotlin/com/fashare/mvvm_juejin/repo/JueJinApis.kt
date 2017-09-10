package com.fashare.mvvm_juejin.repo

import com.fashare.mvvm_juejin.model.ArticleListBean
import com.fashare.mvvm_juejin.model.HotRecomment
import com.fashare.net.ApiFactory
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by apple on 2017/9/6.
 */
@ApiFactory.BaseUrl("https://timeline-merger-ms.juejin.im/")
interface JueJinApis {
    // 热门推荐
    @GET("/v1/get_entry_by_hot_recomment")
    fun getEntryByHotRecomment(@Query("uid") uid: String,
                               @Query("limit") limit: String,
                               @Query("token") token: String,
                               @Query("device_id") device_id: String,
                               @Query("src") src: String): Observable<Response<HotRecomment>>

    // 文章列表
    @GET("/v1/get_entry_by_timeline")
    fun getEntryByTimeLine(@Query("uid") uid: String,
                       @Query("before") before: String,
                       @Query("limit") limit: String,
                       @Query("token") token: String,
                       @Query("device_id") device_id: String,
                       @Query("src") src: String): Observable<Response<ArticleListBean>>
}