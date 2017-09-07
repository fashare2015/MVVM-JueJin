package com.fashare.mvvm_juejin.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.fashare.base_ui.BaseActivity
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.ActivityMainBinding
import com.fashare.mvvm_juejin.view.explore.ExploreFragment
import com.fashare.mvvm_juejin.view.home.HomeFragment
import com.fashare.mvvm_juejin.view.home.NotifyFragment
import com.fashare.mvvm_juejin.view.home.ProfileFragment
import com.fashare.mvvm_juejin.viewmodel.PagesVM
import com.fashare.mvvm_juejin.viewmodel.TabVM

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply{
            this.pages = PagesVM(listOf(
                    HomeFragment(),
                    ExploreFragment(),
                    NotifyFragment(),
                    ProfileFragment()
            ))

            this.tab = TabVM(null)
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
