package com.example.uu.gaode.viewpager;

import android.content.Context;
import android.view.View;

public final class ScreenUtil {

	/**
	 * ��Ļ���
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}
	/**
	 * ��Ļ�߶�
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}
	/**
	 * ��onCreate()���view�ĸ߶�
	 * 
	 * @param view
	 *            �ؼ�
	 * @return �߶�
	 */
	public static int getViewHeight(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return view.getMeasuredHeight();
	}
	/**
	 * ��onCreate()���view�Ŀ��
	 * 
	 * @param view
	 *            �ؼ�
	 * @return ���
	 */
	public static int getViewWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return view.getMeasuredWidth();
	}
}
