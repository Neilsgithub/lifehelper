package com.example.lifehelper.adapter;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OperatorNumAdapter extends BaseAdapter {
	
	String name[] = new String[]{"������ͨVIP�ͷ�",
			"������ͨ�绰Ӫ������",
			"��ͨ����",
			"�й����ų�ֵ����",
			"�й����ź������ͨ",
			"�й����ſͷ�",
			"�й�����������������",
			"�й���ͨ��ֵר��",
			"�й���ͨ�绰����",
			"�й���ͨ���ſͷ�",
			"�й���ͨ�ͷ�",
			"�й���ͨͶ������"
			};
	
	public String num[] = new String[]{"10018",
			"10016",
			"10198",
			"11888",
			"118114",
			"10000",
			"10001",
			"10011",
			"116114",
			"10019",
			"10010",
			"10015",};
	private Context context;
	
	public OperatorNumAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		return name.length;
	}

	@Override
	public Object getItem(int position) {
		return name[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.operator_num_item, null);
		}
		
		TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv5);
		TextView numTv = (TextView) convertView.findViewById(R.id.numTv5);
		
		nameTv.setText(name[position]);
		numTv.setText(num[position]);
		
		return convertView;
	}

}
