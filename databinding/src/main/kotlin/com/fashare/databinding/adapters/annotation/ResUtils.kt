package com.fashare.databinding.adapters.annotation

import com.fashare.databinding.BR
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
object ResUtils{
    const val NO_BR = -1

    fun getItemView(res: HeaderResHolder): ItemView {
        return ItemView.of(
                if(res.br != NO_BR) res.br else BR.item,
                res.layout
        )
    }

    fun getItemView(res: ResHolder): ItemView {
        return ItemView.of(
                if(res.br != NO_BR) res.br else BR.item,
                res.layout
        )
    }
}
