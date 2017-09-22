package com.fashare.databinding.adapters.annotation

import android.support.annotation.LayoutRes

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderResHolder(@LayoutRes val layout: Int,
                                 val br: Int = ResUtils.NO_BR)
