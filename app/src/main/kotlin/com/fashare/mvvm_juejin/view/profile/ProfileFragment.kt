package com.fashare.mvvm_juejin.view.profile

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentProfileBinding
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.mvvm_juejin.viewmodel.ProfileVM
import com.fashare.net.ApiFactory

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class ProfileFragment : BaseFragment(){
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false).apply {
            binding = this
            this.profileVM = ProfileVM()
        }.root
    }

    override fun onResume() {
        super.onResume()
        LocalUser.userToken?.apply{
            val device_id = "b9ae8b6a-efe0-4944-b574-b01a3a1303ee"
            ApiFactory.getApi(JueJinApis.User.Storage::class.java)
                    .getUserInfo(this.user_id?:"",
                            this.user_id?:"",
                            this.token?:"",
                            device_id,
                            "android")
                    .compose(Composers.compose())
                    .subscribe({
                        binding.profileVM.user.set(it)
                    }, {})
        }
    }
}
