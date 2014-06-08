package com.example.lifehelper.adapter;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TicketNumAdapter extends BaseAdapter {
	
	public String name[] = new String[]{"�������ٷ���Ʊ�绰",
			"�ɶ���·��",
			"�������Ŷ�Ʊ�绰",
			"������·��",
			"���ﺽ�շ�������",
			"������������",
			"��������",
			"����֪����Ա��������",
			"���Ϻ��ն�Ʊ��ѯ",
			"���Ϻ��շ�������",
			"���麽�տͻ�����",
			"��¹���շ�������"
			};
	
	public String num[] = new String[]{"95105105",
			"02896006",
			"073196020088",
			"02996688",
			"4008206222",
			"95530",
			"95583",
			"4006100666",
			"950718",
			"950712",
			"4007006000",
			"4000666888",};
	private Context context;
	
	public TicketNumAdapter(Context context) {

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
			convertView = LayoutInflater.from(context).inflate(R.layout.ticket_num_item, null);
		}
		
		TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv);
		TextView numTv = (TextView) convertView.findViewById(R.id.numTv);
		
		nameTv.setText(name[position]);
		numTv.setText(num[position]);
		
		return convertView;
	}

}
