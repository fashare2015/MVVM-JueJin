package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.fashare.adapter.OnItemClickListener
import com.fashare.adapter.ViewHolder
import com.fashare.databinding.ListVM
import com.fashare.databinding.adapters.annotation.ResHolder
import com.fashare.mvvm_juejin.R

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : 153614131@qq.com
 *     desc   :
 * </pre>
 */
@ResHolder(R.layout.item_main_tab)
class TabVM(): ListVM<TabVM.Item>() {
    var curIndex = ObservableInt(0)

    override var onItemClick = object: OnItemClickListener<Item>(){
        override fun onItemClick(holder: ViewHolder?, data: Item?, position: Int) {
            this@TabVM.data.forEach{
                it.isSelected.set(false)
                if(it == data)
                    it.isSelected.set(true)
            }
            curIndex.set(position)
        }
    }

    class Item(var isSelected: ObservableBoolean, var iconResNormal: Int, var iconResSelected: Int)
}
