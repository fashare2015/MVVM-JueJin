package com.fashare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class Adapter<T> extends BaseAdapter {
	protected List<T> mList;
	protected LayoutInflater inflater;
	protected Context content;

	public Adapter(List<T> list, Context context) {
		this.mList = list;
		this.content = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
