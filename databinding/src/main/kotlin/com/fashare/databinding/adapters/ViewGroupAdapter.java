package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

public final class ViewGroupAdapter {
    @BindingAdapter({"itemView", "viewModels"})
    public static void addViews(ViewGroup viewGroup, final ItemView itemView, final Object[] viewModelList) {
        if (viewModelList != null && viewModelList.length > 0) {
            addViews(viewGroup, itemView, Arrays.asList(viewModelList));
        }
    }

    @BindingAdapter({"itemView", "viewModels"})
    public static void addViews(ViewGroup viewGroup, final ItemView itemView, final List<?> viewModelList) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews();
            for (Object viewModel : viewModelList) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        itemView.layoutRes(), viewGroup, true);
                binding.setVariable(itemView.bindingVariable(), viewModel);
            }
        }
    }
}

