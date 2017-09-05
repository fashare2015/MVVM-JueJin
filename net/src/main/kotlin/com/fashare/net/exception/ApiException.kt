package com.fashare.net.exception

/**
 * 对外统一的异常
 */
class ApiException(throwable: Throwable,
                   val errorCode: Int,
                   var errorMsg: String? = null) : Exception(throwable) {
    override fun toString(): String {
        return "ApiException(errorCode=$errorCode, errorMsg=$errorMsg)"
    }
}
