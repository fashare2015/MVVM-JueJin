package com.fashare.mvvm_juejin

import android.app.Application
import android.content.Context

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
    }
}
