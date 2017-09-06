package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public final class ImageViewAdapter {

    @BindingAdapter({"src"})
    public static void setImageRes(ImageView view, @DrawableRes int imgRes) {
        view.setImageResource(imgRes);
    }
}

