package com.fashare.databinding.adapters.annotation

import com.fashare.databinding.BR
import me.tatarka.bindingcollectionadapter.ItemView

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : 153614131@qq.com
 *     desc   :
 * </pre>
 */
object ResUtils{
    const val NO_BR = -1

    fun getItemView(res: HeaderResHolder?): ItemView? {
        return res?.let { ItemView.of(
                if (it.br != NO_BR) it.br else BR.item,
                it.layout
        ) }
    }

    fun getItemView(res: ResHolder?): ItemView? {
        return res?.let { ItemView.of(
                if (it.br != NO_BR) it.br else BR.item,
                it.layout
        ) }
    }
}
