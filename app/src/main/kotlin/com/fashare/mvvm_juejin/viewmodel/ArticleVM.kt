package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fashare.databinding.ListVM
import com.fashare.databinding.adapters.annotation.HeaderResHolder
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.model.comment.CommentListBean
import com.fashare.mvvm_juejin.view.detail.ArticleActivity

/**
 * Created by apple on 2017/9/16.
 */

@ResHolder(R.layout.item_article_comment_list)
@HeaderResHolder(R.layout.header_article)
class ArticleVM(val rv: RecyclerView) : ListVM<CommentListBean.Item>() {
    val article = ObservableField<ArticleBean>(ArticleBean("", ""))

    override val headerData = HeaderVM()

    @ResHolder(R.layout.header_item_home)
    class HeaderVM : ListVM<ArticleBean>() {
        val html = ObservableField<String>("")
        val article = ObservableField<ArticleBean>(ArticleBean("", ""))

        override val onItemClick = ArticleActivity.START
    }

    val scrollToComment = View.OnClickListener {
        val header = rv.layoutManager.findViewByPosition(0)
        header?.apply{
            if(this.height > 0)
                rv.smoothScrollBy(0, this.height - rv.computeVerticalScrollOffset())
        }
    }
}
