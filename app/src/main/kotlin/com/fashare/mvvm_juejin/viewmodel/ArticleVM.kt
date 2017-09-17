package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fashare.mvvm_juejin.BR
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.model.comment.CommentListBean
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * Created by apple on 2017/9/16.
 */

class ArticleVM(val rv: RecyclerView){
    val article = ObservableField<ArticleBean>(ArticleBean("", ""))

    val itemView = ItemView.of(BR.item, R.layout.item_article_comment_list)
    val viewModels = ObservableArrayList<CommentListBean.Item>()

    val headerItemViews = listOf<ItemView>(
            ItemView.of(BR.headerVM, R.layout.header_article)
    )
    val headerViewModels = listOf(HeaderVM())

    class HeaderVM {
        val html = ObservableField<String>("")
        val article = ObservableField<ArticleBean>(ArticleBean("", ""))

        val itemView = ItemView.of(BR.item, R.layout.header_item_home)
        val viewModels = ObservableArrayList<ArticleBean>()
    }

    val scrollToComment = View.OnClickListener {
        val header = rv.layoutManager.findViewByPosition(0)
        header?.apply{
            rv.smoothScrollBy(0, this.height - rv.computeVerticalScrollOffset())
        }
    }
}