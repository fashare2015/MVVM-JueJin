package com.fashare.adapter.abslistview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fashare.adapter.OnItemClickListener;
import com.fashare.adapter.ViewHolder;
import com.fashare.adapter.abslistview.base.ItemViewDelegate;
import com.fashare.adapter.abslistview.base.ItemViewDelegateManager;

import java.util.List;

public class MultiItemTypeAdapter<T> extends BaseAdapter {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected List<T> mDatas;

    private ItemViewDelegateManager mItemViewDelegateManager;
    private OnItemClickListener mOnItemClickListener;

    public List<T> getDataList() {
        return mDatas;
    }

    public void setDataList(List<T> dataList) {
        if(dataList != null) {
            this.mDatas = dataList;
            notifyDataSetChanged();
        }else{
            Log.e(TAG, "mDataList is null");
        }
    }

    public MultiItemTypeAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager())
            return mItemViewDelegateManager.getItemViewDelegateCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            int viewType = mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(mDatas.get(position), position);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder viewHolder = null ;
        if (convertView == null)
        {
            viewHolder = ViewHolder.createViewHolder(mContext, parent, layoutId);
            convertView = viewHolder.getConvertView();
            onViewHolderCreated(viewHolder, convertView);

            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setListener(viewHolder, getItemViewType(position), position);
        convert(viewHolder, getItem(position), position);
        return convertView;
    }

    protected void convert(ViewHolder viewHolder, T item, int position) {
        mItemViewDelegateManager.convert(viewHolder, item, position);
    }

    public void onViewHolderCreated(ViewHolder holder , View itemView )
    {}

    protected void setListener(final ViewHolder viewHolder, int viewType, final int position) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(viewHolder, mDatas.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    return mOnItemClickListener.onItemLongClick(viewHolder, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
