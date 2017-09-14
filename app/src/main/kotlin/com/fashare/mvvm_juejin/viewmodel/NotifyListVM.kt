package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableArrayList
import com.fashare.mvvm_juejin.BR
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.notify.NotifyBean
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class NotifyListVM{
    val itemView = ItemView.of(BR.item, R.layout.item_notify_list)
    val viewModels = ObservableArrayList<NotifyBean>()

    val headerItemViews = listOf<ItemView>(
            ItemView.of(BR.headerVM, R.layout.header_notify)
    )
    val headerViewModels = listOf(HeaderVM())

    class HeaderVM {

    }

    companion object{

    }
}
