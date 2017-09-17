package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.fashare.imageloader.ImageLoader;

public final class ImageViewAdapter {
    @BindingAdapter(value = {"uri", "placeholder", "onImageLoad"}, requireAll = false)
    public static void loadImage(ImageView imageView, String uri, @DrawableRes int placeholder, ImageLoader.Callback callback){
        ImageLoader.INSTANCE.loadImage(imageView, uri, placeholder, callback);
    }

    @BindingAdapter(value = {"uri", "placeholder", "radius"}, requireAll = false)
    public static void loadRoundImage(ImageView imageView, String uri, @DrawableRes int placeholder, float radius){
        ImageLoader.INSTANCE.loadRoundImage(imageView, uri, placeholder, (int)radius);
    }
}

