package com.qhzlwh.yigua.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qhzlwh.yigua.MainActivity;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 启动界面
 */
public class SplashActivity extends Activity implements Animation.AnimationListener {
    //ImageView splashImage;
    @Bind(R.id.splash_image)
    ImageView splashImage;
    private String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        // ButterKnife.inject(this);
        // ButterKnife.bind(this);
        //splashImage= (ImageView) findViewById(R.id.splash_image);
        initView();
    }

    private void initView() {
        SharedPreferences sp1 = MyApplication.getInstance().getSp();
        LogUtil.e(TAG, "USER_ID=" + sp1.getString("USER_ID", "") +
                "R_TOKENN=" + sp1.getString("R_TOKENN", "") +
                "phone=" + sp1.getString("phone", "") +
                "islogin=" + sp1.getBoolean("islogin", false));
        if (sp1.getBoolean("islogin", false)) {
            MyApplication.IS_LOGIN = true;
            MyApplication.USER_ID = Integer.valueOf(sp1.getString("USER_ID", ""));
            MyApplication.PHONE = sp1.getString("phone", "");
        }
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        animation.setAnimationListener(this);
        splashImage.startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
