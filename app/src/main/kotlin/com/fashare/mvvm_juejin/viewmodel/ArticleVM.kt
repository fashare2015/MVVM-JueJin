package com.fashare.mvvm_juejin.viewmodel

import android.databinding.ObservableField

/**
 * Created by apple on 2017/9/16.
 */

class ArticleVM{
    val url = ObservableField<String>("")

    val html = ObservableField<String>("")
    val cssPath = "file:///android_asset/article.css"
//    val cssPath = ""
}