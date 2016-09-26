package com.example.uu.gaode.util;

import android.content.Context;
import android.content.SharedPreferences;


public class ConfigSharePreferencs {
    private static ConfigSharePreferencs configSharePreferencs;
    private SharedPreferences sharedPreferences;
    private static final String FILE_NAME = "config";
    private static final String VERSION="version";

//    private static final String BALANCE = "balance";
    private static final String APPVERSION = "appVersion";
    private static final String SYSTEMVERSION="systemVersion";
    private ConfigSharePreferencs(Context context) {
        /*
        SharePreferences对象的创建过程
        1.getSharePreferences(String name ,int mode)
        2.Editor 来为SharePreferences添加数据
        3.SharePreferences把数据储存到XML文件中
        4.XML文件中保存数据格式key-value键值对
         */

        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized ConfigSharePreferencs getInstance(Context context) {
        if (configSharePreferencs == null) {
            configSharePreferencs = new ConfigSharePreferencs(context);
        }
        return configSharePreferencs;
    }

    /**
     * 设置手机系统版本号
     * @param systemVersion
     */
    public void setSYSTEMVERSION(int systemVersion){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SYSTEMVERSION, systemVersion);
        editor.commit();
    }

    /**
     * 获取手机版本号
     * @return
     */
    public int getSYSTEMVERSION() {
        //暂时默认返回4.4以上
        return sharedPreferences.getInt(SYSTEMVERSION,19);
    }

    public void setAPPVERSION(String appVersion){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APPVERSION, appVersion);
        editor.commit();
    }


    public String getAPPVERSION() {
        return sharedPreferences.getString(APPVERSION,"");
    }

             /*
    存储当前版本
     */

    public void setVersion(int version) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(VERSION, version);
        editor.commit();
    }

    /*
    读取当前版本，默认值为0：代表新安装
     */

    public int getVersion() {
        return sharedPreferences.getInt(VERSION, 0);   //正式使用应使用这一条
    }


}


