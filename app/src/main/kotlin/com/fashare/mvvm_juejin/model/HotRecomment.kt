package com.fashare.mvvm_juejin.model

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-06
 * Time: 01:00
 */
class HotRecomment {
    var banner: BannerBean? = null
    var entry: ArticleListBean? = null

    class BannerBean {
        var total: Int = 0
        /**
         * objectId : 59ad320d6fb9a0247d4f6a8c
         * description : 用的什么程序呀？放的什么壁纸呀？有什么有意思的屏保吗？！看看大家都是如何优雅地使用桌面的！
         * startedAt : 2017-09-04T11:06:00.000Z
         * type : vote
         * title : 晒晒你的电脑桌面 | 程序员的桌面是什么样的？
         * relatedObjectId : 59ad3029f265da2473445cd3
         * url : https://juejin.im/entry/59ad3029f265da2473445cd3/detail
         * position : recommend
         * screenshot : https://ad-gold-cdn.xitu.io/15045227325732034a704e636288d5fb4549228b2a49e.jpg
         * platform : android
         * endedAt : 2017-09-06T09:17:00.000Z
         * createdAt : 2017-09-04T10:59:25.438Z
         * updatedAt : 1970-01-01T00:00:00.Z
         * osTime : 2017-09-05T17:03:07.461Z
         */

        var banner: List<BannerBean2>? = null

        class BannerBean2 {
            var objectId: String? = null
            var description: String? = null
            var startedAt: String? = null
            var type: String? = null
            var title: String? = null
            var relatedObjectId: String? = null
            var url: String? = null
            var position: String? = null
            var screenshot: String? = null
            var platform: String? = null
            var endedAt: String? = null
            var createdAt: String? = null
            var updatedAt: String? = null
            var osTime: String? = null
        }
    }
}
