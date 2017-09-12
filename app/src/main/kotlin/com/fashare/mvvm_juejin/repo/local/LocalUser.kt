package com.fashare.mvvm_juejin.repo.local

import com.fashare.mvvm_juejin.JueJinApp
import com.fashare.mvvm_juejin.model.user.UserBean
import com.saike.library.util.PreferencesUtil

/**
 * Created by apple on 2017/9/12.
 */
object LocalUser{
    val appContext by lazy { JueJinApp.instance }

    var user: UserBean? = null
    var userToken: UserBean.TokenBean? = null
            set(value) {
                field = value

                if(field != null) {
                    // 保存 token 到本地
                    PreferencesUtil.getInstance(appContext)
                            .putEntity("userToken", field!!)
                }else{
                    // 清除本地 token
                    PreferencesUtil.getInstance(appContext)
                            .remove("userToken")
                }
            }
            get() {
                return if(field != null) field else
                    PreferencesUtil.getInstance(appContext)
                            .getEntity("userToken", UserBean.TokenBean::class.java)
            }


}
