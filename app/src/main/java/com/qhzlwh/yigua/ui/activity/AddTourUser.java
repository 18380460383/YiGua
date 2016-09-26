package com.qhzlwh.yigua.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.view.CustomDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加旅客
 */

public class AddTourUser extends BaseActivity {

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.input_name)
    EditText inputName;
    @Bind(R.id.input_idcard)
    EditText inputIdcard;
    @Bind(R.id.input_phone)
    EditText inputPhone;
    @Bind(R.id.add_submit)
    Button addSubmit;
    @Bind(R.id.add_user_know)
    TextView addUserKnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour_user2);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("添加常用旅客");
    }

    @OnClick({R.id.top_back, R.id.add_submit, R.id.add_user_know})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.add_submit:

                break;
            case R.id.add_user_know:
              //  MyDialog.showMsg(AddTourUser.this,"1.须知","2.须知","3.须知");
                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                builder.setMessage("1.须知\n2.须知\n3.须知");
                builder.setTitle("姓名填写须知");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //设置你的操作事项
                    }
                });
              builder.setCancleImageClickListtenser(new CustomDialog.CustomDialogCancle() {
                  @Override
                  public void cancle(CustomDialog dialog) {
                      dialog.dismiss();
                  }
              });
                builder.create().show();
                break;
        }
    }
}
