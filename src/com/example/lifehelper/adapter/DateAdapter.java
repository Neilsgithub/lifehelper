package com.example.lifehelper.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.lifehelper.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DateAdapter extends BaseAdapter {

	private static final String TAG = "TAG";
	private Context context;
	private List<MyItemEntry> lstData;
	private TextView txtAge;
	private ImageView imgViewItem;
	private int holdPosition;
	private boolean isChanged = false;
	private boolean ShowItem = false;

	/**
	 * ���캯��
	 * 
	 * @param mContext
	 * @param listData
	 */
	public DateAdapter(Context mContext,
			List<MyItemEntry> listData) {
		this.context = mContext;
		this.lstData = listData;
	}

	/**
	 * ��д�������
	 */
	@Override
	public int getCount() {
		return lstData.size();
	}

	/**
	 * ��д�Item
	 */
	@Override
	public Object getItem(int position) {
		return lstData.get(position);
	}

	/**
	 * ��д���ItemID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	public void exchange(int startPosition, int endPosition) {
		Log.d(TAG, startPosition+"--->"+endPosition);
		holdPosition = endPosition;
		//ͨ����д��getItem�������һ���ƶ�ǰ��Item
		Object startObject = getItem(startPosition);
		if (startPosition < endPosition) {
			//�ı�����Դ��˳��
			//�±�Ϊ����λ��+1��ֵΪ�϶���Item����
			lstData.add(endPosition + 1, (MyItemEntry)startObject);
			//ͨ���±���ԭ����ԭ�������Դ�е�Ԫ��
			lstData.remove(startPosition);
		} else {
			lstData.add(endPosition, (MyItemEntry)startObject);
			lstData.remove(startPosition + 1);
		}
		isChanged = true;
		notifyDataSetChanged();
	}

	public void showDropItem(boolean showItem) {
		this.ShowItem = showItem;
	}

	/**
	 * ��дgetView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ���ÿһ��Item
		convertView = LayoutInflater.from(context).inflate(R.layout.grid_item,
				null);
		imgViewItem = (ImageView) convertView
				.findViewById(R.id.myiv);
		txtAge = (TextView) convertView.findViewById(R.id.mytv);
		imgViewItem.setImageResource((Integer) lstData.get(position).getDrawable());
		txtAge.setText(lstData.get(position).getName());
		// ���λ�øı�
		if (isChanged) {
			//ԭ����λ�� == �ƶ�������λ��
			if (position == holdPosition) {
				if (!ShowItem) {
					//���ɼ��������View��Ȼ��ռ����xml�ļ���������Ĳ��ֿռ�
					convertView.setVisibility(View.INVISIBLE);
				}
			}
		}

		return convertView;
	}

}
