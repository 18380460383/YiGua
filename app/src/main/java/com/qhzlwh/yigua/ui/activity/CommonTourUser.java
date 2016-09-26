package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.view.ListViewNoSrcoll;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 常用旅客
 */
public class CommonTourUser extends BaseActivity {

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.add_user)
    LinearLayout addUser;
    @Bind(R.id.user_list)
    ListViewNoSrcoll userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour_user);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("常用旅客");
    }

    @OnClick({R.id.top_back, R.id.add_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.add_user:
                Intent intent=new Intent(CommonTourUser.this,AddTourUser.class);
                startActivity(intent);
                break;
        }
    }
}
