package com.fashare.mvvm_juejin.viewmodel

import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.mvvm_juejin.view.MainActivity
import com.fashare.net.ApiFactory

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
class RegisterVM {
    var phoneNum = ObservableField<String>("")
    var userName = ObservableField<String>("")
    var passWord = ObservableField<String>("")

    val doRegister = View.OnClickListener{ view: View ->
        ApiFactory.getApi(JueJinApis.User.Storage::class.java)
                .register(phoneNum.get(),
                        userName.get(),
                        passWord.get(),
                        "", "", "android")
                .compose(Composers.handleError())
                .flatMap { LoginVM.getLoginTask(userName.get(), passWord.get(), "email") }
                .subscribe({
                    LocalUser.userToken = it

                    view.context.apply {
                        this.startActivity(Intent(this, MainActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                    }
                }, {})
    }
}