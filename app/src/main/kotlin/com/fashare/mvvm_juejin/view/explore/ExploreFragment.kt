package com.fashare.mvvm_juejin.view.explore

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentExploreBinding
import com.fashare.mvvm_juejin.viewmodel.ExploreListVM

/**
 * <pre>
 * author : jinliangshan
 * e-mail : 153614131@qq.com
 * desc   :
</pre> *
 */
class ExploreFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentExploreBinding>(inflater, R.layout.fragment_explore, container, false).apply{
            this.listVM = ExploreListVM()
        }.root
    }
}
