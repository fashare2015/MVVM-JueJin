package com.fashare.mvvm_juejin.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.ActivityMainBinding
import com.fashare.mvvm_juejin.viewmodel.TabVM

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply{
            this.viewModel = TabVM()
        }

//        ApiFactory.getApi(JueJinApis:: class.java)
//                .getEntryByHotRecomment(
//                        "57bd25f4a34131005b211b84",
//                        "20",
//                        "eyJhY2Nlc3NfdG9rZW4iOiIyeFVONlB4VDF0SWxxTWhMIiwicmVmcmVzaF90b2tlbiI6InFqR0R0Q3h6dGxqYVZUQ2MiLCJ0b2tlbl90eXBlIjoibWFjIiwiZXhwaXJlX2luIjoyNTkyMDAwfQ==",
//                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
//                        "android")
//                .compose(Composers.compose())
//                .subscribe({
//                    Log.d("aaa", Gson().toJson(it.banner))
//                })

    }
}
