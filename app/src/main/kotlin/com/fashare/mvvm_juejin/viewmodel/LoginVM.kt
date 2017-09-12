package com.fashare.mvvm_juejin.viewmodel

import android.app.Activity
import android.databinding.ObservableField
import android.view.View
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.net.ApiFactory

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
class LoginVM(){
    var userName = ObservableField<String>("18818276018")
    var passWord = ObservableField<String>("qwe13579")

    val doLogin = View.OnClickListener{ view: View ->
        ApiFactory.getApi(JueJinApis.User::class.java)
                .login(JueJinApis.User.LoginParam().apply{
                    this.user = userName.get()
                    this.psd = passWord.get()
                }.toMap())
                .compose(Composers.compose())
                .subscribe({
                    LocalUser.userToken = it
                    (view.context as Activity).finish()
                }, {})
    }
}
