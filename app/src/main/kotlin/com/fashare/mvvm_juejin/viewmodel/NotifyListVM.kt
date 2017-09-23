package com.fashare.mvvm_juejin.viewmodel

import com.fashare.databinding.TwoWayListVM
import com.fashare.databinding.adapters.annotation.HeaderResHolder
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.notify.NotifyBean
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

@ResHolder(R.layout.item_notify_list)
@HeaderResHolder(R.layout.header_notify)
class NotifyListVM : TwoWayListVM<NotifyBean>() {

    override val loadTask = { it: NotifyBean? ->
        ApiFactory.getApi(JueJinApis.Notify:: class.java)
            .getUserNotification(it?.createdAtString?: "")
            .compose(Composers.compose())
    }

    override val onItemClick = ArticleActivity.START_FROM_NOTIFY

    override val headerData = HeaderVM()
    class HeaderVM
}
