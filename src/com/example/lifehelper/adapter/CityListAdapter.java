package com.example.lifehelper.adapter;

import java.util.ArrayList;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class CityListAdapter extends BaseAdapter implements SectionIndexer{
	private Context mContext;
	private ArrayList<String> City;
	private String [] mIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	private String [][] cityName = {{"����","������","����","����","����","��ɽ","��˳","����","����"},
			{"����","����","����","����","��ɽ","��ͷ","����","����","����","��Ϫ","�Ͻ�","����","��ɫ","����"},
			{"����","�ɶ�","��ɳ","����","����","����","����","����","����","����","����","�е�","����","���","����","����","����","����","����"},
			{"����","��ݸ","����","����","����","��ͬ","���˰���","�º�","����","����","����","����","��Ӫ"},
			{"������˹","��ʩ","����"},
			{"����","���Ǹ�","��ɽ","��˳","����","����","����"},
			{"����","����","����","����","����","����","�㰲","��Ԫ","���","����"},
			{"����","������","�Ϸ�","����","���ͺ���","����","����","����","����","����","����","�ױ�","�ӳ�","�׸�","�ں�","��ˮ","����","��Դ","����","���","����","����","����","����","�Ƹ�","����","��ɽ","��ʯ","����","��«��","���ױ���","����","����"},
			{"��"},
			{"����","��ľ˹","����","����","����","����","������","����","����","���","����","������","����","����","��","����","����","����","�Ž�","��Ȫ"},
			{"����","����"},
			{"����","����","����","����","�ȷ�","��ɽ","��ɽ","���Ƹ�","�ĳ�","����","��Դ","����","�ٲ�","�ٷ�","����","����","��֥","��ˮ","����","����ˮ","����","¤��","����","¦��","���","����","����","����"},
			{"��ɽ","ï��","üɽ","÷��","����","ĵ����"},
			{"�Ͼ�","�ϲ�","����","����","�ϳ�","��ƽ","��ͨ","����","����","�ڽ�","����","ŭ��"},
			{"��"},
			{"�̽�","��֦��","ƽ��ɽ","ƽ��","Ƽ��","����","���"},
			{"�ൺ","ǭ����","ǭ��","ǭ����","����","��Զ","�ػʵ�","����","�������","Ȫ��","����","����"},
			{"�տ���","����"},
			{"�Ϻ�","����","����","����","ʯ��ׯ","����Ͽ","����","����","����","����","����","ɽ��","��ͷ","��β","�ع�","����","����","ʮ��","˷��","��ƽ","�绯","����","����","��Ǩ","����"},
			{"���","̫ԭ","̩��","̩��","̨��","��ɽ","��ˮ","����","ͭ��","ͨ��","ͨ��","ͭ��","ͭ��","̨��"},
			{"��"},
			{"��"},
			{"�人","��³ľ��","����","����","Ϋ��","��ɽ","����","�ں�","�ߺ�","�����첼","����","����"},
			{"����","����","����","�差","��̶","����","����","����","Т��","��̨","����","����","����","����","��˫����","����","���","����","���","���ֹ���","�˰�"},
			{"����","�Ű�","�Ӱ�","�ӱ�","�γ�","����","��Ȫ","����","��̨","�˱�","�˲�","�˴�","Ӫ��","����","����","����","����","�˳�","�Ƹ�","����","��Ϫ","����"},
			{"��ɽ"},};
	//private ViewHolder mHolder;
	private CityGridAdapter adapter;
	
	public CityListAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return mIndex.length-1;
	}

	@Override
	public Object getItem(int position) {
		return mIndex[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder mHolder = null;
		adapter = new CityGridAdapter(mContext, cityName[position]);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.city_list, null);
		}
		if (null == mHolder) {
			mHolder = new ViewHolder();
			mHolder.indexLetter = (TextView) convertView.findViewById(R.id.index_letter);
			mHolder.gridView = (GridView) convertView.findViewById(R.id.city);
			convertView.setTag(mHolder);
		}else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.indexLetter.setText(mIndex[position]);
		mHolder.gridView.setAdapter(adapter);
		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView indexLetter;
		GridView gridView;
	}

	@Override
	public int getPositionForSection(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSectionForPosition(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

}
