package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.view.CustomProgressDialog;

public class BaseActivity extends AppCompatActivity {
    private CustomProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // StatusBarCompat.compat(this);
        //透明状态栏.
        //setImmerseLayout();
        progressDialog = new CustomProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
    }
    protected void setImmerseLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
                /*window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        /*    int statusBarHeight = MyUtil.getStatusBarHeight(BaseActivity.this);
            view.setPadding(0, statusBarHeight, 0, 0);*/
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    /**
     * 彭
     * 显示进度条
     * @param text 对话框提示字段
     */
    public void showProgressDialog(String text) {
        Log.i("tag", "对话框显示");
        if(text!=null&&!TextUtils.isEmpty(text.trim())) {
            progressDialog.setText(text);
        }
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }

    }

    /**
     * 彭
     * 关闭进度条
     */
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
