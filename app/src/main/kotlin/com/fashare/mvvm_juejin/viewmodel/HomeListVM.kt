package com.fashare.mvvm_juejin.viewmodel

import android.widget.Toast
import com.fashare.adapter.OnItemClickListener
import com.fashare.adapter.ViewHolder
import com.fashare.databinding.ListVM
import com.fashare.databinding.adapters.annotation.HeaderResHolder
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.article.ArticleBean

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

    override val onItemClick = object : OnItemClickListener<ArticleBean>() {
        override fun onItemClick(holder: ViewHolder?, data: ArticleBean?, position: Int) {
            Toast.makeText(holder?.itemView?.context, "${position}", Toast.LENGTH_LONG).show()
        }
    }

    override val headerData = HeaderVM()

    @ResHolder(R.layout.header_item_home)
    class HeaderVM: ListVM<ArticleBean>() {
//        val itemView = ItemView.of(BR.item, R.layout.header_item_home)
//        val viewModels = ObservableArrayList<ArticleBean>()

        override val onItemClick = object : OnItemClickListener<ArticleBean>() {
            override fun onItemClick(holder: ViewHolder?, data: ArticleBean?, position: Int) {
                Toast.makeText(holder?.itemView?.context, "${position}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
