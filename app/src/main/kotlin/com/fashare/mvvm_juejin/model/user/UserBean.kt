package com.fashare.mvvm_juejin.model.user

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
    var isAuthor: Boolean = false
    var postedEntriesCount: Int = 0
    var totalCommentsCount: Int = 0
    var ngxCachedTime: Int = 0
    var ngxCached: Boolean = false
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