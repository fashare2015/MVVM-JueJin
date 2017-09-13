package com.fashare.mvvm_juejin.view.explore

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fashare.base_ui.BaseFragment
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.FragmentExploreBinding
import com.fashare.mvvm_juejin.model.BannerListBean
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.repo.local.LocalUser
import com.fashare.mvvm_juejin.viewmodel.ExploreListVM
import com.fashare.net.ApiFactory
import java.util.*

/**
 * <pre>
 * author : jinliangshan
 * e-mail : jinliangshan@chexiang.com
 * desc   :
</pre> *
 */
class ExploreFragment : BaseFragment(){

    lateinit var binding: FragmentExploreBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentExploreBinding>(inflater, R.layout.fragment_explore, container, false).apply{
            binding = this
            this.listVM = ExploreListVM()
        }.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiFactory.getApi(JueJinApis.BannerStorage::class.java)
                .getBanner("explore", 0, 20, "android",
                        LocalUser.userToken?.token?: "",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.banner?: Collections.emptyList<BannerListBean.Item>()

                    binding.listVM.headerViewModels.get(0).viewModels.apply{
                        this.clear()
                        this.addAll(list)
                    }
                }, {})


        ApiFactory.getApi(JueJinApis::class.java)
                .getEntryByTimeLine("all",
                        "vote",
                        "",
                        "",
                        "5",
                        LocalUser.userToken?.token?: "",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.entrylist as Iterable<ArticleBean>

                    binding.listVM.headerViewModels.get(0).topics.apply{
                        this.clear()
                        this.addAll(list)
                    }
                }, {

                })

    }
}
