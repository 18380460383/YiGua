package com.hxkj.alertviewlibrary.AlertView;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxkj.alertviewlibrary.R;


/**
 * Created by Administrator on 2016/4/8.
 */
public class AlertDialogup {
    Context context;
    android.app.AlertDialog ad;
    TextView titleView;
    TextView messageView;
    LinearLayout buttonLayout;
    public AlertDialogup(Context context,View view) {
        // TODO Auto-generated constructor stub
        this.context=context;
        ad=new android.app.AlertDialog.Builder(context).create();
        ad.setCancelable(false);
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(view);
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
    }
    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    }
}
