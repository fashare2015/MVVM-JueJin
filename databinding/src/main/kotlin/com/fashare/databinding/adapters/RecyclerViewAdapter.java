package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;
import com.fashare.adapter.recyclerview.CommonRvAdapter;
import com.fashare.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.fashare.databinding.BR;
import com.fashare.databinding.ListVM;
import com.fashare.databinding.adapters.annotation.HeaderResHolder;
import com.fashare.databinding.adapters.annotation.ResHolder;
import com.fashare.databinding.adapters.annotation.ResUtils;

import java.util.Arrays;
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

    @BindingConversion
    public static ItemView resToResHolder(@LayoutRes int layoutRes) {
        int defaultBindingVariable = BR.item;
        return ItemView.of(defaultBindingVariable, layoutRes);
    }

//    @BindingAdapter(value = {"itemView", "viewModels", "headerItemViews", "headerViewModels", "onItemClick"}, requireAll = false)
    private static void bind(RecyclerView container, final ItemView item, final List<?> datas,
                            final List<? extends ItemView> header, final List<?> headerDatas,
                            final OnItemClickListener<?> onItemClickListener) {


        RecyclerView.Adapter<?> adapter;
        if (datas != null) {
            // set default LayoutManager.
            if(container.getLayoutManager() == null)
                setLayoutManager(container, LayoutManagers.linear());

            adapter = container.getAdapter();
            if(adapter == null) {

                // initialize, adapter is only set once !!!
                CommonRvAdapter innerAdapter;
                container.setAdapter(adapter = new HeaderAndFooterWrapper<>(innerAdapter = new CommonRvAdapter<Object>(container.getContext(), item.layoutRes(), (List<Object>)datas) {
                    @Override
                    protected void convert(ViewHolder holder, Object data, int position) {
                        DataBindingUtil.bind(holder.itemView).setVariable(item.bindingVariable(), data);
                    }
                }));
                innerAdapter.setOnItemClickListener(onItemClickListener);

                // add headers !!!
                if (header!=null && headerDatas != null) {
                    HeaderAndFooterWrapper headerAdapter = (HeaderAndFooterWrapper) adapter;
                    headerAdapter.removeHeaderViews();
                    for (int i = 0; i < header.size() && i < headerDatas.size(); i++) {
                        ItemView _itemView = header.get(i);
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

//    @BindingAdapter(value = {"itemView", "viewModels", "headerItemViews", "headerViewModels", "onItemClick"}, requireAll = false)
    private static void bind(RecyclerView container, final ItemView item, final List<?> datas,
                            final ItemView header, final Object headerData,
                            final OnItemClickListener<?> onItemClickListener) {
        if(header != null && headerData != null) {
            bind(container, item, datas, Arrays.asList(header), Arrays.asList(headerData), onItemClickListener);
        }else
            bind(container, item, datas, Arrays.<ItemView>asList(), Arrays.asList(), onItemClickListener);
    }

    @BindingAdapter(value = {"vm", "data"}, requireAll = false)
    public static <T> void bind(RecyclerView container, ListVM<T> vm, List<T> datas){
        // <include> 标签有 bug, 会多调用bind(), 若不判空将导致空指针
        if(vm == null){
            return ;
        }

        ItemView item = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(ResHolder.class)),
                header = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(HeaderResHolder.class));

        if(item == null)
            throw new IllegalArgumentException(TAG + "ItemView is null, maybe you forget @ResHolder(R.layout.XXX) in " + vm.getClass().getCanonicalName());

        bind(container, item, datas, header, vm.getHeaderData(), vm.getOnItemClick());
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }
}
