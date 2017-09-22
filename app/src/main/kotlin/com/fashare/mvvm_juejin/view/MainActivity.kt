package com.fashare.mvvm_juejin.view

import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.os.Bundle
import com.fashare.base_ui.BaseActivity
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.ActivityMainBinding
import com.fashare.mvvm_juejin.view.explore.ExploreFragment
import com.fashare.mvvm_juejin.view.home.HomeFragment
import com.fashare.mvvm_juejin.view.notify.NotifyFragment
import com.fashare.mvvm_juejin.view.profile.ProfileFragment
import com.fashare.mvvm_juejin.viewmodel.TabPagesVM
import com.fashare.mvvm_juejin.viewmodel.TabVM

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply{
            this.pages = TabPagesVM(listOf(
                    HomeFragment(),
                    ExploreFragment(),
                    NotifyFragment(),
                    ProfileFragment()
            ))

            this.tab = TabVM().apply{
                this.data.addAll(listOf(
                    TabVM.Item(ObservableBoolean(true), R.drawable.tab_home_normal, R.drawable.tab_home),
                    TabVM.Item(ObservableBoolean(false), R.drawable.tab_explore_normal, R.drawable.tab_explore),
                    TabVM.Item(ObservableBoolean(false), R.drawable.tab_notifications_normal, R.drawable.tab_notifications),
                    TabVM.Item(ObservableBoolean(false), R.drawable.tab_profile_normal, R.drawable.tab_profile)
                ))
            }
        }
    }
}
