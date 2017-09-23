package com.fashare.mvvm_juejin

import android.app.Application
import android.content.Context
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.net.ApiFactory
import com.fashare.net.widget.OkHttpFactory
import okhttp3.Interceptor

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
class JueJinApp: Application() {
    companion object {
        lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initNet()
    }

    private fun initNet() {
        ApiFactory.okhttpClient = OkHttpFactory.create(Interceptor {
            val originRequest = it.request()

            // 添加 header 以及公共的 GET 参数
            val newRequest = originRequest.newBuilder()
                    .addHeader("X-Juejin-Src", "android")
                    .url(originRequest.url().newBuilder()
                            .addQueryParameter("uid", LocalUser.userToken?.user_id?:"unlogin")
                            .addQueryParameter("token", LocalUser.userToken?.token?:"")
                            .addQueryParameter("device_id", "b9ae8b6a-efe0-4944-b574-b01a3a1303ee")
                            .addQueryParameter("src", "android")
                            .build()
                    ).build()

            it.proceed(newRequest)
        })
    }
}
