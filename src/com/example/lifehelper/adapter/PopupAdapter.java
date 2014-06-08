package com.example.lifehelper.adapter;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopupAdapter extends BaseAdapter {
	public static String[] choose = {"����",
			"�Ϻ�",
			"����",
			"����",
			"̫ԭ",
			"�Ϸ�",
			"����",
			"����",
			"����",
			"�Ͼ�",
			"����",
			"����"};
	private Context context;
	
	public PopupAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return choose.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return choose[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.first_pop_item, null);
			
		}
		TextView tv = (TextView) convertView.findViewById(R.id.first_pop_itemTv);
		tv.setText(choose[position]);
		
		return convertView;
	}

}
