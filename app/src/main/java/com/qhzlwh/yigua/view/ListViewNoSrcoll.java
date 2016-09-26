package com.qhzlwh.yigua.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 创建者：Administrator
 * 时间：2016/8/30
 * 功能描述：
 */
public class ListViewNoSrcoll extends ListView{
    public ListViewNoSrcoll(Context context) {
        super(context);
    }

      public ListViewNoSrcoll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewNoSrcoll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

 /*   @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }*/
}
