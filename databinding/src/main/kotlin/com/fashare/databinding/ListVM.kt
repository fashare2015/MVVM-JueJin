package com.fashare.databinding

import android.databinding.ObservableArrayList
import com.fashare.adapter.OnItemClickListener

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */

open class ListVM<T>{
    open val data = ObservableArrayList<T>()
    open val onItemClick: OnItemClickListener<T>? = null

    open val headerData: Any? = null
}
