package com.fashare.mvvm_juejin.model.category

import java.io.Serializable

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-16
 * Time: 01:11
 */
class HomeCategoryListBean: Serializable {
    val total = 0
    val categoryList = emptyList<Item>()

    /**
     * User: fashare(153614131@qq.com)
     * Date: 2017-09-16
     * Time: 01:10
     */
    class Item: Serializable{
        /**
         * id : 5562b410e4b00c57d9b94a92
         * name : Android
         * title : android
         * createdAt : 2015-05-24T21:33:04Z
         * updatedAt : 2016-04-21T19:19:13Z
         * icon : https://dn-mhke0kuv.qbox.me/225aafca3a440e5d.png
         * background : https://dn-mhke0kuv.qbox.me/ec8d337c485c4db2.png
         * tagId : 5597838ee4b08a686ce2319d
         * weight : 1
         * isSubscribe : false
         */

        var id: String? = null
        var name: String? = null
        var title: String? = null
        var createdAt: String? = null
        var updatedAt: String? = null
        var icon: String? = null
        var background: String? = null
        var tagId: String? = null
        var weight: String? = null
        var isSubscribe: Boolean = false
    }
}

