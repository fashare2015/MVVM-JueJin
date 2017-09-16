package com.fashare.mvvm_juejin.viewmodel

import android.content.Intent
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.annotation.DrawableRes
import android.view.View
import com.fashare.mvvm_juejin.BR
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.user.UserBean
import com.fashare.mvvm_juejin.view.profile.login.LoginActivity
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
class ProfileVM(){
    // login area
    var user = ObservableField<UserBean>(null)

    val toLogin = View.OnClickListener{
        it.context.startActivity(Intent(it.context, LoginActivity:: class.java))
    }

    fun parseJobAndCompany(user: UserBean?): String{
        return (user?.jobTitle?: "添加职位") + " @ " +
                (user?.company?: "添加公司")
    }

    // list area
    val itemView = ItemView.of(BR.item, R.layout.item_profile_list)
    val viewModels = ObservableArrayList<Item>().apply{
        this.addAll(getDefaultList())
    }

    fun getDefaultList() = listOf(
            Item(R.mipmap.profile_i_like, "我喜欢的", user.get()),
            Item(R.mipmap.profile_collection_set, "收藏集", user.get()),
            Item(R.mipmap.profile_view, "阅读过的文章", user.get()),
            Item(R.mipmap.profile_tag, "标签管理", user.get()),
            Item(R.mipmap.profile_feed_back, "意见反馈", user.get()),
            Item(R.mipmap.profile_settings, "设置", user.get())
    )

    class Item(@DrawableRes val iconRes: Int = 0,
               val name: String = "",
               val user: UserBean? = null){

        fun parseItemCount(user: UserBean?):String{
            when(iconRes){
                R.mipmap.profile_i_like -> return "" + (user?.collectedEntriesCount?: 0) + "篇"
                R.mipmap.profile_collection_set -> return "" + (user?.collectionSetCount?: 0) + "个"
                R.mipmap.profile_view -> return "" + (user?.viewedEntriesCount?: 0) + "篇"
                R.mipmap.profile_tag -> return "" + (user?.subscribedTagsCount?: 0) + "个"
                else -> return ""
            }
        }
    }
}
