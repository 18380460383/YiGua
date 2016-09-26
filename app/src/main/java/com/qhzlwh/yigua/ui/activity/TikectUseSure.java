package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.util.DateUtil;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 确定使用票
 */
public class TikectUseSure extends BaseActivity {

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.tikect_type)
    TextView tikectType;
    @Bind(R.id.tikect_name)
    TextView tikectName;
    @Bind(R.id.tikect_count)
    TextView tikectCount;
    @Bind(R.id.tikect_work)
    TextView tikectWork;
    @Bind(R.id.tikcet_use)
    TextView tikcetUse;
    @Bind(R.id.tikect_bg)
    RelativeLayout tikectBg;
    @Bind(R.id.tikect_sure)
    Button tikectSure;
    public static String SENIC_NAME="";
    public static String SENIC_ID="";
    public static String TICKET_TIME="";
    public static String TICKET_COUNT="";
    public static String TICKET_TYPE="";
    public static String ticket_type="";
    public static String ticket_id="";
    public static String is_senic="";
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tikect_use_sure);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("确定使用门票");
        bundle = getIntent().getExtras();
        if(bundle !=null){
            SENIC_NAME=bundle.getString("SENIC_NAME");
            SENIC_ID=bundle.getString("SENIC_ID");
            TICKET_TIME=bundle.getString("TICKET_TIME");
            TICKET_COUNT=bundle.getString("TICKET_COUNT");
            TICKET_TYPE=bundle.getString("TICKET_TYPE");
            ticket_type=bundle.getString("ticket_type");
            ticket_id=bundle.getString("ticket_id");
            is_senic=bundle.getString("is_senic");
            tikectCount.setText("门票数量： "+ bundle.getString("TICKET_COUNT")+"张");
            tikectType.setText(bundle.getString("TICKET_TYPE"));
            tikectName.setText(bundle.getString("SENIC_NAME"));
            tikectWork.setText("有  效  期："+DateUtil.timesOne(bundle.getString("TICKET_TIME")));
        }
    }

    @OnClick({R.id.top_back, R.id.tikect_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;

            case R.id.tikect_sure:

                OkHttpUtils.post().url(Myconstant.useSenicTicket)
                        .addParams("uid",""+MyApplication.USER_ID)
                        .addParams("senic_id",SENIC_ID)
                        .addParams("is_senic",is_senic)
                        .addParams("ticket_id",ticket_id)
                        .addParams("ticket_type",ticket_type)
                        .addParams("come_date",TICKET_TIME)
                        .addParams("number",TICKET_COUNT)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showShortToast(TikectUseSure.this,"门票使用错误");
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject js=new JSONObject(response);
                            int code=js.getInt("retcode");
                            String data=js.getString("msg");
                            if(code!=2000){
                                ToastUtil.showShortToast(TikectUseSure.this,data);
                            }else{
                                LogUtil.e("tikecteuse",response);
                                Intent intent = new Intent(TikectUseSure.this, TikectUseMessage.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                break;
        }
    }
/*    public void showAlterDialog(final String payType){
        final AlertDialog dialog=new AlertDialog(OrderPay.this);
        dialog.setTitle("使用");
        dialog.setMessage("确定使用 "+((int)(amount/100))+" 元？");
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("PING",payType+"    "+amount+"     "+order_id+"      "+order_type);
                if(payType.contains("阿里")){
                    postPay(CHANNEL_ALIPAY);
                    //new PaymentTask().execute(new PaymentRequest(CHANNEL_ALIPAY,amount,order_id,order_type));
                }else if(payType.contains("微信")){
                    postPay(CHANNEL_WECHAT);
                    // new PaymentTask().execute(new PaymentRequest(CHANNEL_WECHAT,amount,order_id,order_type));
                }
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }*/

}
