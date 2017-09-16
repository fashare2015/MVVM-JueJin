package com.fashare.mvvm_juejin.model.article

import java.io.Serializable

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-06
 * Time: 01:00
 */
class ArticleListBean : Serializable {
    var total: Int = 0
    var entrylist: List<ArticleBean>? = null
}
