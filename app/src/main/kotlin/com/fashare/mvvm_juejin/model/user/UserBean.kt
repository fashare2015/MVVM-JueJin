package com.fashare.mvvm_juejin.model.user
/**
 * role : editor
 * username : 梁山boy
 * selfDescription : 擅长面向基佬编程
 * email :
 * mobilePhoneNumber : 18818276018
 * jobTitle : Android
 * company : 上海
 * avatarHd : https://avatars.githubusercontent.com/u/15076541?v=3
 * avatarLarge : https://avatars.githubusercontent.com/u/15076541?v=3
 * blogAddress : http://blog.csdn.net/a153614131
 * deviceType : android
 * editorType : markdown
 * allowNotification : false
 * emailVerified : false
 * mobilePhoneVerified : false
 * isAuthor : true
 * isUnitedAuthor : false
 * blacklist : false
 * followeesCount : 116
 * followersCount : 39
 * postedPostsCount : 5
 * postedEntriesCount : 5
 * collectedEntriesCount : 238
 * viewedEntriesCount : 1832
 * subscribedTagsCount : 18
 * totalCollectionsCount : 1349
 * totalViewsCount : 35326
 * totalCommentsCount : 73
 * latestLoginedInAt : 2017-09-12T10:55:48.674Z
 * createdAt : 2016-08-24T04:43:32.001Z
 * updatedAt : 2017-09-12T12:10:05.860Z
 * collectionSetCount : 28
 * useLeancloudPwd : false
 * objectId : 57bd25f4a34131005b211b84
 * uid : 57bd25f4a34131005b211b84
 */
/**
 * 用户信息
 */
class UserBean {
    var role: String? = null
    var username: String? = null
    var selfDescription: String? = null
    var email: String? = null
    var mobilePhoneNumber: String? = null
    var jobTitle: String? = null
    var company: String? = null
    var avatarHd: String? = null
    var avatarLarge: String? = null
    var blogAddress: String? = null
    var deviceType: String? = null
    var editorType: String? = null
    var allowNotification: Boolean = false
    var emailVerified: Boolean = false
    var mobilePhoneVerified: Boolean = false
    var isAuthor: Boolean = false
    var isUnitedAuthor: Boolean = false
    var blacklist: Boolean = false
    var followeesCount: Int = 0
    var followersCount: Int = 0
    var postedPostsCount: Int = 0
    var postedEntriesCount: Int = 0
    var collectedEntriesCount: Int = 0
    var viewedEntriesCount: Int = 0
    var subscribedTagsCount: Int = 0
    var totalCollectionsCount: Int = 0
    var totalViewsCount: Int = 0
    var totalCommentsCount: Int = 0
    var latestLoginedInAt: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null
    var collectionSetCount: Int = 0
    var useLeancloudPwd: Boolean = false
    var objectId: String? = null
    var uid: String? = null

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

    class TokenBean{
        var token: String? = null
        var user_id: String? = null
        var state: String? = null
    }
}
