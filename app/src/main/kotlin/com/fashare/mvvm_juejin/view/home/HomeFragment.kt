package com.fashare.mvvm_juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.JueJinApp
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentHomeBinding
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.view.profile.ProfileFragment
import com.fashare.mvvm_juejin.viewmodel.HomePagesVM
import com.fashare.net.ApiFactory

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class HomeFragment : BaseFragment(){
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false).apply {
            binding = this
            this.pages = HomePagesVM()
        }.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiFactory.getApi(JueJinApis.Tags::class.java)
                .getCategories()
                .compose(Composers.compose())
                .subscribe({
                    val list = it.categoryList
                    binding.pages.tabList.apply{
                        this.clear()
                        this.add("首页")
                        this.addAll(list.map{ it.name })
                    }

                    binding.pages.pageList.apply{
                        this.clear()
                        this.add(HomeFragment())
                        this.addAll(list.map{ ProfileFragment() })
                    }
                }, {})

    }

}
