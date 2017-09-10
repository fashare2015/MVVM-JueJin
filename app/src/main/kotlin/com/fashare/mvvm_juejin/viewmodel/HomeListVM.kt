package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableArrayList
import com.fashare.mvvm_juejin.BR
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.article.ArticleBean
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class HomeListVM{
    val itemView = ItemView.of(BR.item, R.layout.item_home_list)
    val viewModels = ObservableArrayList<ArticleBean>()

    val headerItemViews = listOf<ItemView>(
            ItemView.of(BR.headerVM, R.layout.header_home)
    )
    val headerViewModels = listOf(HeaderVM())

    class HeaderVM {
        val itemView = ItemView.of(BR.item, R.layout.header_item_home)
        val viewModels = ObservableArrayList<ArticleBean>()
    }
}
