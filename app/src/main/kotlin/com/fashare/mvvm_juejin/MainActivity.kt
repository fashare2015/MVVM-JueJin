package com.fashare.mvvm_juejin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.net.ApiFactory
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiFactory.getApi(JueJinApis:: class.java)
                .getEntryByHotRecomment(
                        "57bd25f4a34131005b211b84",
                        "20",
                        "eyJhY2Nlc3NfdG9rZW4iOiIyeFVONlB4VDF0SWxxTWhMIiwicmVmcmVzaF90b2tlbiI6InFqR0R0Q3h6dGxqYVZUQ2MiLCJ0b2tlbl90eXBlIjoibWFjIiwiZXhwaXJlX2luIjoyNTkyMDAwfQ==",
                        "b9ae8b6a-efe0-4944-b574-b01a3a1303ee",
                        "android")
                .compose(Composers.compose())
                .subscribe({
                    Log.d("aaa", Gson().toJson(it.banner))
                })

    }
}
