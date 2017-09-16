package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;

import java.util.Arrays;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

public final class ViewGroupAdapter {
    @BindingAdapter(value = {"itemView", "viewModels", "onItemClick"}, requireAll = false)
    public static void addViews(ViewGroup viewGroup, ItemView itemView, Object[] viewModelList, OnItemClickListener<?> onItemClickListener) {
        if (viewModelList != null && viewModelList.length > 0) {
            addViews(viewGroup, itemView, Arrays.asList(viewModelList), onItemClickListener);
        }
    }

    @BindingAdapter(value = {"itemView", "viewModels", "onItemClick"}, requireAll = false)
    public static void addViews(ViewGroup viewGroup, ItemView itemView, List<?> viewModelList, final OnItemClickListener<?> onItemClickListener) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews();
            for (int pos=0; pos<viewModelList.size(); pos++) {
                final Object viewModel = viewModelList.get(pos);
                final ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        itemView.layoutRes(), viewGroup, true);
                binding.setVariable(itemView.bindingVariable(), viewModel);

                setListener(onItemClickListener, ViewHolder.createViewHolder(viewGroup.getContext(), binding.getRoot()), viewModel, pos);
            }
        }
    }

    private static void setListener(final OnItemClickListener onItemClickListener, final ViewHolder viewHolder, final Object data, final int pos) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(viewHolder, data, pos);
                }
            }
        });
    }
}

