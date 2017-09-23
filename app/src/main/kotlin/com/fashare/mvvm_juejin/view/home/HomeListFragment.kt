package com.fashare.mvvm_juejin.view.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.GListBinding
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.viewmodel.HomeListVM
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
class HomeListFragment : BaseFragment(){
    private val IS_CLEAR = true
    lateinit var mListVM: HomeListVM

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<GListBinding>(inflater, R.layout.g_list, container, false).apply {
            mListVM = HomeListVM()
            this.listVM = mListVM
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
                val list: List<ArticleBean> = mListVM.data
                loadArticles(!IS_CLEAR, list[list.size-1].createdAt?: "")
            }
        })
    }

    private fun loadHeaderData(){
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByHotRecomment()
                .compose(Composers.compose())
                .subscribe({
                    sv.onFinishFreshAndLoad()
                    val list = it?.entry?.entrylist?: Collections.emptyList<ArticleBean>()

                    mListVM.headerData.data.apply{
                        this.clear()
                        this.addAll(if(list.size<=3) list else list.subList(0, 3))
                    }
                }, {

                })
    }

    private fun loadArticles(isClear: Boolean, before: String) {
        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByTimeLine(before = before)
                .compose(Composers.compose())
                .subscribe({
                    sv.onFinishFreshAndLoad()
                    val list = it?.entrylist as Iterable<ArticleBean>

                    mListVM.data.apply{
                        if(isClear)
                            this.clear()
                        this.addAll(list)
                    }
                }, {

                })
    }

}
