package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;
import com.fashare.adapter.recyclerview.CommonRvAdapter;
import com.fashare.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.LayoutManagers;

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-10
 * Time: 16:15
 */
public class RecyclerViewAdapter {
    public static final String TAG = "RecyclerViewAdapter - binding RecyclerView...: ";

    @BindingAdapter(value = {"itemView", "viewModels", "onItemClick"}, requireAll = false)
    public static void bind(RecyclerView container, final ItemView itemView, final List<?> datas, final OnItemClickListener<Object> onItemClickListener) {
        if(!(container.getContext() instanceof FragmentActivity))
            throw new IllegalArgumentException(TAG + "context must instanceof FragmentActivity");

        RecyclerView.Adapter<?> adapter;
        if (datas != null && !datas.isEmpty()) {
            adapter = container.getAdapter();
            if(adapter == null) {
                // initialize, adapter is only set once !!!
                CommonRvAdapter innerAdapter;
                container.setAdapter(adapter = new HeaderAndFooterWrapper<>(innerAdapter = new CommonRvAdapter<Object>(container.getContext(), itemView.layoutRes(), (List<Object>)datas) {
                    @Override
                    protected void convert(ViewHolder holder, Object data, int position) {
                        DataBindingUtil.bind(holder.itemView).setVariable(itemView.bindingVariable(), data);
                    }
                }));

                innerAdapter.setOnItemClickListener(onItemClickListener);
            }

            adapter.notifyDataSetChanged();
        }
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }
}  