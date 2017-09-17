package com.fashare.mvvm_juejin.model.comment

import com.fashare.mvvm_juejin.model.user.UserBean
import java.io.Serializable

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-17
 * Time: 18:13
 */
class CommentListBean : Serializable {
    val count = 0
    val targetId = ""
    val targetType = ""
    val comments = emptyList<Item>()

    /**
     * id : 599a9ce16fb9a002225a6cb1
     * content : 请问掘金，需要购买域名吗，http://coding.so
     * userId : 599a9c98f265da247e7d9599
     * respUser : 56fa9a7c2db6ce00478e6bda
     * respComment :
     * userInfo : {"objectId":"599a9c98f265da247e7d9599","username":"smartmi","avatarLarge":"","selfDescription":"","jobTitle":"","company":"","viewedEntriesCount":0,"collectedEntriesCount":0,"isFollow":false}
     * respUserInfo : {"objectId":"56fa9a7c2db6ce00478e6bda","username":"膜法小编","avatarLarge":"https://dn-mhke0kuv.qbox.me/7df0063363b254c6a0d4.png?imageView/2/w/100/h/100/q/80/format/png","selfDescription":"申请掘金专栏加微信：memindcn。","jobTitle":"膜法师一等","company":"掘金","viewedEntriesCount":7679,"collectedEntriesCount":612,"isFollow":false}
     * likesCount : 0
     * picList : []
     * createdAt : 2017-08-21T08:42:09.002Z
     * updatedAt : 2017-08-21T08:42:09.002Z
     * subCount : 3
     * topComment : [{"id":"599a9d0cf265da0225b2bc3c","content":"联系方式可否给一下？","userId":"56fa9a7c2db6ce00478e6bda","respUser":"599a9c98f265da247e7d9599","respComment":"599a9ce16fb9a002225a6cb1","userInfo":{"objectId":"56fa9a7c2db6ce00478e6bda","username":"膜法小编","avatarLarge":"https://dn-mhke0kuv.qbox.me/7df0063363b254c6a0d4.png?imageView/2/w/100/h/100/q/80/format/png","selfDescription":"申请掘金专栏加微信：memindcn。","jobTitle":"膜法师一等","company":"掘金","viewedEntriesCount":7679,"collectedEntriesCount":612,"isFollow":false},"respUserInfo":{"objectId":"599a9c98f265da247e7d9599","username":"smartmi","avatarLarge":"","selfDescription":"","jobTitle":"","company":"","viewedEntriesCount":0,"collectedEntriesCount":0,"isFollow":false},"likesCount":0,"picList":[],"createdAt":"2017-08-21T08:42:52.621Z","updatedAt":"2017-08-21T08:42:52.621Z","subCount":0,"topComment":null,"isLiked":false}]
     * isLiked : false
     */
    class Item : Serializable {
        val id: String? = null
        val content: String? = null
        val userId: String? = null
        val respUser: String? = null
        val respComment: String? = null
        val userInfo: UserBean? = null
        val respUserInfo: UserBean? = null
        val likesCount: Int = 0
        val createdAt: String? = null
        val updatedAt: String? = null
        val subCount: Int = 0
        val isLiked: Boolean = false
        val picList: List<*>? = null
    }
}