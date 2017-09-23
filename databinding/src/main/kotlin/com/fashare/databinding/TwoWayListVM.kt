package com.fashare.databinding

import io.reactivex.Observable

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */

open class TwoWayListVM<T>: ListVM<T>(){
    /**
     * lastItem: T
     * 分两种情况:
     *      onRefresh(): lastItem = null, 直接拉取数据
     *      onLoadMore(): lastItem = 最后一条, 由lastItem作为参数，加载更多
     */
    open val loadTask: ((T?) -> Observable<List<T>>)? = null

    interface Refreshable{
        fun setOnRefresh(callback: CallBack?)
        fun endRefresh()

        interface CallBack {
            fun onRefresh()
            fun onLoadMore()
        }
    }
}