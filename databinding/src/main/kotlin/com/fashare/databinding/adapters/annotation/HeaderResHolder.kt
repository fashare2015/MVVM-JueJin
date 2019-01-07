package com.fashare.databinding.adapters.annotation

import android.support.annotation.LayoutRes

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : 153614131@qq.com
 *     desc   :
 * </pre>
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderResHolder(@LayoutRes val layout: Int,
                                 val br: Int = ResUtils.NO_BR)
