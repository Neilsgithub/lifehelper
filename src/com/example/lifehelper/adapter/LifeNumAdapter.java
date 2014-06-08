package com.example.lifehelper.adapter;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LifeNumAdapter extends BaseAdapter {
	
	String name[] = new String[]{"�����绰",
			"��",
			"��������",
			"EMS�й�����ҵ����ѯ",
			"��ʱ����",
			"�����ǹ�����",
			"�������⳵�г��绰",
			"���й�������",
			"��Ա��ѯ�绰",
			"�绰������Ϣ����",
			"�����ͻ���������"
			};
	
	public String num[] = new String[]{"110",
			"119",
			"120",
			"11185",
			"12117",
			"96310",
			"96103",
			"12319",
			"12371",
			"114",
			"95598"};
	
	private Context context;
	
	public LifeNumAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return name.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return name[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.life_num_item, null);
		}
		
		TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv4);
		TextView numTv = (TextView) convertView.findViewById(R.id.numTv4);
		
		nameTv.setText(name[position]);
		numTv.setText(num[position]);
		
		return convertView;
	}

}
