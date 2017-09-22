package com.fashare.mvvm_juejin.viewmodel

import android.support.annotation.LayoutRes
import com.fashare.adapter.OnItemClickListener
import com.fashare.adapter.ViewHolder
import com.fashare.databinding.ListVM
import com.fashare.databinding.adapters.annotation.HeaderResHolder
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.BannerListBean
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.view.detail.ArticleActivity

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */

@ResHolder(R.layout.item_explore_list)
@HeaderResHolder(R.layout.header_explore)
class ExploreListVM : ListVM<ArticleBean>() {
    override val onItemClick = object: OnItemClickListener<ArticleBean>(){
        override fun onItemClick(holder: ViewHolder?, data: ArticleBean?, position: Int) {
            holder?.itemView?.context?.apply {
                ArticleActivity.start(this, data)
            }
        }
    }

    override val headerData = HeaderVM()

    class HeaderVM {
        val banner = Banner()
        val entrys = Entrys().apply{
            this.data.addAll(listOf(
                    Entrys.Item(R.drawable.explore_hot, "本周热门"),
                    Entrys.Item(R.drawable.explore_collection_set, "收藏集"),
                    Entrys.Item(R.drawable.explore_offline, "线下活动"),
                    Entrys.Item(R.drawable.explore_post, "专栏")
            ))
        }
        val topics = Topics()

        // banner
        @ResHolder(R.layout.header_item_explore_banner)
        class Banner : ListVM<BannerListBean.Item>(){}

        // 4 个入口
        @ResHolder(R.layout.header_item_explore_entry)
        class Entrys : ListVM<Entrys.Item>(){

            class Item(@LayoutRes val icon: Int, val title: String)
        }

        // 沸点
        @ResHolder(R.layout.header_item_explore_topic)
        class Topics : ListVM<ArticleBean>(){}
    }
}
