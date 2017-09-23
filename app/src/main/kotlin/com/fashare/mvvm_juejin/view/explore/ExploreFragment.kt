package com.fashare.mvvm_juejin.view.explore

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentExploreBinding
import com.fashare.mvvm_juejin.model.BannerListBean
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.viewmodel.ExploreListVM
import com.fashare.net.ApiFactory
import java.util.*

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class ExploreFragment : BaseFragment(){
    lateinit var mListVM: ExploreListVM

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentExploreBinding>(inflater, R.layout.fragment_explore, container, false).apply{
            this.listVM = ExploreListVM()
            mListVM = this.listVM
        }.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadAll()
    }

    fun loadAll(){
        loadHeaderBanner()
        loadHeaderTopic()
    }

    fun loadHeaderBanner(){
        ApiFactory.getApi(JueJinApis.BannerStorage::class.java)
                .getBanner(position = "explore")
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.banner?: Collections.emptyList<BannerListBean.Item>()

                    mListVM.headerData.banner.data.apply{
                        this.clear()
                        this.addAll(list)
                    }
                }, {})
    }

    fun loadHeaderTopic(){
        ApiFactory.getApi(JueJinApis::class.java)
                .getEntryByTimeLine(type = "vote", limit = "5")
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.entrylist as Iterable<ArticleBean>

                    mListVM.headerData.topics.data.apply{
                        this.clear()
                        this.addAll(list)
                    }
                }, {

                })
    }
}
