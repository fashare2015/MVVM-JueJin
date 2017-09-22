package com.fashare.mvvm_juejin.view.profile.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.fashare.base_ui.BaseActivity
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.ActivityRegisterBinding
import com.fashare.mvvm_juejin.viewmodel.LoginVM
import com.fashare.mvvm_juejin.viewmodel.RegisterVM

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register).apply{
            this.registerVM = RegisterVM()
        }
    }
}