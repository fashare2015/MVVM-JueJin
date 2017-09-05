package com.fashare.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    /**
     * baseUrl of each Api
     */
    @Target(AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BaseUrl(val value: String)

    private val mRetrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .client(OkHttpFactory.client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    fun <T> getApi(clazz: Class<T>): T {
        val baseUrl = clazz.getAnnotation(BaseUrl:: class.java).value
        if(baseUrl.isEmpty())
            throw IllegalArgumentException("@BaseUrl of ${clazz.simpleName} is empty")
        return mRetrofitBuilder.baseUrl(baseUrl).build().create(clazz)
    }
}
