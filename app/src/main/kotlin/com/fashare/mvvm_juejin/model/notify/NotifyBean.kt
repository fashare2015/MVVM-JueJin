package com.fashare.mvvm_juejin.model.notify

import com.fashare.mvvm_juejin.model.user.UserBean

/**
 * objectId : 59b73e69ef23ab6f3540a08b
 * createdAtString : 2017-09-12T01:54:47.107Z
 * updatedAtString : 2017-09-12T01:54:47.107Z
 * check : true
 * type : comment
 * category : comment
 * beforeAtString : 2017-09-12T01:54:47.107Z
 * afterAtString : 2017-09-12T01:54:47.107Z
 * comment : {"id":"59b550f351882502246d5c30","content":"除了线程池以外，基本上都知道。线程与并发该怎么掌握呢？不写网络框架的话，平时基本接触不到。","userId":"57bd25f4a34131005b211b84","targetId":"59b541c85188257e81536d75","targetType":"entry","respUser":"56949a9960b2e058a42be0ba","respComment":"","firstComment":"","selfFirstComment":"","likesCount":0,"picList":[],"createdAt":"2017-09-10T22:49:23.252+08:00","updatedAt":"2017-09-10T22:49:23.252+08:00","subCount":1,"topComment":null,"isLiked":false}
 * reply : {"id":"59b73e66f265da0225b2c5e4","content":"还有数据结构","userId":"57ad6b9aa341310060e6c2ce","targetId":"59b541c85188257e81536d75","targetType":"entry","respUser":"57bd25f4a34131005b211b84","respComment":"59b550f351882502246d5c30","firstComment":"","selfFirstComment":"59b550f351882502246d5c30","likesCount":0,"picList":[],"createdAt":"2017-09-12T09:54:46.999+08:00","updatedAt":"2017-09-12T09:54:46.999+08:00","subCount":0,"topComment":null,"isLiked":false}
 * entry : {"objectId":"59b541c85188257e81536d75","title":"Android 开发者该如何进阶？","url":"https://juejin.im/post/59b541795188257e8822dfd9","type":"post","screenshotUrl":"https://user-gold-cdn.xitu.io/2017/9/10/de59f88be631f46c379221e0281ed57a","originalUrl":"https://juejin.im/post/59b541795188257e8822dfd9"}
 * count : 1
 */

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class NotifyBean {
    enum class Types(val type: String){
        COLLECTION("collection"),
        COMMENT("comment")
    }

    var objectId: String? = null
    var createdAtString: String? = null
    var updatedAtString: String? = null
    var isCheck: Boolean = false
    var type: String? = null
    var category: String? = null
    var beforeAtString: String? = null
    var afterAtString: String? = null
    var comment: CommentBean? = null
    var reply: ReplyBean? = null
    var entry: EntryBean? = null
    var users: List<UserBean> = emptyList()
    var count: Int = 0

    /**
     * id : 59b550f351882502246d5c30
     * content : 除了线程池以外，基本上都知道。线程与并发该怎么掌握呢？不写网络框架的话，平时基本接触不到。
     * userId : 57bd25f4a34131005b211b84
     * targetId : 59b541c85188257e81536d75
     * targetType : entry
     * respUser : 56949a9960b2e058a42be0ba
     * respComment :
     * firstComment :
     * selfFirstComment :
     * likesCount : 0
     * picList : []
     * createdAt : 2017-09-10T22:49:23.252+08:00
     * updatedAt : 2017-09-10T22:49:23.252+08:00
     * subCount : 1
     * topComment : null
     * isLiked : false
     */
    class CommentBean {
        var id: String? = null
        var content: String? = null
        var userId: String? = null
        var targetId: String? = null
        var targetType: String? = null
        var respUser: String? = null
        var respComment: String? = null
        var firstComment: String? = null
        var selfFirstComment: String? = null
        var likesCount: Int = 0
        var createdAt: String? = null
        var updatedAt: String? = null
        var subCount: Int = 0
        var topComment: Any? = null
        var isIsLiked: Boolean = false
        var picList: List<*>? = null
    }

    /**
     * id : 59b73e66f265da0225b2c5e4
     * content : 还有数据结构
     * userId : 57ad6b9aa341310060e6c2ce
     * targetId : 59b541c85188257e81536d75
     * targetType : entry
     * respUser : 57bd25f4a34131005b211b84
     * respComment : 59b550f351882502246d5c30
     * firstComment :
     * selfFirstComment : 59b550f351882502246d5c30
     * likesCount : 0
     * picList : []
     * createdAt : 2017-09-12T09:54:46.999+08:00
     * updatedAt : 2017-09-12T09:54:46.999+08:00
     * subCount : 0
     * topComment : null
     * isLiked : false
     */
    class ReplyBean {
        var id: String? = null
        var content: String? = null
        var userId: String? = null
        var targetId: String? = null
        var targetType: String? = null
        var respUser: String? = null
        var respComment: String? = null
        var firstComment: String? = null
        var selfFirstComment: String? = null
        var likesCount: Int = 0
        var createdAt: String? = null
        var updatedAt: String? = null
        var subCount: Int = 0
        var topComment: Any? = null
        var isIsLiked: Boolean = false
        var picList: List<*>? = null
    }

    /**
     * objectId : 59b541c85188257e81536d75
     * title : Android 开发者该如何进阶？
     * url : https://juejin.im/post/59b541795188257e8822dfd9
     * type : post
     * screenshotUrl : https://user-gold-cdn.xitu.io/2017/9/10/de59f88be631f46c379221e0281ed57a
     * originalUrl : https://juejin.im/post/59b541795188257e8822dfd9
     */
    class EntryBean {
        var objectId: String? = null
        var title: String? = null
        var url: String? = null
        var type: String? = null
        var screenshotUrl: String? = null
        var originalUrl: String? = null
    }

    // -------------   解析数据   --------------
    fun parseUserAvatar(): String{
        val data = this
        return data.users.let{
            if(it.isEmpty()) "" else it[0].avatarLarge?: ""
        }
    }

    fun parseTitle(): String{
        val data = this
        return data.users.let{
                    val username = if(it.isEmpty()) "" else it[0].username?: "未知用户"
                    val userCount = if(it.size > 1) "等${data.users.size}人" else ""
                    username + userCount + " "
                } +
                 data.type.let{
                    when(it){
                        NotifyBean.Types.COLLECTION.type    -> "喜欢了"
                        NotifyBean.Types.COMMENT.type       -> {
                            if(data.reply != null)
                                "回复了"
                            else if(data.comment != null)
                                "评论了"
                            else
                                "喜欢了"
                        }
                        else -> "喜欢了"
                    }
                } +
                "您的文章" +
                (data.entry?.title?: "")
    }

    fun parseContent(): String{
        val data = this
        return data.type.let{
            when(it){
                NotifyBean.Types.COLLECTION.type    -> ""
                NotifyBean.Types.COMMENT.type       -> {
                    if(data.reply != null)          // 回复
                        data.reply?.content?: ""
                    else if(data.comment != null)   // 评论
                        data.comment?.content?: ""
                    else
                        ""
                }
                else -> ""
            }
        }
    }
}
