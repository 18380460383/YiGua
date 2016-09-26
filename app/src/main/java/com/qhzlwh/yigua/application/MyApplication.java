package com.qhzlwh.yigua.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.qhzlwh.yigua.AppManager;
import com.qhzlwh.yigua.util.PreferenceUtil;


/**
 * 创建者：Administrator
 * 时间：2016/8/31
 * 功能描述：
 */
public class MyApplication extends Application {
    public static boolean IS_LOGIN=false;
    public static int USER_ID=27;
    public static String R_TOKENN="0";
    public static String PHONE="";
    private final String SHARED_USER = "yigua_context";
    private static MyApplication instance;
    private static Context _context;
    private static Resources _resource;
    private Activity oldinstance;
    private  Activity oneActivity;
    public SharedPreferences sp;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sp = getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE);
        IS_LOGIN=sp.getBoolean("islogin",false);
    }
    public SharedPreferences getSp() {
        if(sp==null){
            sp = getSharedPreferences(SHARED_USER,Context.MODE_PRIVATE);
        }
        return sp;
    }
    /**
     * 获得当前app运行的AppContext
     */
    public static MyApplication getInstance() {
        return instance;
    }
    public boolean isFirst() {
        return PreferenceUtil.getPrefBoolean(this, "isFirst", true);
    }
    public void setFirst() {
        PreferenceUtil.setPrefBoolean(this, "isFirst", false);
    }

    public Context context() {
        if(_context == null) {
            _context = getApplicationContext();
        }
        return _context;
    }

    public Resources resources() {
        if(_resource == null) {
            _resource = context().getResources();
        }
        return _resource;
    }
    public void setOldinstance(Activity oldinstance) {
        this.oldinstance = oldinstance;
    }

    public void setOneActivity(Activity oneActivity) {
        if(this.oldinstance!=oneActivity){
            this.oldinstance=this.oneActivity;
        }
        this.oneActivity = oneActivity;
    }

    public  Activity getOldinstance() {
        if(oldinstance==null||oldinstance == oneActivity){
            return AppManager.getAppManager().getOldActivity();
        }else{
            return oldinstance;
        }
    }

    public  Activity getOneActivity() {
        return oneActivity;
    }
    public  void setDownId(Long id){
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong("dowid", id);
        edit.commit();
    }
    public  Long getDownId(){
        return sp.getLong("dowid",0);
    }
    private void setChannel(){
        ApplicationInfo appInfo = null;
        try {
            appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            String msg=appInfo.metaData.getInt("UMENG_CHANNEL")+"";
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("channel",msg);
            edit.commit();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  String getChannel(){
        String string = sp.getString("channel", "appstore");
        if("0".equals(string)){
            string="appstore";
        }
        return string;
    }
}
