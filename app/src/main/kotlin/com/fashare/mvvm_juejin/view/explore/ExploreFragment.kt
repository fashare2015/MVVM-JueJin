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
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import kotlinx.android.synthetic.main.g_list.*
import java.util.*

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class ExploreFragment : BaseFragment(){
    private val IS_CLEAR = true
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

        sv.header = DefaultHeader(context)
        sv.footer = DefaultFooter(context)
        sv.setListener(object : SpringView.OnFreshListener{
            override fun onRefresh() {
                loadAll()
            }

            override fun onLoadmore() {
                val list: List<ArticleBean> = mListVM.data
                loadHotArticles(!IS_CLEAR, list[list.size-1].rankIndex.toString())
            }
        })
    }

    fun loadAll(){
        loadHeaderBanner()
        loadHeaderTopic()
        loadHotArticles(IS_CLEAR, "")
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

    private fun loadHotArticles(isClear: Boolean, before: String) {
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByRank(before = before)
                .compose(Composers.compose())
                .subscribe({
                    sv.onFinishFreshAndLoad()
                    val list = it?.entrylist as Iterable<ArticleBean>

                    mListVM.data.apply{
                        if(isClear)
                            this.clear()
                        this.addAll(list.filter{ !this.contains(it) })  // 去重
                    }
                }, {

                })
    }
}
