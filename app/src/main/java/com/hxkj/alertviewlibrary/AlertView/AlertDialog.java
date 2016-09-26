package com.hxkj.alertviewlibrary.AlertView;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhzlwh.yigua.R;


/**
 * Created by Administrator on 2016/4/8.
 */
public class AlertDialog {
    Context context;
    android.app.AlertDialog ad;
    TextView titleView;
    TextView messageView;
    LinearLayout buttonLayout;
    public AlertDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        ad=new android.app.AlertDialog.Builder(context).create();
        ad.setCancelable(false);
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(R.layout.alertdailog);
        titleView=(TextView)window.findViewById(R.id.title);
        messageView=(TextView)window.findViewById(R.id.message);
        buttonLayout=(LinearLayout)window.findViewById(R.id.buttonLayout);
    }
    public void setTitle(int resId)
    {
        titleView.setText(resId);
    }
    public void setTitle(String title) {
        titleView.setText(title);
    }
    public void setMessage(int resId) {messageView.setText(resId);}
    public void setMessage(String message)
    {
        messageView.setText(message);
    }
    /**
     * 设置按钮
     * @param text
     * @param listener
     */
    public void setPositiveButton(String text,final View.OnClickListener listener)
    {
        Button button=new Button(context);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1);
        params.setMargins(10, 10, 10, 10);
        button.setBackgroundResource(R.drawable.dailog_button_other);
        button.setText(text);
        button.setTextColor(context.getResources().getColor(R.color.dailogColor_button_others));
        button.setTextSize(20);
        button.setOnClickListener(listener);
        buttonLayout.addView(button, params);
    }  /**
     * 设置按钮
     * @param text
     * @param listener
     */
    public void setNegativeButton(String text,final View.OnClickListener listener)
    {
        Button button=new Button(context);

        button.setBackgroundResource(R.drawable.dailog_button_single);
        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setTextSize(20);
        button.setOnClickListener(listener);
        if(buttonLayout.getChildCount()>0)
        {
            LinearLayout.LayoutParams params=new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,1);
            params.setMargins(10, 10, 10, 10);
            button.setLayoutParams(params);
            buttonLayout.addView(button, 1);
        }else{
            LinearLayout.LayoutParams params=new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            button.setPadding(60,0,60,0);
            button.setLayoutParams(params);
            buttonLayout.addView(button);
        }  }
    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    }
}
