package com.fashare.mvvm_juejin.model

import com.fashare.mvvm_juejin.model.article.ArticleListBean
import java.io.Serializable

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-06
 * Time: 01:00
 */
class HotRecomment : Serializable {
    var banner: BannerListBean? = null
    var entry: ArticleListBean? = null

}
