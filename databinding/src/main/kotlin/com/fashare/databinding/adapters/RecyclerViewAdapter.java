package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewParent;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;
import com.fashare.adapter.recyclerview.CommonRvAdapter;
import com.fashare.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.fashare.databinding.BR;
import com.fashare.databinding.ListVM;
import com.fashare.databinding.R;
import com.fashare.databinding.TwoWayListVM;
import com.fashare.databinding.adapters.annotation.HeaderResHolder;
import com.fashare.databinding.adapters.annotation.ResHolder;
import com.fashare.databinding.adapters.annotation.ResUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
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

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    /**
     * 单向 databinding:
     *      需要手动触发 {@link ListVM#setData(ObservableArrayList)}
     *      然后自动更新 RecyclerView
     *
     * @param container
     * @param vm
     * @param datas
     * @param <T>
     */
    @BindingAdapter(value = {"vm", "data"}, requireAll = false)
    public static <T> void setData(RecyclerView container, ListVM<T> vm, List<T> datas){
        // <include> 标签有 bug, 会多调用bind(), 若不判空将导致空指针
        if(vm == null){
            return ;
        }

        ItemView item = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(ResHolder.class)),
                header = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(HeaderResHolder.class));

        if(item == null)
            throw new IllegalArgumentException(TAG + "ItemView is null, maybe you forget @ResHolder(R.layout.XXX) in " + vm.getClass().getCanonicalName());

        container.setTag(R.id.db_vm, vm);

        bind(container, item, datas, header, vm.getHeaderData(), vm.getOnItemClick());
    }

    /**
     * 双向 databinding: 自动调用 {@link TwoWayListVM#getLoadTask()},
     *      并自动触发 {@link TwoWayListVM#setData(ObservableArrayList)}
     *      然后自动更新 RecyclerView
     *
     * @param container
     * @param vm
     * @param datas
     * @param listChanged
     * @param <T>
     */
    @BindingAdapter(value = {"vm", "data", "listChanged"}, requireAll = false)
    public static <T> void setData(final RecyclerView container, final TwoWayListVM<T> vm, List<T> datas, final InverseBindingListener listChanged){
        // <include> 标签有 bug, 会多调用bind(), 若不判空将导致空指针
        if(vm == null){
            return ;
        }

        ItemView item = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(ResHolder.class)),
                header = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(HeaderResHolder.class));

        if(item == null)
            throw new IllegalArgumentException(TAG + "ItemView is null, maybe you forget @ResHolder(R.layout.XXX) in " + vm.getClass().getCanonicalName());

        bind(container, item, datas, header, vm.getHeaderData(), vm.getOnItemClick());

        /**
         * 类似 {@link TextViewBindingAdapter#haveContentsChanged(CharSequence, CharSequence)},
         * 若数据未变则不 loadData(), 防止死循环
         */
        Object oldData = container.getTag(R.id.db_data);
        if(!equals(oldData, datas)) {
            loadData(container, vm, listChanged, null, null);

            // 若 parent 可下拉刷新，设置回调
            ViewParent parent = container.getParent();
            if(parent != null && parent instanceof TwoWayListVM.Refreshable){
                final TwoWayListVM.Refreshable refreshable = (TwoWayListVM.Refreshable) parent;
                ((TwoWayListVM.Refreshable) parent).setOnRefresh(new TwoWayListVM.Refreshable.CallBack() {
                    @Override
                    public void onRefresh() {
                        loadData(container, vm, listChanged, null, refreshable);
                    }

                    @Override
                    public void onLoadMore() {
                        List<T> data = getOriginData(container);
                        if(data.size()-1 >= 0) {
                            loadData(container, vm, listChanged, data.get(data.size()-1), refreshable);
                        }
                    }
                });
            }
        }
    }

    private static <T> boolean equals(Object _oldData, List<T> newData) {
        if(_oldData != null && newData != null && _oldData instanceof List){
            List<?> oldData = ((List) _oldData);
            if(oldData.size() != newData.size())
                return false;
            for(int i=0; i<oldData.size(); i++)
                if(oldData.get(i) != newData.get(i))    // 仅判断引用相等
                    return false;
            return true;
        }
        return false;
    }

    @InverseBindingAdapter(attribute = "data", event = "listChanged")
    public static ObservableArrayList<?> getData(RecyclerView container) {
        ObservableArrayList data = new ObservableArrayList<>();
        Object _newData = container.getTag(R.id.db_data);
        List<?> newData = (_newData != null && _newData instanceof List)? ((List<?>) _newData): Collections.emptyList();

        data.addAll(newData);
        return data;
    }

//    @BindingAdapter(value = {"listChanged"}, requireAll = false)
    public static <T> void loadData(final RecyclerView container, TwoWayListVM<T> vm, final InverseBindingListener listChanged, T lastItem, final TwoWayListVM.Refreshable refreshable){
        if(vm.getLoadTask() != null){
            final boolean isLoadMore = lastItem != null;

            vm.getLoadTask()
                    .invoke(lastItem)
                    .subscribe(new Consumer<List<T>>() {
                        @Override
                        public void accept(@NonNull List<T> objects) throws Exception {
                            List<T> data = new ArrayList<>();
                            if (isLoadMore) {
                                data.addAll((List<T>)getOriginData(container));
                            }
                            data.addAll(objects);
                            container.setTag(R.id.db_data, data);
                            if (listChanged != null)
                                listChanged.onChange();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {

                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            if(refreshable != null)
                                refreshable.endRefresh();
                        }
                    });
        }
    }

    public static <T> List<T> getOriginData(RecyclerView container) {
        RecyclerView.Adapter adapter = container.getAdapter();
        List<T> originData = Collections.emptyList();
        if(adapter != null) {
            if(adapter instanceof HeaderAndFooterWrapper)
                adapter = ((HeaderAndFooterWrapper) adapter).getInnerAdapter();

            if(adapter instanceof CommonRvAdapter)
                originData = ((CommonRvAdapter<T>) adapter).getDataList();
        }

        return originData;
    }
}
