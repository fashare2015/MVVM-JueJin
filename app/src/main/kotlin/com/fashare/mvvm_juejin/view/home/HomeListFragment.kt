package com.fashare.mvvm_juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentHomeListBinding
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.model.HotRecomment
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.viewmodel.HomeListVM
import com.fashare.net.ApiFactory
import com.google.gson.Gson
import java.util.*

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class HomeListFragment : BaseFragment(){
    lateinit var binding: FragmentHomeListBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentHomeListBinding>(inflater, R.layout.fragment_home_list, container, false).apply {
            binding = this
            binding.listVM = HomeListVM()
        }.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadHeaderData()
        loadArticles()
    }

    private fun loadHeaderData(){
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByHotRecomment(
                        "57bd25f4a34131005b211b84",
                        "20",
                        "eyJhY2Nlc3NfdG9rZW4iOiIyeFVONlB4VDF0SWxxTWhMIiwicmVmcmVzaF90b2tlbiI6InFqR0R0Q3h6dGxqYVZUQ2MiLCJ0b2tlbl90eXBlIjoibWFjIiwiZXhwaXJlX2luIjoyNTkyMDAwfQ==",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.entry?.entrylist?: Collections.emptyList<ArticleBean>()

                    binding.listVM.headerViewModels.get(0).viewModels.apply{
                        this.clear()
                        this.addAll(if(list.size<=3) list else list.subList(0, 3))
                    }
                })
    }

    private fun loadArticles() {
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByTimeLine(
                        "57bd25f4a34131005b211b84",
                        "",
                        "20",
                        "eyJhY2Nlc3NfdG9rZW4iOiIyeFVONlB4VDF0SWxxTWhMIiwicmVmcmVzaF90b2tlbiI6InFqR0R0Q3h6dGxqYVZUQ2MiLCJ0b2tlbl90eXBlIjoibWFjIiwiZXhwaXJlX2luIjoyNTkyMDAwfQ==",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.entrylist as Iterable<ArticleBean>

                    binding.listVM.viewModels.apply{
                        this.clear()
                        this.addAll(list)
                    }
                })
    }

}
