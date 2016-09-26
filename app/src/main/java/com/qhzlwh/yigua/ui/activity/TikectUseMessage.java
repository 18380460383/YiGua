package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qhzlwh.yigua.MainActivity;
import com.qhzlwh.yigua.R;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class TikectUseMessage extends BaseActivity {


    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top)
    RelativeLayout top;
    @Bind(R.id.tikect_use_image)
    ImageView tikectUseImage;
    @Bind(R.id.tikect_use_message)
    TextView tikectUseMessage;
    @Bind(R.id.tikect_use_back)
    Button tikectUseBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseLayout();
        setContentView(R.layout.activity_tikect_use_message);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("使用成功");
    }

    @OnClick(R.id.tikect_use_back)
    public void onClick() {
        Intent intent=new Intent(TikectUseMessage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
