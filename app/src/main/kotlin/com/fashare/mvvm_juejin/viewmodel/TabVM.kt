package com.fashare.mvvm_juejin.viewmodel

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
    var itemList = arrayOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
}
