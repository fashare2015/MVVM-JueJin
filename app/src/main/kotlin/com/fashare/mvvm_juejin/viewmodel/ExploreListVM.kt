package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableArrayList
import android.support.annotation.LayoutRes
import com.fashare.mvvm_juejin.BR
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.BannerListBean
import com.fashare.mvvm_juejin.model.article.ArticleBean
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class ExploreListVM{
    val itemView = ItemView.of(BR.item, R.layout.item_explore_list)
    val viewModels = ObservableArrayList<ArticleBean>()

    val headerItemViews = listOf<ItemView>(
            ItemView.of(BR.headerVM, R.layout.header_explore)
    )
    val headerViewModels = listOf(HeaderVM())

    class HeaderVM {
        // banner
        val itemView = ItemView.of(BR.item, R.layout.header_item_explore_banner)
        val viewModels = ObservableArrayList<BannerListBean.Item>()

        // 4 个入口
        val itemViewEntry = ItemView.of(BR.item, R.layout.header_item_explore_entry)
        val entrys = ObservableArrayList<Entry>().apply{
            this.addAll(listOf(
                    Entry(R.mipmap.explore_hot, "本周热门"),
                    Entry(R.mipmap.explore_collection_set, "收藏集"),
                    Entry(R.mipmap.explore_offline, "线下活动"),
                    Entry(R.mipmap.explore_post, "专栏")
            ))
        }

        class Entry(@LayoutRes val icon: Int, val title: String)

        // 沸点
        val itemViewTopic = ItemView.of(BR.item, R.layout.header_item_explore_topic)
        val topics = ObservableArrayList<ArticleBean>()
    }
}
