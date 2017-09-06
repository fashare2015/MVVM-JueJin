package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableBoolean
import com.fashare.adapter.OnItemClickListener
import com.fashare.adapter.ViewHolder
import com.fashare.mvvm_juejin.BR
import com.fashare.mvvm_juejin.R
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
class TabVM{
//    var img = "https://img.mp.itc.cn/upload/20160506/0afc31ee80dd444687b2b52fb21ec5e3_th.png"
    var itemView = ItemView.of(BR.viewModel, R.layout.item_main_tab)
    var viewModels = arrayOf(
            Item(ObservableBoolean(true), R.mipmap.tab_home_normal, R.mipmap.tab_home),
            Item(ObservableBoolean(false), R.mipmap.tab_explore_normal, R.mipmap.tab_explore),
            Item(ObservableBoolean(false), R.mipmap.tab_notifications_normal, R.mipmap.tab_notifications),
            Item(ObservableBoolean(false), R.mipmap.tab_profile_normal, R.mipmap.tab_profile)
    )

    var onItemClick = object: OnItemClickListener<Item>(){
        override fun onItemClick(holder: ViewHolder?, data: Item?, position: Int) {
            viewModels.forEach{
                it.isSelected.set(false)
                if(it == data)
                    it.isSelected.set(true)
            }
        }
    }

    class Item(var isSelected: ObservableBoolean, var iconResNormal: Int, var iconResSelected: Int)
}
