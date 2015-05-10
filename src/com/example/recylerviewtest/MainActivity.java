package com.example.recylerviewtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.recylerviewtest.MyAdapter.OnItemClickListener;
import com.example.recylerviewtest.MyAdapter.OnItemLongClickListener;

public class MainActivity extends Activity {

	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

		// 创建一个线性布局管理器
		mLayoutManager = new LinearLayoutManager(this);
		// 默认是Vertical，可以不写
		mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView.setLayoutManager(mLayoutManager);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			list.add("item  " + i);
		}
		final MyAdapter adapter = new MyAdapter(list);
		mRecyclerView.setAdapter(adapter);
		mRecyclerView.addItemDecoration(new ItemDivider(MainActivity.this,
				R.drawable.divider));
		adapter.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onClick(View parent, int position) {
				// TODO Auto-generated method stub
				adapter.insert("Insert", position);
			}
		});
		adapter.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onLongClick(View parent, int position) {
				// TODO Auto-generated method stub
				adapter.remove(position);
				return true;
			}
		});

		mRecyclerView.setOnScrollListener(new OnScrollListener() {
			boolean isShowTop = false;
			boolean isShowBottom = false;

			@Override
			public void onScrolled(int arg0, int arg1) {
				// TODO Auto-generated method stub
				if (mLayoutManager.findLastCompletelyVisibleItemPosition() == 99) {
					if (!isShowTop) {
						Toast.makeText(MainActivity.this, "滑动到底部",
								Toast.LENGTH_SHORT).show();
					}
					isShowTop = true;

				} else {
					isShowTop = false;
				}
				if (mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
					if (!isShowBottom) {
						Toast.makeText(MainActivity.this, "滑动到顶部",
								Toast.LENGTH_SHORT).show();

					}
					isShowBottom = true;
				} else {
					isShowBottom = false;
				}
			}

			@Override
			public void onScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
