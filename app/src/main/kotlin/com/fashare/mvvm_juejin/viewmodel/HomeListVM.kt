package com.fashare.mvvm_juejin.viewmodel

import com.fashare.databinding.ListVM
import com.fashare.databinding.adapters.annotation.HeaderResHolder
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.view.detail.ArticleActivity

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */

@ResHolder(R.layout.item_home_list)
@HeaderResHolder(R.layout.header_home)
class HomeListVM: ListVM<ArticleBean>() {
    override val onItemClick = ArticleActivity.START

    override val headerData = HeaderVM()

    @ResHolder(R.layout.header_item_home)
    class HeaderVM: ListVM<ArticleBean>() {
        override val onItemClick = ArticleActivity.START
    }
}
