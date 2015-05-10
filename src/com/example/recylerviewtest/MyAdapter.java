package com.example.recylerviewtest;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

	private List<String> dataList;
	private OnItemClickListener onItemClickListener;
	private OnItemLongClickListener onItemLongClickListener;

	public MyAdapter(List<String> list) {
		this.dataList = list;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		// TODO Auto-generated method stub
		viewHolder.textView.setText(dataList.get(position));
		viewHolder.itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (onItemClickListener != null) {
					onItemClickListener.onClick(v, position);
				}
			}
		});
		viewHolder.itemView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				if (onItemLongClickListener != null) {
					return onItemLongClickListener.onLongClick(v, position);
				}
				return false;
			}
		});
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.item, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}
	
	public void insert(String data, int position){
		dataList.add(position, data);
		notifyItemInserted(position);
	}
	
	public void remove(int position){
		dataList.remove(position);
		notifyItemRemoved(position);
	}

	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public OnItemLongClickListener getOnItemLongClickListener() {
		return onItemLongClickListener;
	}

	public void setOnItemLongClickListener(
			OnItemLongClickListener onItemLongClickListener) {
		this.onItemLongClickListener = onItemLongClickListener;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public TextView textView;
		public View itemView;

		public ViewHolder(View view) {
			super(view);
			// TODO Auto-generated constructor stub
			itemView = view;
			textView = (TextView) view.findViewById(R.id.item_text);
		}

	}

	public interface OnItemClickListener {
		public void onClick(View parent, int position);
	}

	public interface OnItemLongClickListener {
		public boolean onLongClick(View parent, int position);
	}

}
