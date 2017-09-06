package com.fashare.imageloader

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.manager.RequestManagerRetriever
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Created by jinliangshan on 17/2/13.
 * 图片加载工具类 —— 基于 Glide

 * [使用 ARGB_8888 防止图片过度压缩时变绿](https://github.com/bumptech/glide/issues/305)</a>
  同时需要用自定义压缩器, 防止过度压缩 .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,100))
) */
object ImageLoader {
    val TAG = "ImageLoader"

    /**
     * 异步加载图片
     *
     * @param url 图片路径
     * @param placeholderRes 占位图
     */
    fun loadImage(imageView: ImageView, url: String?, placeholderRes: Int?) {
        loadImage(imageView, url, placeholderRes, null)
    }

    /**
     * 异步加载图片, 带回调
     *
     * @param url 图片路径
     * @param placeholderRes 占位图
     * @param callback 图片加载完成回调
     */
    fun loadImage(imageView: ImageView, url: String?, placeholderRes: Int?, callback: Callback? = null) {
        val context = imageView.context
        if (!isValid(context))
            return

        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(placeholderRes?: 0)
                .error(placeholderRes?: 0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .encoder(BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .into(object : BitmapImageViewTarget(imageView) {
                    /**
                     * [onLoadFailed run some times](https://github.com/bumptech/glide/issues/1764)
                     */
                    internal var hasFailed = false

                    override fun onLoadStarted(placeholder: Drawable?) {
                        super.onLoadStarted(placeholder)
                        callback?.onLoadingStarted(url, imageView)
                    }

                    override fun onResourceReady(bitmap: Bitmap, glideAnimation: GlideAnimation<in Bitmap>?) {
                        super.onResourceReady(bitmap, glideAnimation)
                        callback?.onLoadingComplete(url, imageView, bitmap)
                    }

                    override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                        super.onLoadFailed(e, errorDrawable)
                        if (hasFailed)
                            return
                        hasFailed = true

                        callback?.onLoadingFailed(url, imageView)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        super.onLoadCleared(placeholder)
                        callback?.onLoadingCancelled(url, imageView)
                    }
                })
    }

    /**
     * 异步加载圆角图片, 使用 Glide 内置的圆角裁剪 —— bitmapTransform.

     * 注意: bitmapTransform 与 外在的裁剪不兼容。即: [issues 54](https://github.com/wasabeef/glide-transformations/issues/54)

     * 1. 此时的 imageView 不能是自定义的圆角View.
     * 2. scaleType 也不能设。

     * 否则重复裁剪, 会有 bug.

     * @param url 图片路径
     * @param placeholderRes 占位图
     * @param radiusPixels 圆角像素值
     */
    fun loadRoundImage(imageView: ImageView, url: String?, placeholderRes: Int?, radiusPixels: Int) {
        val context = imageView.context
        if (!isValid(context))
            return

        Glide.with(context)
                .load(url)
                .placeholder(placeholderRes?: 0)
                .error(placeholderRes?: 0)
                .bitmapTransform(
                        CenterCrop(context),
                        RoundedCornersTransformation(context, radiusPixels, 0)
                )
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }

    /**
     * 异步加载图片, 带回调(不涉及 ImageView)
     * @param url 图片路径
     * *
     * @param callback 图片加载完成回调
     */
    fun loadImage(context: Context, url: String, callback: Callback?) {
        if (!isValid(context))
            return

        val imageView: ImageView? = null

        Glide.with(context)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .encoder(BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .into(object : SimpleTarget<Bitmap>() {
                    internal var hasFailed = false

                    override fun onLoadStarted(placeholder: Drawable?) {
                        super.onLoadStarted(placeholder)
                        callback?.onLoadingStarted(url, imageView)
                    }

                    override fun onResourceReady(bitmap: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                        callback?.onLoadingComplete(url, imageView, bitmap)
                    }

                    override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                        super.onLoadFailed(e, errorDrawable)
                        if (hasFailed)
                            return
                        hasFailed = true

                        callback?.onLoadingFailed(url, imageView)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        super.onLoadCleared(placeholder)
                        callback?.onLoadingCancelled(url, imageView)
                    }
                })
    }

    /**
     * reason: [RequestManagerRetriever.assertNotDestroyed]
     * [Issue #138: Getting a crash in Glide 3.3 library](https://github.com/bumptech/glide/issues/138) */
    private fun isValid(context: Context): Boolean {
        if (context is Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && context.isDestroyed)
                return false
        }

        return true
    }

    class Callback {
        /**
         * 下载开始
         * @param url
         * *
         * @param imageView
         */
        fun onLoadingStarted(url: String?, imageView: View?) {
        }

        /**
         * 下载失败
         * @param url
         * *
         * @param imageView
         */
        fun onLoadingFailed(url: String?, imageView: View?) {
        }

        /**
         * 下载成功
         * @param url
         * *
         * @param imageView
         * *
         * @param bitmap
         */
        fun onLoadingComplete(url: String?, imageView: View?, bitmap: Bitmap?) {
        }

        /**
         * 下载取消
         * @param url
         * *
         * @param imageView
         */
        fun onLoadingCancelled(url: String?, imageView: View?) {
        }
    }
}
