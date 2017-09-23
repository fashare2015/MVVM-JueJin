package com.fashare.mvvm_juejin.view.notify

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentNotifyBinding
import com.fashare.mvvm_juejin.viewmodel.NotifyListVM

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class NotifyFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentNotifyBinding>(inflater, R.layout.fragment_notify, container, false).apply{
            this.listVM = NotifyListVM()
        }.root
    }
}
