package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableArrayList
import android.support.v4.app.Fragment
import com.fashare.mvvm_juejin.view.home.HomeListFragment

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : 153614131@qq.com
 *     desc   :
 * </pre>
 */
class HomePagesVM() {
    var tabList = ObservableArrayList<String>().apply {
        this.add("首页")
    }
    var pageList = ObservableArrayList<Fragment>().apply {
        this.add(HomeListFragment())
    }
}
