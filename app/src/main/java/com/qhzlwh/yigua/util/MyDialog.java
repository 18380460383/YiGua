package com.qhzlwh.yigua.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * 创建者：Administrator
 * 时间：2016/9/5
 * 功能描述：
 */
public class MyDialog {
    public static void showMsg(Context context, String title, String msg1, String msg2) {
        String str = title;
        if (null !=msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null !=msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

    /**
     * 添加只有确定的dialog
     * @param context
     * @param title
     * @param msg1
     * @param msg2
     */
    public static void showMsgOnlySure(Context context, String title, String msg1, String msg2) {
        String str = title;
        if (null !=msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null !=msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }
}
