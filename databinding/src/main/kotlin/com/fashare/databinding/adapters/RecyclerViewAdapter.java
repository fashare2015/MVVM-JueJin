package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

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
    public static final String TAG_SHORT = "RecyclerViewAdapter";
    public static final String TAG = "RecyclerViewAdapter - binding RecyclerView...: ";

    @BindingAdapter(value = {"itemView", "viewModels", "headerItemViews", "headerViewModels", "onItemClick"}, requireAll = false)
    public static void bind(RecyclerView container, final ItemView itemView, final List<?> datas,
                            final List<? extends ItemView> headerItemViews, final List<?> headerDatas,
                            final OnItemClickListener<?> onItemClickListener) {
        if(!(container.getContext() instanceof FragmentActivity))
            throw new IllegalArgumentException(TAG + "context must instanceof FragmentActivity");

        RecyclerView.Adapter<?> adapter;
        if (datas != null) {
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

                // add headers !!!
                if (headerItemViews!=null && headerDatas != null) {
                    HeaderAndFooterWrapper headerAdapter = (HeaderAndFooterWrapper) adapter;
                    headerAdapter.removeHeaderViews();
                    for (int i = 0; i < headerItemViews.size() && i < headerDatas.size(); i++) {
                        ItemView _itemView = headerItemViews.get(i);
                        Object _data = headerDatas.get(i);
                        LayoutInflater inflater = LayoutInflater.from(container.getContext());

                        ViewDataBinding binding = DataBindingUtil.inflate(inflater, _itemView.layoutRes(), container, false);
                        headerAdapter.addHeaderView(binding.getRoot());
                        binding.setVariable(_itemView.bindingVariable(), _data);
                    }
                }
            }

            adapter.notifyDataSetChanged();
        }
    }

//    @BindingAdapter(value = {"headerItemViews", "headerViewModels"}, requireAll = false)
//    public static void bind(RecyclerView container, final List<? extends ItemView> headerItemViews, final List<?> headerDatas) {
//        if(container.getAdapter() == null || !(container.getAdapter() instanceof HeaderAndFooterWrapper)){
//            Log.e(TAG_SHORT, "adapter is null or not instanceof HeaderAndFooterWrapper");
//            return ;
//        }
//        HeaderAndFooterWrapper adapter = (HeaderAndFooterWrapper)container.getAdapter();
//
//        adapter.removeHeaderViews();
//        for(int i=0; i<headerItemViews.size() && i<headerDatas.size(); i++){
//            ItemView itemView = headerItemViews.get(i);
//            Object data = headerDatas.get(i);
//            LayoutInflater inflater = LayoutInflater.from(container.getContext());
//
//            ViewDataBinding binding = DataBindingUtil.inflate(inflater, itemView.layoutRes(), container, false);
//            adapter.addHeaderView(binding.getRoot());
//            binding.setVariable(itemView.bindingVariable(), data);
//        }
//
//        adapter.notifyDataSetChanged();
//    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }
}  