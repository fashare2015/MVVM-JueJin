package com.fashare.mvvm_juejin.view.notify

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentNotifyBinding
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.mvvm_juejin.viewmodel.NotifyListVM
import com.fashare.net.ApiFactory

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class NotifyFragment : BaseFragment(){
    lateinit var binding: FragmentNotifyBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentNotifyBinding>(inflater, R.layout.fragment_notify, container, false).apply{
            binding = this
            this.listVM = NotifyListVM()
        }.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiFactory.getApi(JueJinApis.Notify:: class.java)
                .getUserNotification(LocalUser.userToken?.user_id?:"",
                        ""/* before */,
                        "30",
                        LocalUser.userToken?.token?: "",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
//                    sv.onFinishFreshAndLoad()
                    val list = it

                    binding.listVM.data.apply{
//                        if(isClear)
                            this.clear()
                        this.addAll(list)
                    }
                }, {

                })
    }
}
