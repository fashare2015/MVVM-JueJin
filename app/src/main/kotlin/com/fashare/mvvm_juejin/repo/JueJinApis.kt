package com.fashare.mvvm_juejin.repo

import com.fashare.mvvm_juejin.model.BannerListBean
import com.fashare.mvvm_juejin.model.HotRecomment
import com.fashare.mvvm_juejin.model.article.ArticleListBean
import com.fashare.mvvm_juejin.model.user.UserBean
import com.fashare.net.ApiFactory
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by apple on 2017/9/6.
 */
const val BASE_URL = "https://timeline-merger-ms.juejin.im/"

@ApiFactory.BaseUrl(BASE_URL)
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
    fun getEntryByTimeLine(@Query("category") category: String,
                           @Query("type") type: String,
                           @Query("uid") uid: String,
                           @Query("before") before: String,
                           @Query("limit") limit: String,
                           @Query("token") token: String,
                           @Query("device_id") device_id: String,
                           @Query("src") src: String): Observable<Response<ArticleListBean>>

    // 发现 - 热门文章列表
    @GET("/v1/get_entry_by_rank")
    fun getEntryByRank(@Query("uid") uid: String,
                       @Query("before") before: String,
                       @Query("limit") limit: String,
                       @Query("token") token: String,
                       @Query("device_id") device_id: String,
                       @Query("src") src: String): Observable<Response<ArticleListBean>>

    @ApiFactory.BaseUrl("https://auth-center-ms.juejin.im")
    interface User{
        class LoginParam{
            var login_type = "tel"
            var user = ""
            var psd = ""
            var client_id = "b9ae8b6a-efe0-4944-b574-b01a3a1303ee"
            var state = "nOOKnTFSCE"
            var src = "android"

            fun toMap(): Map<String, String>{
                return mapOf(
                        "login_type" to login_type,
                        "user" to user,
                        "psd" to psd,
                        "client_id" to client_id,
                        "state" to state,
                        "src" to src
                )
//                return (Gson().toJsonTree(this) as JsonObject).let{
//                    JsonObject:: class.java.getField("members").get(it) as Map<String, *>
//                }
            }
        }

        @FormUrlEncoded
        @Headers(
//            "Content-Type: application/x-www-form-urlencoded",
            "X-Juejin-Src:android"
        )
        @POST("/v1/login")
        fun login(@FieldMap param: Map<String, String>): Observable<Response<UserBean.TokenBean>>

        @ApiFactory.BaseUrl("https://user-storage-api-ms.juejin.im")
        interface Storage{
            @POST("v1/getUserInfo")
            fun getUserInfo(@Query("uid") uid: String,
                            @Query("current_uid") current_uid: String,
                            @Query("token") token: String,
                            @Query("device_id") device_id: String,
                            @Query("src") src: String): Observable<Response<UserBean>>
        }
    }

    // 发现 - banner
    @ApiFactory.BaseUrl("https://banner-storage-ms.juejin.im")
    interface BannerStorage{
        @GET("v1/get_banner")
        fun getBanner(@Query("position") position: String,
                      @Query("page") page: Int,
                      @Query("pageSize") pageSize: Int,
                      @Query("platform") platform: String,
                      @Query("token") token: String,
                      @Query("device_id") device_id: String,
                      @Query("src") src: String): Observable<Response<BannerListBean>>
    }
}
