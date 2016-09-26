package com.qhzlwh.yigua.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.util.DisplayUtil;


/**
 * 创建者：Administrator
 * 时间：2016/8/26
 * 功能描述：
 */
public class MyLinearLayout extends View {
    int width;
    int height;
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(getResources().getColor(R.color.gray));                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);
        getWidthAndHeight();
        RectF oval=new RectF();                     //RectF对象
        oval.left=0;                              //左边
        oval.top=100;                                   //上边
        oval.right=width;                             //右边
        oval.bottom= DisplayUtil.dip2px(getContext(), 500);                             //下边
        canvas.drawArc(oval, 225, 90, false, paint);    //绘制圆弧
    }
    public void getWidthAndHeight(){
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height= wm.getDefaultDisplay().getHeight();
    }


}
