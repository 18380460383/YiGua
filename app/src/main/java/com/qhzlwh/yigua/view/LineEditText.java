package com.qhzlwh.yigua.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 创建者：Administrator
 * 时间：2016/8/31
 * 功能描述：自定义具有下划线的EditeText
 */
public class LineEditText  extends EditText {
    private Paint mPaint;

    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

//      画底线
        canvas.drawLine(0,this.getHeight()-1,  this.getWidth()-1, this.getHeight()-1, mPaint);
    }
}