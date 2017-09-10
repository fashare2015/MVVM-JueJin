package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;
import com.fashare.adapter.viewpager.CommonPagerAdapter;

import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-10
 * Time: 16:15
 */
public class ViewPagerAdapter {
    public static final String TAG = "ViewPagerAdapter - binding ViewPager...: ";

    @BindingAdapter(value = {"itemView", "viewModels", "onItemClick"}, requireAll = false)
    public static void bind(ViewPager container, final ItemView itemView, final List<?> datas, final OnItemClickListener<Object> onItemClickListener) {
        if(!(container.getContext() instanceof FragmentActivity))
            throw new IllegalArgumentException(TAG + "context must instanceof FragmentActivity");

        PagerAdapter adapter;
        if (datas != null && !datas.isEmpty()) {
            adapter = container.getAdapter();
            if(adapter == null) {
                // initialize, adapter is only set once !!!
                container.setAdapter(adapter = new CommonPagerAdapter<Object>(container.getContext(), itemView.layoutRes(), (List<Object>)datas) {
                    @Override
                    protected void convert(ViewHolder holder, Object data, int position) {
                        DataBindingUtil.bind(holder.itemView).setVariable(itemView.bindingVariable(), data);
                    }
                });

                ((CommonPagerAdapter<Object>)adapter).setOnItemClickListener(onItemClickListener);
            }

            adapter.notifyDataSetChanged();
        }
    }
}