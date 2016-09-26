package com.qhzlwh.yigua.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.qhzlwh.yigua.R;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;

/**
 * 创建者：Administrator
 * 时间：2016/9/2
 * 功能描述：弹出选择日期
 */
public class SelectPicPopupWindow extends PopupWindow {
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private View mMenuView;

    public SelectPicPopupWindow(Activity context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popu_getonetikect_date, null);

        //设置按钮监听
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                SelectPicPopupWindow.this.dismiss();
                return true;
            }
        });
        GridViewNoScoll mGridView= (GridViewNoScoll) mMenuView.findViewById(R.id.popu_gridview);
        List<String> list=new ArrayList<>();
        for (int i = 0; i <31 ; i++) {
            list.add("price"+i);
        }
        mGridView.setAdapter(new CommonAdapter<String>(context, R.layout.senic_getoneticket_gridview_item, list) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.item_price, item);
                final LinearLayout bg = viewHolder.getView(R.id.item_background);
                bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bg.setBackgroundResource(R.drawable.button_retangle_solid);
                        SelectPicPopupWindow.this.dismiss();

                    }
                });
            }
        });

    }
}
