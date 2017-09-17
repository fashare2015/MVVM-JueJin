package com.fashare.mvvm_juejin.widget

import android.app.Activity
import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.Log
import com.fashare.mvvm_juejin.R

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-17
 * Time: 14:08
 */
class TitleBar : Toolbar {
    val TAG = javaClass.simpleName
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        if(navigationIcon == null) {
            setNavigationIcon(R.mipmap.g_back_white)
        }
        setNavigationOnClickListener { (context as Activity).finish() }

        if(background == null)
            setBackgroundColor(context.resources.getColor(R.color.colorPrimary, null))

        if(getTitleTextColor() == 0)
            setTitleTextColor(context.resources.getColor(android.R.color.white, null))
    }

    private fun getTitleTextColor(): Int {
        try {
            return Toolbar::class.java.getDeclaredField("mTitleTextColor").apply {
                this.isAccessible = true
            }.get(this) as Int
        }catch (e: NoSuchFieldException){
            Log.e(TAG, e.toString())
            return 0
        }
    }
}