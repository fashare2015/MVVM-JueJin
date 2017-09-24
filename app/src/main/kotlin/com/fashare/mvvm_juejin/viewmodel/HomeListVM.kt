package com.fashare.mvvm_juejin.viewmodel

import android.text.TextUtils
import com.fashare.databinding.TwoWayListVM
import com.fashare.databinding.adapters.annotation.HeaderResHolder
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.view.detail.ArticleActivity
import com.fashare.net.ApiFactory

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */

@ResHolder(R.layout.item_home_list)
@HeaderResHolder(R.layout.header_home)
class HomeListVM(val categoryId: String = ""): TwoWayListVM<ArticleBean>() {
    override val loadTask = { lastItem: ArticleBean? ->
        ApiFactory.getApi(JueJinApis::class.java)
                .getEntryByTimeLine(categoryId = categoryId, before = lastItem?.createdAt ?: "")
                .compose(Composers.handleError())
                .map { it.entrylist ?: emptyList() }
    }

    override val onItemClick = ArticleActivity.START

    override val headerData = HeaderVM(categoryId)

    @ResHolder(R.layout.header_item_home)
    class HeaderVM(val categoryId: String = ""): TwoWayListVM<ArticleBean>() {
        override val loadTask = { lastItem: ArticleBean? ->
            if(TextUtils.isEmpty(categoryId)) {
                ApiFactory.getApi(JueJinApis::class.java)
                        .getEntryByHotRecomment()
                        .compose(Composers.handleError())
                        .map { it.entry?.entrylist ?: emptyList() }
                        .map { if (it.size > 3) it.subList(0, 3) else it }
            }else{
                ApiFactory.getApi(JueJinApis::class.java)
                        .getEntryByPeriod(categoryId = categoryId)
                        .compose(Composers.handleError())
                        .map { it.entrylist ?: emptyList() }
                        .map { if (it.size > 3) it.subList(0, 3) else it }
            }
        }

        override val onItemClick = ArticleActivity.START
    }
}
