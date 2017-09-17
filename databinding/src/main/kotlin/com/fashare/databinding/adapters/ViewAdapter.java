package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.view.View;

public final class ViewAdapter {
    @BindingAdapter(value = {"visibility"})
    public static void setVisibility(View view, boolean isShow) {
        view.setVisibility(isShow? View.VISIBLE: View.GONE);
    }
}

