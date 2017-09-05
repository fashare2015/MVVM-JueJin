package com.fashare.mvvm_juejin.repo

import com.google.gson.Gson

/**
 * Response 返回结果
 */
class Response<T> {
    companion object Initializer {
        private val GSON: Gson by lazy { Gson() }
    }

    var s = -1          // errorCode
    var m = ""          // errorMsg
    var d: T? = null    // data

    override fun toString(): String {
        return GSON.toJson(this)
    }
}
