package com.example.lifehelper.self_define;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lifehelper.adapter.DateAdapter;

/**
 * �Զ���Ŀ���קGridView
 * 
 * @author Administrator
 * 
 */
public class DragGrid extends GridView {
	/** ����ѡ���λ�� **/
	private int dragPosition;
	/** ��ֹ��ѡ���λ�� **/
	private int dropPosition;

	/** ����ѡ�ʼ��λ�� **/
	private int startPosition;

	private int holdPosition;
	private int specialPosition = -1;
	private int leftBottomPosition = -1;

	private int nColumns = 2;

	/** ������ **/
	private int nRows;
	/** ����еĸ��� **/
	private int Remainder;

	/** ��ȡѡ������ **/
	private int itemTotalCount;
	/** ��ȡѡ��һ��Ŀ�� **/
	private int halfItemWidth;

	/** �����϶�img **/
	private ImageView dragImageView = null;
	private ViewGroup dragItemView = null;

	private WindowManager windowManager = null;
	private WindowManager.LayoutParams windowParams = null;

	/** λ������s **/
	private int mLastX, xtox;
	private int mLastY, ytoy;

	/** �϶�Item��Y���� **/
	private int specialItemY;
	private int leftBtmItemY;

	private String LastAnimationID;

	private boolean isCountXY = false;
	/** �Ƿ��ƶ� **/
	private boolean isMoving = false;

	// private ArrayList<ViewGroup> mItemViewList ;

	public DragGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DragGrid(Context context) {
		super(context);
	}

	boolean flag = false;

	

	public boolean setOnItemLongClickListener(final MotionEvent ev) {
		this.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// ���Ƹ��ؼ����ش����¼�
				getParent().requestDisallowInterceptTouchEvent(true);
				// ��ȡ��Ļ���겢��ֵ
				int x = (int) ev.getX();
				int y = (int) ev.getY();
				// ��ȡλ��
				mLastX = x;
				mLastY = y;
				// ��ֵ����λ��
				startPosition = dragPosition = dropPosition = arg2;
				// �������Чλ��(�����߽磬�ָ��ߵ�λ��)������
				// ��AdapterView.INVALID_POSITION ������Ч��λ�á���Чֵ�ķ�Χ�� 0 ����ǰ��������Ŀ���� 1
				// ��
				if (dragPosition == AdapterView.INVALID_POSITION) {
					return false;
				}

				// ��dragPosition��GridView��ǰ��ʾ����Ŷ�Ӧ
				// getChildAt(int position)��ʾdisplay�ڽ����positionλ�õ�View
				// getFirstVisiblePosition()���ص�һ��display�ڽ����view��adapter��λ��position��������0��Ҳ������4
				ViewGroup itemView = (ViewGroup) getChildAt(dragPosition
						- getFirstVisiblePosition());

				// ��ʼ������ѡ��,��,��,������Ϣ
				if (!isCountXY) {
					// ��ȡѡ��һ��Ŀ��
					halfItemWidth = itemView.getWidth() / 2;
					int rows;
					itemTotalCount = getCount();
					rows = itemTotalCount / nColumns;
					Remainder = itemTotalCount % nColumns;
					// ����һ�����������������һ�в�������
					nRows = Remainder == 0 ? rows : rows + 1;

					specialPosition = itemTotalCount - 1 - Remainder;//���һ����position

					// if(Remainder!=1)
					leftBottomPosition = nColumns * (nRows - 1);//���½ǵ�position
					// if(Remainder == 0 || nRows == 1)
					// specialPosition = -1;
					isCountXY = true;
				}

				if (specialPosition != dragPosition && dragPosition != -1) {
					specialItemY = getChildAt(specialPosition).getTop();
				} else {
					specialItemY = -1;
				}

				if (leftBottomPosition != dragPosition && dragPosition != -1) {
					leftBtmItemY = getChildAt(leftBottomPosition).getTop();
				} else {
					leftBtmItemY = -1;
				}

				dragItemView = itemView;
				// ÿ�ζ�����һ��cache����������һ��bitmap
				itemView.destroyDrawingCache();
				// ����DrawingcacheΪtrue�����ѡ�����Ӱ��bm�����Ǻ��������϶����ĸ�ͷ��
				itemView.setDrawingCacheEnabled(true);
				itemView.setDrawingCacheBackgroundColor(0xff6DB7ED);
				Bitmap bm = itemView.getDrawingCache(true);
				Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0,bm.getWidth() - 8, bm.getHeight() - 8);
				startDrag(bitmap, x, y);   //����������
				hideDropItem();   //���ظ���
				itemView.setVisibility(View.INVISIBLE);
				isMoving = false;
				return false;
			};
		});
		return super.onInterceptTouchEvent(ev);
	}

	public void GetItemShadow(int x, int y) {

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// ���ذ��¶���
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			return setOnItemLongClickListener(ev);
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (dragImageView != null
				&& dragPosition != AdapterView.INVALID_POSITION) {
			// ���Ƹ��ؼ������¼�
			getParent().requestDisallowInterceptTouchEvent(true);
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			switch (ev.getAction()) {
			case MotionEvent.ACTION_MOVE:
				if (!isCountXY) {
					// �ƶ����ĸ�λ��
					xtox = x - mLastX;
					ytoy = y - mLastY;
					isCountXY = true;
				}
				onDrag(x-120, y+20);
				if (!isMoving)
					OnMove(x, y);
				break;
			case MotionEvent.ACTION_UP:
				stopDrag();//������������
				onDrop(x, y);
				break;
			}
		}
		return super.onTouchEvent(ev);
	}

	private void startDrag(Bitmap bm, int x, int y) {
		stopDrag();
		windowParams = new WindowManager.LayoutParams();
		windowParams.gravity = Gravity.TOP | Gravity.LEFT;
		
//		windowParams.x = x;
//		windowParams.y = y;
		
		windowParams.x = dragItemView.getLeft()+20;
		windowParams.y = dragItemView.getTop()+halfItemWidth-10;
		Log.d("TAG", windowParams.x+"+"+windowParams.y);
		windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.alpha = 0.8f;

		ImageView iv = new ImageView(getContext());
		iv.setImageBitmap(bm);
		windowManager = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		// ��Ӱ��ImagView��ӵ���ǰ��ͼ��
		windowManager.addView(iv, windowParams);

		dragImageView = iv;
	}

	/**
	 * ����ѡ����ƶ�λ��
	 * 
	 * @param x
	 * @param y
	 */
	public void OnMove(int x, int y) {
		getParent().requestDisallowInterceptTouchEvent(true);
//		int TempPosition = pointToPosition(x, y);
//		int sOffsetY = specialItemY == -1 ? y - mLastY : y - specialItemY;
//				//- halfItemWidth;
//		int lOffsetY = leftBtmItemY == -1 ? y - mLastY : y - leftBtmItemY;
//				//- halfItemWidth;
//		if (TempPosition != AdapterView.INVALID_POSITION
//				&& TempPosition != dragPosition) {
//			dropPosition = TempPosition;
//		} else if (specialPosition != -1 && dragPosition == specialPosition
//				&& sOffsetY >= halfItemWidth) {
//			dropPosition = (itemTotalCount - 1);
//		} else if (leftBottomPosition != -1
//				&& dragPosition == leftBottomPosition
//				&& lOffsetY >= halfItemWidth) {
//			dropPosition = (itemTotalCount - 1);
//		}
//		if (dragPosition != startPosition)
//			dragPosition = startPosition;
//		int MoveNum = dropPosition - dragPosition;
//		if (dragPosition != startPosition && dragPosition == dropPosition)
//			MoveNum = 0;
//		if (MoveNum != 0) {
//			int itemMoveNum = Math.abs(MoveNum);
//			float Xoffset, Yoffset;
//			for (int i = 0; i < itemMoveNum; i++) {
//				if (MoveNum > 0) {
//					holdPosition = dragPosition + 1;
//					Xoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? (-1) : (nColumns - 1);
//					Yoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? 0 : (-1);
//				} else {
//					holdPosition = dragPosition - 1;
//					Xoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? 1 : (-(nColumns - 1));
//					Yoffset = (dragPosition / nColumns == holdPosition
//							/ nColumns) ? 0 : 1;
//				}
//				ViewGroup moveView = (ViewGroup) getChildAt(holdPosition);
//				Animation animation = getMoveAnimation(Xoffset, Yoffset);
//				moveView.startAnimation(animation);
//				dragPosition = holdPosition;
//				if (dragPosition == dropPosition)
//					LastAnimationID = animation.toString();
//				final DateAdapter adapter = (DateAdapter) this.getAdapter();
//				animation
//						.setAnimationListener(new Animation.AnimationListener() {
//
//							@Override
//							public void onAnimationStart(Animation animation) {
//								// TODO Auto-generated method stub
//								isMoving = true;
//							}
//
//							@Override
//							public void onAnimationRepeat(Animation animation) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onAnimationEnd(Animation animation) {
//								// TODO Auto-generated method stub
//								String animaionID = animation.toString();
//								if (animaionID
//										.equalsIgnoreCase(LastAnimationID)) {
//									adapter.exchange(startPosition,
//											dropPosition);
//									startPosition = dropPosition;
//									isMoving = false;
//								}
//							}
//						});
//			}
//		}
		int TempPosition = pointToPosition(x,y);
		int sOffsetY = specialItemY == -1 ? y - mLastY : y - specialItemY - halfItemWidth;
		int lOffsetY = leftBtmItemY == -1 ? y - mLastY : y - leftBtmItemY - halfItemWidth;
		if(TempPosition != AdapterView.INVALID_POSITION && TempPosition != dragPosition){
			dropPosition = TempPosition;
		}else if(specialPosition != -1 && dragPosition == specialPosition && sOffsetY >= halfItemWidth){
			dropPosition = (itemTotalCount - 1);
		}else if(leftBottomPosition != -1 && dragPosition == leftBottomPosition && lOffsetY >= halfItemWidth){
			dropPosition = (itemTotalCount - 1);
		}	
		if(dragPosition != startPosition)
			dragPosition = startPosition;
		int MoveNum = dropPosition - dragPosition;
		if(dragPosition != startPosition && dragPosition == dropPosition)
			MoveNum = 0;
		if(MoveNum != 0){
			System.out.println("moveNum="+MoveNum);
			int itemMoveNum = Math.abs(MoveNum);
			float Xoffset,Yoffset;
			for (int i = 0;i < itemMoveNum;i++){
				System.out.println("itemMoveNum=" + itemMoveNum);
				if(MoveNum > 0){
					holdPosition = dragPosition + 1;
//					Xoffset = (dragPosition/nColumns == holdPosition/nColumns) ? (-1) : (nColumns -1);
					Xoffset = 1;
					Yoffset = (dragPosition/nColumns == holdPosition/nColumns) ? 0 : (-1);
				}else{
					holdPosition = dragPosition - 1;
					Xoffset = (dragPosition/nColumns == holdPosition/nColumns) ? 1 : (-(nColumns-1));
					Yoffset = (dragPosition/nColumns == holdPosition/nColumns) ? 0 : 1;
				}
				ViewGroup moveView = (ViewGroup)getChildAt(holdPosition);				
				Animation animation = getMoveAnimation(Xoffset,Yoffset);
				moveView.startAnimation(animation);
				dragPosition = holdPosition;
				if(dragPosition == dropPosition)
					LastAnimationID = animation.toString();
				final DateAdapter adapter = (DateAdapter)this.getAdapter();
				animation.setAnimationListener(new Animation.AnimationListener() {


					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						isMoving = true;
					}


					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}


					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						String animaionID = animation.toString();
						if(animaionID.equalsIgnoreCase(LastAnimationID)){
							adapter.exchange(startPosition, dropPosition);
							startPosition = dropPosition;
							isMoving = false;
						}					
					}
				});	
			}
		}
	}

	/**
	 * ����������
	 * 
	 * @param x
	 * @param y
	 */
	private void onDrop(int x, int y) {
		final DateAdapter adapter = (DateAdapter) this.getAdapter();
		adapter.showDropItem(true);
		adapter.notifyDataSetChanged();
	}

	/**
	 * ��������ָ�ƶ����ı�windowmanager��λ��
	 * 
	 * @param x
	 * @param y
	 */
	private void onDrag(int x, int y) {
		// ����λ�ã�ͨ��windowManager��ͣ�ظ���View
		if (dragImageView != null) {
			windowParams.alpha = 0.8f;
//			windowParams.gravity=Gravity.LEFT|Gravity.TOP;
			windowParams.x = (x );
			windowParams.y = (y );
//					+ (int) (45 * Configure.screenDensity
			windowManager.updateViewLayout(dragImageView, windowParams);
		}
	}

	/**
	 * ��windowManager �Ƴ�ImageView �ڴ��ͷ�
	 */
	private void stopDrag() {
		if (dragImageView != null) {
			windowManager.removeView(dragImageView);
			dragImageView = null;
		}
	}

	/**
	 * ���ص��ѡ��
	 */
	private void hideDropItem() {
		final DateAdapter adapter = (DateAdapter) this.getAdapter();
		adapter.showDropItem(false);
	}

	public Animation getMoveAnimation(float x, float y) {
		TranslateAnimation go = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, x,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, y);
		go.setFillAfter(true);
		go.setDuration(300);
		return go;
	}

}
