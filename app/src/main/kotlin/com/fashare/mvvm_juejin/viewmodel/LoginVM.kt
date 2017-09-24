package com.fashare.mvvm_juejin.viewmodel

import android.app.Activity
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.mvvm_juejin.view.profile.login.RegisterActivity
import com.fashare.net.ApiFactory

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
class LoginVM{
    companion object {
        fun getLoginTask(userName: String, passWord: String, loginType: String = "tel") =
                ApiFactory.getApi(JueJinApis.User::class.java)
                        .login(login_type = loginType,
                            user = userName,
                            psd = passWord)
                        .compose(Composers.handleError())
    }

    var userName = ObservableField<String>("18818276018")
    var passWord = ObservableField<String>("qwe13579")

    val doLogin = View.OnClickListener{ view: View ->
        getLoginTask(userName.get(), passWord.get())
                .subscribe({
                    LocalUser.userToken = it
                    (view.context as Activity).finish()
                }, {})
    }

    val toRegister = View.OnClickListener {
        it.context.apply {
            this.startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
