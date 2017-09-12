package com.fashare.mvvm_juejin.viewmodel

import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import com.fashare.mvvm_juejin.model.user.UserBean
import com.fashare.mvvm_juejin.view.profile.login.LoginActivity

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
class ProfileVM(){
    var user = ObservableField<UserBean>(null)

    val toLogin = View.OnClickListener{
        it.context.startActivity(Intent(it.context, LoginActivity:: class.java))
    }

    fun parseJobAndCompany(user: UserBean?): String{
        return (user?.jobTitle?: "添加职位") + " @ " +
                (user?.company?: "添加公司")
    }
}
