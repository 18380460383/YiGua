package com.qhzlwh.yigua.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.LoginUserBean;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.MyUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.RegularUtil;
import com.qhzlwh.yigua.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class LoginFast extends BaseActivity implements View.OnClickListener {

    private Button mGetCode;
    private Button mLogin;
    private EditText mCode;
    private EditText mPhone;
    private String TAG="LoginFast";
    public static int TYPE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseLayout();
        setContentView(R.layout.activity_login_fast);
        initView();
    }

    private void initView() {
        mGetCode = (Button) findViewById(R.id.login_getverification_Code);
        mPhone = (EditText) findViewById(R.id.login_input_phone);
        mCode = (EditText) findViewById(R.id.login_input_verification_Code);
        mLogin = (Button) findViewById(R.id.login_login);
        mGetCode.setOnClickListener(this);
        mLogin.setOnClickListener(this);
       // mhander.sendEmptyMessage(1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_getverification_Code:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                String phone=mPhone.getText().toString();
                LogUtil.e(TAG,MyUtil.getUnique(LoginFast.this));
                if(phone.length()>0&&RegularUtil.isMobileNO(phone)){
                  OkHttpUtils.post().url(Myconstant.GETCODE_URL).addParams("mobile",phone).addParams("device_token", MyUtil.getUnique(LoginFast.this)).build().execute(new StringCallback() {
                      @Override
                      public void onError(Request request, Exception e) {
                          Log.e(TAG, request.toString());
                          ToastUtil.showShortToast(LoginFast.this, "出现错误");
                      }

                      @Override
                      public void onResponse(String response) {
                          Log.e(TAG, response);
                          if (response.contains("4002")) {
                              ToastUtil.showShortToast(LoginFast.this, "发送失败");
                          } else if (response.contains("4001")) {
                              ToastUtil.showShortToast(LoginFast.this, "60秒内不能重复发送");
                          } else if (response.contains("2000")) {
                              mGetCode.setClickable(false);
                              ToastUtil.showShortToast(LoginFast.this, "发送成功");
                              mhander.sendEmptyMessage(1);
                          }
                      }
                  });
                }
                else if(phone.isEmpty()){
                    ToastUtil.showShortToast(LoginFast.this, "手机号不能为空！");
                }else{
                    ToastUtil.showShortToast(LoginFast.this, "手机号不合法！");
                }

                break;
            case R.id.login_login:

                String Code=mCode.getText().toString();
                final String phone1=mPhone.getText().toString();
                ToastUtil.showShortToast(LoginFast.this, "登录！" + phone1 + "  " + Code);
                if(Code.equals("")){
                    ToastUtil.showShortToast(LoginFast.this, "验证码不能为空！");
                }
               else if(phone1.equals("")){
                    ToastUtil.showShortToast(LoginFast.this, "手机号不能为空！");
                }else  if(!RegularUtil.isMobileNO(phone1)){
                    ToastUtil.showShortToast(getApplicationContext(),"手机号码不合法");
                }else{
                    OkHttpUtils.post().url(Myconstant.SENIC_LOGIN).addParams("mobile",phone1).addParams("device_token",MyUtil.getUnique(LoginFast.this)).addParams("code",Code)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e(TAG,"错误"+request.toString());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG,response);
                            try{
                                Gson gson=new Gson();
                                LoginUserBean user=gson.fromJson(response,LoginUserBean.class);
                                MyApplication app= (MyApplication) getApplication();
                                if(user.getRetcode()==2000){

                                    SharedPreferences.Editor edit = MyApplication.getInstance().getSp().edit();
                                    edit.putString("USER_ID",""+user.getData().getUser_id());
                                    edit.putString("R_TOKENN",""+user.getData().getR_token());
                                    edit.putString("phone",phone1);
                                    edit.putBoolean("islogin",true);
                                    edit.commit();
                                    app.USER_ID=user.getData().getUser_id();
                                    app.PHONE=phone1;
                                    app.IS_LOGIN=true;
                                    app.USER_ID=user.getData().getUser_id();
                                    app.R_TOKENN=user.getData().getR_token();
                                    Intent intent=null;
                                    SharedPreferences sp=MyApplication.getInstance().getSp();
                                    LogUtil.e("Login","USER_ID="+sp.getString("USER_ID","")+
                                            "R_TOKENN="+sp.getString("R_TOKENN","")+
                                            "phone="+sp.getString("phone","")+
                                            "islogin="+sp.getBoolean("islogin",false));
                                    if(TYPE==1){
                                        finish();
                                       // intent =new Intent(LoginFast.this,OrderWrite.class);
                                    }else if(TYPE==2){
                                        finish();
                                    }else if(TYPE==3){
                                        intent =new Intent(LoginFast.this,MyCollection.class);
                                    }
                                    else{
                                        finish();
                                       // intent =new Intent(LoginFast.this,MainActivity.class);
                                    }
                                    if(intent!=null){
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }
                            catch (Exception e){

                            }
                        }
                    });
                }
                break;
        }
    }
      int count=60;
    Handler mhander=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            count--;
            mGetCode.setText(""+count);
            if(count==0){
                mGetCode.setText("获取验证码");
                mGetCode.setClickable(true);
            }else{
                mhander.sendEmptyMessageDelayed(1,1000);
            }
            return false;
        }
    });
}
