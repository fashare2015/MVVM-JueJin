package com.fashare.databinding

import android.databinding.ObservableArrayList
import com.fashare.adapter.OnItemClickListener

/**
 * <pre>
 * author : jinliangshan
 * e-mail : 153614131@qq.com
 * desc   :
</pre> *
 */

open class ListVM<T>{
    open var data = ObservableArrayList<T>()
            set(value) {
                field.clear()
                field.addAll(value)
            }
    open val onItemClick: OnItemClickListener<T>? = null

    open val headerData: Any? = null
}
