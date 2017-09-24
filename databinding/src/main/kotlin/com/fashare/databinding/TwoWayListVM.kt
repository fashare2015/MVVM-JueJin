package com.fashare.databinding

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */

open class TwoWayListVM<T>: ListVM<T>(){
    /**
     * lastItem: T          视图列表里的最后一条数据，无则为 null
     * 分两种情况:
     *      onRefresh(): lastItem = null, 直接拉取数据
     *      onLoadMore(): lastItem = 最后一条, 由lastItem作为参数，加载更多
     */
    open val loadTask: ((T?) -> Observable<List<T>>)? = null

    /**
     * originData: List<T>          当前视图列表里的数据
     * refreshable: Refreshable?    触发 loadTask 的下拉刷新控件
     * 分两种情况:
     *      onRefresh(): lastItem = null,       newData = netData
     *      onLoadMore(): lastItem = 最后一条,   netData = newData + originData
     */
    open val loadTaskObserver : ((List<T>, Refreshable?) -> Observer<List<T>>)? = { originData: List<T>, refreshable: Refreshable? ->
        object: Observer<List<T>>{
            override fun onNext(netData: List<T>) {
                val lastItem = if(!originData.isEmpty()) originData[originData.size-1] else null
                val isLoadMore = lastItem != null

                val newData = ArrayList<T>()
                if (isLoadMore) {
                    newData.addAll(originData)
                }
                newData.addAll(netData)

                data.clear()
                data.addAll(newData)
            }

            override fun onComplete() {
                refreshable?.endRefresh()
            }

            override fun onError(e: Throwable) {}

            override fun onSubscribe(d: Disposable) {}
        }
    }

    interface Refreshable{
        fun setOnRefresh(callback: CallBack?)
        fun endRefresh()

        interface CallBack {
            fun onRefresh()
            fun onLoadMore()
        }
    }
}