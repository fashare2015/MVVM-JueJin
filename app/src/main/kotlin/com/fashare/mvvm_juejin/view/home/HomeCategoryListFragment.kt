package com.fashare.mvvm_juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.GTwoWayListBinding
import com.fashare.mvvm_juejin.viewmodel.HomeListVM

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class HomeCategoryListFragment(val categoryId: String = "") : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<GTwoWayListBinding>(inflater, R.layout.g_two_way_list, container, false).apply {
            this.listVM = HomeListVM(categoryId)
        }.root
    }
}
