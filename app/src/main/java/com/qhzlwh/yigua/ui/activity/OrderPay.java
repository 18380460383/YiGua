package com.qhzlwh.yigua.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hxkj.alertviewlibrary.AlertView.AlertDialog;
import com.pingplusplus.android.Pingpp;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.util.LogUtil;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 订单支付
 */
public class OrderPay extends BaseActivity {
    private String TAG="OrderPay";
    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.order_pay_ordernumber)
    TextView orderPayOrdernumber;
    @Bind(R.id.order_pay_senicname)
    TextView orderPaySenicname;
    @Bind(R.id.order_start_time)
    TextView orderStartTime;
    @Bind(R.id.order_contact_person)
    TextView orderContactPerson;
    @Bind(R.id.order_end_time)
    TextView orderEndTime;
    @Bind(R.id.order_contact_phone)
    TextView orderContactPhone;
    @Bind(R.id.order_person_count)
    TextView orderPersonCount;
    @Bind(R.id.order_price_count)
    TextView orderPriceCount;
    @Bind(R.id.pay_ali)
    LinearLayout payAli;
    @Bind(R.id.pay_wx)
    LinearLayout payWx;

    private static String YOUR_URL ="http://218.244.151.190/demo/charge";
    //public static final String URL = YOUR_URL;
   // public static final String URL = Myconstant.SENIC_PING;
    public static final String URL = "http://qh.91chuanbo.cn/Api/Ping/get_charge";
    public static String order_type="1";
    /**
     * 微信支付渠道
     */
    private static final String CHANNEL_WECHAT = "wx";
    /**
     * 支付支付渠道
     */
    private static final String CHANNEL_ALIPAY = "alipay";
    private String order_number="";
    private int amount=0;
    private String order_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("订单支付");
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            order_number=bundle.getString("订单编号");
            order_id=bundle.getString("订单id");
            amount= (int) ((Float.valueOf(bundle.getString("订单金额")))*100);
            float price=Float.valueOf(bundle.getString("单价"));
            orderPayOrdernumber.setText(getString(R.string.order_number)+order_number);
            orderPaySenicname.setText(getString(R.string.senic_name)+bundle.getString("景区名称"));
            String travle=bundle.getString("出发时间");
            orderStartTime.setText(getString(R.string.start_time)+travle);
            orderContactPerson.setText(getString(R.string.contact_person) + bundle.getString("联系人"));
           // orderPersonCount.setText(getString(R.string.person_count)+""+(int)((amount/100)/price));
            orderPersonCount.setText(getString(R.string.person_count)+""+bundle.getString("出游人数"));
            orderContactPhone.setText(getString(R.string.contact_phone)+bundle.getString("联系电话"));
            orderPriceCount.setText(getString(R.string.order_price_count)+amount/100+"元");
            orderEndTime.setText(getString(R.string.end_time)+travle);
            order_type=bundle.getString("订单类型");
        }
    }

    @OnClick({R.id.top_back, R.id.pay_ali, R.id.pay_wx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.pay_ali:
                showAlterDialog("阿里");
                break;
            case R.id.pay_wx:
                showAlterDialog("微信");
                break;
        }
    }
   public void showAlterDialog(final String payType){
        final AlertDialog dialog=new AlertDialog(OrderPay.this);
        dialog.setTitle("支付");
        dialog.setMessage("确定支付 "+((int)(amount/100))+" 元？");
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
    }
   public void postPay(final String payType){
         /*        参数名称	类型	参数说明
                channel	必须	支付渠道代码 alipay|支付宝手机支付 wx|微信支付
                amount	必须	订单金额，单位（分）
                order_id	必须	订单ID
                order_type	必须	订单类型 1|景区 2|土特产 3|线路游 4|自驾游*/
       //SENIC_PING
       LogUtil.e("PING",payType+"    "+amount+"     "+order_id+"      "+order_type);
       OkHttpUtils.post().url(URL).addParams("channel","wx")
               .addParams("amount", "" + amount)
               .addParams("order_id", order_id)
               .addParams("order_type", order_type)
               .build().execute(new StringCallback() {
           @Override
           public void onError(Request request, Exception e) {
               Log.e(TAG, "支付错误");
               LogUtil.e("PING",request.toString());
           }
           @Override
           public void onResponse(String response) {
               Log.e(TAG, response);
               try {
                   JSONObject jsonObject=new JSONObject(response);
                 JSONObject jsonObject1=jsonObject.getJSONObject("data");
                   Log.e(TAG, jsonObject1.toString());
                   Pingpp.createPayment(OrderPay.this, jsonObject1.toString());
               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       });
   }
    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                if(result.equals("success")){
                    startActivity(new Intent(OrderPay.this,OrderAll.class));
                    finish();
                }else{
                    showMsg(result, errorMsg, extraMsg);
                }
            }
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null !=msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null !=msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(OrderPay.this);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }
    private static String postJson(String url, String json) throws IOException {
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(type, json);
        Request request = new Request.Builder().url(url).post(body).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    class PaymentRequest {
        String channel;
        int  amount;
        String order_id;
        String order_type;
        public PaymentRequest(String channel, int amount,String order_id,String order_type) {
            this.channel = channel;
            this.amount = amount;
            this.order_id=order_id;
            this.order_type=order_type;
        }
    }
    class PaymentTask extends AsyncTask<PaymentRequest, Void, String> {

        @Override
        protected void onPreExecute() {
            //按键点击之后的禁用，防止重复点击
        /*    wechatButton.setOnClickListener(null);
            alipayButton.setOnClickListener(null);
            upmpButton.setOnClickListener(null);
            bfbButton.setOnClickListener(null);
            qpayButton.setOnClickListener(null);*/
        }
        @Override
        protected String doInBackground(PaymentRequest... pr) {

            PaymentRequest paymentRequest = pr[0];
            String data = null;
            String json = new Gson().toJson(paymentRequest);
            try {
                //向Your Ping++ Server SDK请求数据
                data = postJson(URL, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }
        /**
         * 获得服务端的charge，调用ping++ sdk。
         */
        @Override
        protected void onPostExecute(String data) {
            if(null == data){
                showMsg("请求出错", "请检查URL", "URL无法获取charge");
                return;
            }
            Log.d("charge", data);
            Pingpp.createPayment(OrderPay.this, data);
        }

    }
}
