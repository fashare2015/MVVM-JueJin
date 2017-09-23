package com.fashare.mvvm_juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.GTwoWayListBinding
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.viewmodel.HomeListVM
import com.fashare.net.ApiFactory
import java.util.*

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class HomeCategoryListFragment(val categoryId: String = "") : BaseFragment(){
    lateinit var mListVM: HomeListVM

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<GTwoWayListBinding>(inflater, R.layout.g_two_way_list, container, false).apply {
            mListVM = HomeListVM(categoryId)
            this.listVM = mListVM
        }.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadHeaderData()
    }

    private fun loadHeaderData(){
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByHotRecomment()
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.entry?.entrylist?: Collections.emptyList<ArticleBean>()

                    mListVM.headerData.data.apply{
                        this.clear()
                        this.addAll(if(list.size<=3) list else list.subList(0, 3))
                    }
                }, {

                })
    }
}
