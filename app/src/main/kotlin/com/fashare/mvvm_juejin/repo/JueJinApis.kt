package com.fashare.mvvm_juejin.repo

import com.blankj.utilcode.util.DeviceUtils
import com.fashare.mvvm_juejin.model.BannerListBean
import com.fashare.mvvm_juejin.model.HotRecomment
import com.fashare.mvvm_juejin.model.article.ArticleHtmlBean
import com.fashare.mvvm_juejin.model.article.ArticleListBean
import com.fashare.mvvm_juejin.model.category.HomeCategoryListBean
import com.fashare.mvvm_juejin.model.comment.CommentListBean
import com.fashare.mvvm_juejin.model.notify.NotifyBean
import com.fashare.mvvm_juejin.model.user.UserBean
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.net.ApiFactory
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by apple on 2017/9/6.
 */
@ApiFactory.BaseUrl("https://timeline-merger-ms.juejin.im/")
interface JueJinApis {
    // 首页 - 热门推荐
    @GET("/v1/get_entry_by_hot_recomment")
    fun getEntryByHotRecomment(@Query("limit") limit: String = "20"): Observable<Response<HotRecomment>>

    // 首页 - 各子tab的热门推荐
    @GET("/v1/get_entry_by_period")
    fun getEntryByPeriod(@Query("category") categoryId: String = "",
                         @Query("limit") limit: String = "20",
                         @Query("period") period: String = "3day"): Observable<Response<ArticleListBean>>


    // 文章列表
    @GET("/v1/get_entry_by_timeline")
    fun getEntryByTimeLine(@Query("category") categoryId: String = "",
                           @Query("type") type: String = "",
                           @Query("before") before: String = "",
                           @Query("limit") limit: String = "20"): Observable<Response<ArticleListBean>>

    // 发现 - 热门文章列表
    @GET("/v1/get_entry_by_rank")
    fun getEntryByRank(@Query("before") before: String,
                       @Query("limit") limit: String = "30"): Observable<Response<ArticleListBean>>

    // 文章 - 相关文章列表
    @GET("/v1/get_related_entry")
    fun getRelatedEntry(@Query("entryId") entryId: String,
                        @Query("limit") limit: String = "4"): Observable<Response<ArticleListBean>>

    @ApiFactory.BaseUrl("https://auth-center-ms.juejin.im")
    interface User{
        @FormUrlEncoded
        @POST("/v1/login")
        fun login(@Field("login_type") login_type: String,
                  @Field("user") user: String = "tel",
                  @Field("psd") psd: String = "tel",
                  @Field("client_id") client_id: String = DeviceUtils.getAndroidID(),
                  @Field("state") state: String = "nOOKnTFSCE",
                  @Field("src") src: String = "android"): Observable<Response<UserBean.TokenBean>>


        @ApiFactory.BaseUrl("https://user-storage-api-ms.juejin.im")
        interface Storage{
            @FormUrlEncoded
            @POST("/v1/createUser")
            fun register(@Field("email") email: String,
                         @Field("username") username: String,
                         @Field("password") password: String,
                         @Field("mobilePhoneNumber") mobilePhoneNumber: String = "",
                         @Field("smsCode") smsCode: String = "",
                         @Field("src") src: String = "android"): Observable<Response<List<Any>>>

            @GET("v1/getUserInfo")
            fun getUserInfo(@Query("current_uid") current_uid: String = LocalUser.userToken?.user_id?: ""): Observable<Response<UserBean>>
        }
    }

    // 发现 - banner
    @ApiFactory.BaseUrl("https://banner-storage-ms.juejin.im")
    interface BannerStorage{
        @GET("v1/get_banner")
        fun getBanner(@Query("position") position: String,
                      @Query("page") page: Int = 0,
                      @Query("pageSize") pageSize: Int = 20,
                      @Query("platform") platform: String = "android"): Observable<Response<BannerListBean>>
    }

    @ApiFactory.BaseUrl("https://ufp-api-ms.juejin.im")
    interface Notify{
        @GET("/v1/getUserNotification")
        fun getUserNotification(@Query("before") before: String,
                                @Query("limit") limit: String = "30"): Observable<Response<List<NotifyBean>>>
    }

    @ApiFactory.BaseUrl("https://gold-tag-ms.juejin.im")
    interface Tags{
        @GET("/v1/categories")
        fun getCategories(): Observable<Response<HomeCategoryListBean>>
    }

    interface Article{
        @ApiFactory.BaseUrl("https://entry-view-storage-api-ms.juejin.im")
        interface Html{
            @GET("/v1/getEntryView")
            fun getHtml(@Query("entryId") articleId: String): Observable<Response<ArticleHtmlBean>>
        }
    }

    @ApiFactory.BaseUrl("https://comment-wrapper-ms.juejin.im")
    interface Comment{
        @GET("/v1/comments/entry/{articleId}")
        fun getComments(@Path("articleId") articleId: String,
                        @Query("createdAt") before: String,
                        @Query("rankType") rankType: String = "new"): Observable<Response<CommentListBean>>
    }
}
