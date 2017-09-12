package com.fashare.mvvm_juejin.view.profile.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.fashare.base_ui.BaseActivity
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.ActivityLoginBinding
import com.fashare.mvvm_juejin.viewmodel.LoginVM

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply{
            this.loginVM = LoginVM()
        }
    }
}
