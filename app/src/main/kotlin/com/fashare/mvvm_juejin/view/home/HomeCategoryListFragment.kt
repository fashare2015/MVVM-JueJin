package com.fashare.mvvm_juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentHomeListBinding
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.mvvm_juejin.viewmodel.HomeListVM
import com.fashare.net.ApiFactory
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import kotlinx.android.synthetic.main.fragment_home_list.*
import java.util.*

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class HomeCategoryListFragment(val categoryId: String = "") : BaseFragment(){
    private val IS_CLEAR = true
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
        loadArticles(IS_CLEAR, "")

        sv.header = DefaultHeader(context)
        sv.footer = DefaultFooter(context)
        sv.setListener(object : SpringView.OnFreshListener{
            override fun onRefresh() {
                loadHeaderData()
                loadArticles(IS_CLEAR, "")
            }

            override fun onLoadmore() {
                val list: List<ArticleBean> = binding.listVM.viewModels
                loadArticles(!IS_CLEAR, list[list.size-1].createdAt?: "")
            }
        })
    }

    private fun loadHeaderData(){
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByHotRecomment(
                        "57bd25f4a34131005b211b84",
                        "20",
                        LocalUser.userToken?.token?: "",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
                    sv.onFinishFreshAndLoad()
                    val list = it?.entry?.entrylist?: Collections.emptyList<ArticleBean>()

                    binding.listVM.headerViewModels.get(0).viewModels.apply{
                        this.clear()
                        this.addAll(if(list.size<=3) list else list.subList(0, 3))
                    }
                }, {

                })
    }

    private fun loadArticles(isClear: Boolean, before: String) {
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByTimeLine(categoryId, "",
                        "57bd25f4a34131005b211b84",
                        before,
                        "20",
                        LocalUser.userToken?.token?: "",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
                    sv.onFinishFreshAndLoad()
                    val list = it?.entrylist as Iterable<ArticleBean>

                    binding.listVM.viewModels.apply{
                        if(isClear)
                            this.clear()
                        this.addAll(list)
                    }
                }, {

                })
    }

}
