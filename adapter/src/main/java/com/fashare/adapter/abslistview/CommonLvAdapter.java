package com.fashare.adapter.abslistview;

import android.content.Context;

import com.fashare.adapter.ViewHolder;
import com.fashare.adapter.abslistview.base.ItemViewDelegate;

import java.util.List;

public abstract class CommonLvAdapter<T> extends MultiItemTypeAdapter<T>
{

    public CommonLvAdapter(Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                CommonLvAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
