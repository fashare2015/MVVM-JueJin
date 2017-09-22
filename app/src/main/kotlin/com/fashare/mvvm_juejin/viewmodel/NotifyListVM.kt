package com.fashare.mvvm_juejin.viewmodel

import android.widget.Toast
import com.fashare.adapter.OnItemClickListener
import com.fashare.adapter.ViewHolder
import com.fashare.databinding.ListVM
import com.fashare.databinding.adapters.annotation.HeaderResHolder
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.model.notify.NotifyBean

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */

@ResHolder(R.layout.item_notify_list)
@HeaderResHolder(R.layout.header_notify)
class NotifyListVM : ListVM<NotifyBean>() {

    override val onItemClick = object : OnItemClickListener<NotifyBean>() {
        override fun onItemClick(holder: ViewHolder?, data: NotifyBean?, position: Int) {
            Toast.makeText(holder?.itemView?.context, "${position}", Toast.LENGTH_LONG).show()
        }
    }

    override val headerData = HeaderVM()

    class HeaderVM
}
