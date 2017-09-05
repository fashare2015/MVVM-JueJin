package com.fashare.net.exception


import android.util.Log
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

object ExceptionFactory {
    //未知错误
    val UNKNOWN = 1000
    //解析错误
    val PARSE_ERROR = 1001
    //网络错误
    val NETWORK_ERROR = 1002
    //协议出错
    val HTTP_ERROR = 1003

    class ServerException(val code: Int, val msg: String) : RuntimeException()

    fun create(throwable: Throwable): ApiException {
        val apiException: ApiException
        if (throwable is ConnectException
                || throwable is SocketTimeoutException
                || throwable is UnknownHostException) {
            apiException = ApiException(throwable, NETWORK_ERROR, "网络连接错误")

        }else if (throwable is HttpException) {
            apiException = ApiException(throwable, HTTP_ERROR, "HTTP协议错误: ${throwable.code()}")

        } else if (throwable is JsonParseException
                || throwable is JsonSyntaxException
                || throwable is JsonIOException
                || throwable is JSONException
                || throwable is ParseException) {
            apiException = ApiException(throwable, PARSE_ERROR, "Json 解析错误")

        } else if (throwable is ServerException) {
            apiException = ApiException(throwable, throwable.code, throwable.msg)

        } else {
            apiException = ApiException(throwable, UNKNOWN, "未知错误")
        }

        Log.e(javaClass.name, apiException.toString())
        return apiException
    }
}
