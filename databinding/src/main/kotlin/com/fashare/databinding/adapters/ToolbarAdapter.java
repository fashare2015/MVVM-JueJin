package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;

public final class ToolbarAdapter {
    @BindingAdapter(value = {"title"}, requireAll = false)
    public static void bind(Toolbar toolbar, String title){
        toolbar.setTitle(title);
    }
}

