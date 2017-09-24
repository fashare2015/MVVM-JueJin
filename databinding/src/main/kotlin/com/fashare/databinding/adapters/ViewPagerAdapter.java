package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;
import com.fashare.adapter.viewpager.CommonPagerAdapter;
import com.fashare.databinding.ListVM;
import com.fashare.databinding.R;
import com.fashare.databinding.TwoWayListVM;
import com.fashare.databinding.adapters.annotation.ResHolder;
import com.fashare.databinding.adapters.annotation.ResUtils;

import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * User: fashare(153614131@qq.com)
 * Date: 2017-09-10
 * Time: 16:15
 */
public class ViewPagerAdapter {
    public static final String TAG = "ViewPagerAdapter - binding ViewPager...: ";

    @BindingAdapter({"offscreenPageLimit"})
    public static void bind(ViewPager container, final int offscreenPageLimit) {
        container.setOffscreenPageLimit(offscreenPageLimit);
    }

//    @BindingAdapter(value = {"itemView", "viewModels", "onItemClick"}, requireAll = false)
    private static void bind(ViewPager container, final ItemView itemView, final List<?> datas, final OnItemClickListener<?> onItemClickListener) {
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

                ((CommonPagerAdapter<Object>)adapter).setOnItemClickListener((OnItemClickListener<Object>)onItemClickListener);
            }

            adapter.notifyDataSetChanged();
        }
    }

//    @BindingAdapter(value = {"vm", "data"})
    private static <T> void bind(ViewPager container, ListVM<T> vm, List<T> datas) {
        if(vm == null)
            return ;

        ItemView item = ResUtils.INSTANCE.getItemView(vm.getClass().getAnnotation(ResHolder.class));

        if(item == null)
            throw new IllegalArgumentException(TAG + "ItemView is null, maybe you forget @ResHolder(R.layout.XXX) in " + vm.getClass().getCanonicalName());

        bind(container, item, datas, vm.getOnItemClick());
    }

    /**
     * (伪)双向 databinding: 同 {@link RecyclerViewAdapter#setDataTwoWay(RecyclerView, ListVM, List)}
     *
     * @param container
     * @param vm
     * @param datas
     * @param <T>
     */
    @BindingAdapter({"vm", "data"})
    public static <T> void setDataTwoWay(final ViewPager container, final ListVM<T> vm, List<T> datas){
        if(vm == null){
            return ;
        }
        bind(container, vm, datas);

        if(vm instanceof TwoWayListVM) {
            boolean isInited = container.getTag(R.id.db_inited) != null;
            if (!isInited) {
                container.setTag(R.id.db_inited, true);
                loadData(container, (TwoWayListVM<T>) vm, null, null);
            }
        }
    }

    public static <T> void loadData(final ViewPager container, final TwoWayListVM<T> vm, T lastItem, final TwoWayListVM.Refreshable refreshable){
        if(vm.getLoadTask() != null && vm.getLoadTaskObserver() != null){
            vm.getLoadTask().invoke(lastItem)
                    .subscribe(vm.getLoadTaskObserver().invoke(vm.getData(), refreshable));
        }
    }
}
