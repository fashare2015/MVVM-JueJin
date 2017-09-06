package com.fashare.mvvm_juejin.model

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-06
 * Time: 01:00
 */
class HotRecomment {
    var banner: BannerBean? = null
    var entry: EntryBean? = null

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

    class EntryBean {
        var total: Int = 0
        /**
         * collectionCount : 240
         * commentsCount : 203
         * gfw : false
         * entryView :
         * subscribersCount : 0
         * ngxCachedTime : 1504630987
         * tags : [{"ngxCachedTime":1504630959,"ngxCached":true,"title":"开源","id":"5597a3cae4b08a686ce5d0fb"}]
         * updatedAt : 2017-09-05T17:03:05.512Z
         * rankIndex : 152.80284780731
         * hot : true
         * objectId : 59ad7c92f265da24921b5310
         * originalUrl : https://juejin.im/post/59ad758a518825243d1f28cb
         * buildTime : 1.504630985514E9
         * createdAt : 2017-09-04T16:17:22.580Z
         * user : {"community":{"weibo":{"uid":"5885816355","nickname":"墨镜猫jacky"},"wechat":{"nickname":"Android王世昌","uid":"oDv1Eww5c17raaESRVI8l9M6zsuE","expires_at":"1468246472191"},"github":{"nickname":"Jacky Wang","uid":"17797018","expires_at":"1468239341180"}},"collectedEntriesCount":3,"company":"映客","followersCount":916,"followeesCount":4,"role":"editor","postedPostsCount":5,"isAuthor":true,"postedEntriesCount":16,"totalCommentsCount":184,"ngxCachedTime":1504630984,"ngxCached":true,"viewedEntriesCount":1713,"jobTitle":"Android架构师","subscribedTagsCount":42,"totalCollectionsCount":4575,"username":"墨镜猫","avatarLarge":"https://dn-mhke0kuv.qbox.me/4ERoRptV8ySFHwtZ06bHGY7twEfJ0VJ1cVMU0tml","objectId":"562dc7cc60b20fc9817962a2"}
         * author :
         * screenshot :
         * original : true
         * hotIndex : 57093.4726
         * content : 中国首位00后CEO公然抄袭、复制我的开源作品 这几天我们或许都看到了这样的新闻，标题大多数为：《中国首位00后CEO...》，连雷军都进行了转发（如下）。但今天意想不到的事却发生了... 9.4号晚上，有一个我项目的关注者在我的开源项目智能电视桌面提了一个issue问题（截图如…
         * title : 中国首位00后CEO公然抄袭、复制我的开源作品
         * lastCommentTime : 2017-09-06T00:23:46.155Z
         * type : post
         * english : false
         * category : {"ngxCached":true,"title":"article","id":"5562b428e4b00c57d9b94b9d","name":"阅读","ngxCachedTime":1504630889}
         * viewsCount : 53676
         * isCollected : false
         */
        var entrylist: List<EntrylistBean>? = null

        class EntrylistBean {
            var collectionCount: Int = 0
            var commentsCount: Int = 0
            var isGfw: Boolean = false
            var entryView: String? = null
            var subscribersCount: Int = 0
            var ngxCachedTime: Int = 0
            var updatedAt: String? = null
            var rankIndex: Double = 0.toDouble()
            var isHot: Boolean = false
            var objectId: String? = null
            var originalUrl: String? = null
            var buildTime: Double = 0.toDouble()
            var createdAt: String? = null
            /**
             * community : {"weibo":{"uid":"5885816355","nickname":"墨镜猫jacky"},"wechat":{"nickname":"Android王世昌","uid":"oDv1Eww5c17raaESRVI8l9M6zsuE","expires_at":"1468246472191"},"github":{"nickname":"Jacky Wang","uid":"17797018","expires_at":"1468239341180"}}
             * collectedEntriesCount : 3
             * company : 映客
             * followersCount : 916
             * followeesCount : 4
             * role : editor
             * postedPostsCount : 5
             * isAuthor : true
             * postedEntriesCount : 16
             * totalCommentsCount : 184
             * ngxCachedTime : 1504630984
             * ngxCached : true
             * viewedEntriesCount : 1713
             * jobTitle : Android架构师
             * subscribedTagsCount : 42
             * totalCollectionsCount : 4575
             * username : 墨镜猫
             * avatarLarge : https://dn-mhke0kuv.qbox.me/4ERoRptV8ySFHwtZ06bHGY7twEfJ0VJ1cVMU0tml
             * objectId : 562dc7cc60b20fc9817962a2
             */

            var user: UserBean? = null
            var author: String? = null
            var screenshot: String? = null
            var isOriginal: Boolean = false
            var hotIndex: Double = 0.toDouble()
            var content: String? = null
            var title: String? = null
            var lastCommentTime: String? = null
            var type: String? = null
            var isEnglish: Boolean = false
            /**
             * ngxCached : true
             * title : article
             * id : 5562b428e4b00c57d9b94b9d
             * name : 阅读
             * ngxCachedTime : 1504630889
             */

            var category: CategoryBean? = null
            var viewsCount: Int = 0
            var isIsCollected: Boolean = false
            /**
             * ngxCachedTime : 1504630959
             * ngxCached : true
             * title : 开源
             * id : 5597a3cae4b08a686ce5d0fb
             */

            var tags: List<TagsBean>? = null

            class UserBean {
                /**
                 * weibo : {"uid":"5885816355","nickname":"墨镜猫jacky"}
                 * wechat : {"nickname":"Android王世昌","uid":"oDv1Eww5c17raaESRVI8l9M6zsuE","expires_at":"1468246472191"}
                 * github : {"nickname":"Jacky Wang","uid":"17797018","expires_at":"1468239341180"}
                 */

                var community: CommunityBean? = null
                var collectedEntriesCount: Int = 0
                var company: String? = null
                var followersCount: Int = 0
                var followeesCount: Int = 0
                var role: String? = null
                var postedPostsCount: Int = 0
                var isIsAuthor: Boolean = false
                var postedEntriesCount: Int = 0
                var totalCommentsCount: Int = 0
                var ngxCachedTime: Int = 0
                var isNgxCached: Boolean = false
                var viewedEntriesCount: Int = 0
                var jobTitle: String? = null
                var subscribedTagsCount: Int = 0
                var totalCollectionsCount: Int = 0
                var username: String? = null
                var avatarLarge: String? = null
                var objectId: String? = null

                class CommunityBean {
                    /**
                     * uid : 5885816355
                     * nickname : 墨镜猫jacky
                     */

                    var weibo: WeiboBean? = null
                    /**
                     * nickname : Android王世昌
                     * uid : oDv1Eww5c17raaESRVI8l9M6zsuE
                     * expires_at : 1468246472191
                     */

                    var wechat: WechatBean? = null
                    /**
                     * nickname : Jacky Wang
                     * uid : 17797018
                     * expires_at : 1468239341180
                     */

                    var github: GithubBean? = null

                    class WeiboBean {
                        var uid: String? = null
                        var nickname: String? = null
                    }

                    class WechatBean {
                        var nickname: String? = null
                        var uid: String? = null
                        var expires_at: String? = null
                    }

                    class GithubBean {
                        var nickname: String? = null
                        var uid: String? = null
                        var expires_at: String? = null
                    }
                }
            }

            class CategoryBean {
                var isNgxCached: Boolean = false
                var title: String? = null
                var id: String? = null
                var name: String? = null
                var ngxCachedTime: Int = 0
            }

            class TagsBean {
                var ngxCachedTime: Int = 0
                var isNgxCached: Boolean = false
                var title: String? = null
                var id: String? = null
            }
        }
    }
}
