package com.fashare.mvvm_juejin

import android.app.Application
import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.Utils
import com.fashare.mvvm_juejin.repo.Response
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.net.ApiFactory
import com.fashare.net.widget.OkHttpFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.ResponseBody

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : 153614131@qq.com
 *     desc   :
 * </pre>
 */
class JueJinApp: Application() {
    companion object {
        lateinit var instance: Context

        val GSON = Gson()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Utils.init(this)
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
                            .addQueryParameter("device_id", DeviceUtils.getAndroidID())
                            .addQueryParameter("src", "android")
                            .build()
                    ).build()

            /** 处理不规范的返回值
             *  <-- 400 Bad Request
             *  {
             *      "s": 10012,
             *      "m": "密码错误",
             *      "d": []             // 应该返回 空对象{}, 否则 Json 解析异常
             *  }
             */
            var response = it.proceed(newRequest)
            response.newBuilder()
                    .apply {
                        val originBody = response.body()
                        var json = originBody?.string()
                        var res : Response<Any>? = null

                        try {
                            res = GSON.fromJson(json, object: TypeToken<Response<Any>>(){}.type)
                        }catch (e: Exception){
                            Log.e("initNet", "interceptor response" + e)
                        }
                        try {
                            res = GSON.fromJson(json, object: TypeToken<Response<List<Any>>>(){}.type)
                        }catch (e: Exception){
                            Log.e("initNet", "interceptor response" + e)
                        }

                        // 不成功，则移除 "d" 字段
                        if(1 != res?.s){
                            res?.d = null
                        }
                        json = GSON.toJson(res)

                        this.body(ResponseBody.create(originBody?.contentType(), json))
                    }
                    .apply {
                        this.code(if(response.code() in 400 .. 500) 200 else response.code())
                    }
                    .build()
        })
    }
}
