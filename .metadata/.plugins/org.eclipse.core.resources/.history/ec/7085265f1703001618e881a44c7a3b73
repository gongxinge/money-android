package com.wb;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WheelActivity extends Activity {

	private ListView listView = null;
	private MyViewGroup myViewGroup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myViewGroup = (MyViewGroup) findViewById(R.id.myviewGroup);

	}

	public void scroll(View view) {

		myViewGroup.beginScroll();

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return 10;
		}

		@Override
		public Object getItem(int position) {

			return null;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			TextView textView = new TextView(WheelActivity.this);
			textView.setText("------" + position + "------");
			LayoutParams layoutparam = new AbsListView.LayoutParams(
					LayoutParams.FILL_PARENT, 100);
			textView.setLayoutParams(layoutparam);
			textView.setGravity(Gravity.CENTER);
			return textView;
		}

	}
}