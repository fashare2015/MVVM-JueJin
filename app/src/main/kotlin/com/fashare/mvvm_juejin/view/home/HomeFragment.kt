package com.fashare.mvvm_juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentHomeBinding
import com.fashare.mvvm_juejin.viewmodel.HomePagesVM

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class HomeFragment : BaseFragment(){
//    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false).apply {
//            binding = this
            this.pages = HomePagesVM(listOf(
                    HomeListFragment(),
                    ProfileFragment()
            ))
        }.root
    }

}
