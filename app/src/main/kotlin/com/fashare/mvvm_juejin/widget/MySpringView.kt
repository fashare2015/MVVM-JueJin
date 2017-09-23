package com.fashare.mvvm_juejin.widget

import android.content.Context
import android.util.AttributeSet

import com.fashare.databinding.TwoWayListVM
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView

/**
 * Created by apple on 2017/9/24.
 */

class MySpringView(context: Context, attrs: AttributeSet) : SpringView(context, attrs), TwoWayListVM.Refreshable {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.header = DefaultHeader(context)
        this.footer = DefaultFooter(context)
    }

    override fun setOnRefresh(callback: TwoWayListVM.Refreshable.CallBack?) {
        this.setListener(object : SpringView.OnFreshListener {
            override fun onRefresh() {
                callback?.onRefresh()
            }

            override fun onLoadmore() {
                callback?.onLoadMore()
            }
        })
    }

    override fun endRefresh() {
        this.onFinishFreshAndLoad()
    }
}
