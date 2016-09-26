package com.qhzlwh.yigua.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 创建者：Administrator
 * 时间：2016/8/30
 * 功能描述：
 */
public class GridViewNoScoll extends GridView{
    public GridViewNoScoll(Context context) {
        super(context);
    }

    public GridViewNoScoll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewNoScoll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
