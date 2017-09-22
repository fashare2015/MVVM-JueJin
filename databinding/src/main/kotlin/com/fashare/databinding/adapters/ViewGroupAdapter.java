package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;
import com.fashare.databinding.ListVM;
import com.fashare.databinding.adapters.annotation.ResHolder;
import com.fashare.databinding.adapters.annotation.ResUtils;

import java.util.Arrays;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

public final class ViewGroupAdapter {
    public static final String TAG = "ViewGroupAdapter - binding ViewGroup...: ";

    @BindingAdapter(value = {"itemView", "viewModels", "onItemClick"}, requireAll = false)
    public static void bind(ViewGroup viewGroup, ItemView itemView, Object[] viewModelList, OnItemClickListener<?> onItemClickListener) {
        if (viewModelList != null && viewModelList.length > 0) {
            bind(viewGroup, itemView, Arrays.asList(viewModelList), onItemClickListener);
        }
    }

    @BindingAdapter(value = {"itemView", "viewModels", "onItemClick"}, requireAll = false)
    public static void bind(ViewGroup viewGroup, ItemView itemView, List<?> viewModelList, final OnItemClickListener<?> onItemClickListener) {
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

    @BindingAdapter(value = {"vm", "data"})
    public static <T> void bind(ViewGroup viewGroup, ListVM<T> vm, List<T> datas) {
        if(vm == null)
            return ;

        ItemView item = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(ResHolder.class));

        if(item == null)
            throw new IllegalArgumentException(TAG + "ItemView is null, maybe you forget @ResHolder(R.layout.XXX) in " + vm.getClass().getCanonicalName());

        bind(viewGroup, item, datas.toArray(), vm.getOnItemClick());
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

