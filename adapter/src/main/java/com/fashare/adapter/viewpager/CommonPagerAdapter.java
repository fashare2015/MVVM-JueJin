package com.fashare.adapter.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;

import java.util.List;


/**
 * 通用的 PagerAdapter
 * @param <T>
 */
public abstract class CommonPagerAdapter<T> extends PagerAdapter {

    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected List<T> mDataList;
    private OnItemClickListener<T> mOnItemClickListener;
    private int mLayoutId;

    public List<T> getDataList() {
        return mDataList;
    }

    public void setDataList(List<T> dataList) {
        if(dataList != null) {
            this.mDataList = dataList;
            notifyDataSetChanged();
        }else{
            Log.e(TAG, "mDataList is null");
        }
    }

    public void setDataListWithoutNotify(List<T> dataList) {
        if(dataList != null) {
            this.mDataList = dataList;
        }else{
            Log.e(TAG, "mDataList is null");
        }
    }


    /**
     * reutrn POSITION_NONE 使得 notifyDataSetChanged() 会触发 instantiateItem() -> onBindViewHolder()
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CommonPagerAdapter(final Context context, final int layoutId, List<T> dataList) {
        mContext = context;
        mLayoutId = layoutId;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList == null? 0: mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewHolder viewHolder = createViewHolder(container, position);
        onBindViewHolder(viewHolder, position);
        return viewHolder.itemView;
    }

    protected final ViewHolder createViewHolder(ViewGroup container, int position) {
        ViewHolder viewHolder;
        viewHolder = onCreateViewHolder(container);

        if(viewHolder != null) {
            container.addView(viewHolder.itemView);
        }else {
            Log.e(TAG, "viewHolder is null");
        }

        return viewHolder;
    }

    protected ViewHolder onCreateViewHolder(ViewGroup parent){
        return ViewHolder.createViewHolder(mContext, parent, mLayoutId);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setListener(holder, position);

        T data = getDataList().get(position);
        if(data != null) {
            convert(holder, data, position);
        }else{
            Log.e(TAG, String.format("mDataList.get(%d) is null", position));
        }
    }

    protected void setListener(final ViewHolder viewHolder, final int position) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(viewHolder, mDataList.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    return mOnItemClickListener.onItemLongClick(viewHolder, mDataList.get(position), position);
                }
                return false;
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
