package com.fashare.mvvm_juejin.repo

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
    @GET("/v1/get_entry_by_hot_recomment")
    fun getEntryByHotRecomment(@Query("uid") uid: String,
                               @Query("limit") limit: String,
                               @Query("token") token: String,
                               @Query("device_id") device_id: String,
                               @Query("src") src: String): Observable<Response<HotRecomment>>
}