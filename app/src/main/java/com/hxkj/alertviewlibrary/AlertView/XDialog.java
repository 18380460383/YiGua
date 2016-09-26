package com.hxkj.alertviewlibrary.AlertView;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhzlwh.yigua.R;

/**
 * Created by Administrator on 2016/4/8.
 */
public class XDialog {
    Context context;
    android.app.AlertDialog ad;
    TextView contentView;
    ImageView xcancel;
    public XDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        ad=new android.app.AlertDialog.Builder(context).create();
        ad.setCancelable(false);
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(R.layout.xdailog);
        contentView=(TextView)window.findViewById(R.id.content);
        xcancel=(ImageView)window.findViewById(R.id.xcancel);
    }
    public void setMessage(int resId,View.OnClickListener listener) {
        contentView.setText(resId);
        xcancel.setOnClickListener(listener);
    }
    public void setMessage(String message,View.OnClickListener listener) {
        contentView.setText(message);
        xcancel.setOnClickListener(listener);
    }
    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    }
}
