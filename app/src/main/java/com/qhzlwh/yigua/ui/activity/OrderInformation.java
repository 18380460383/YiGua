package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxkj.alertviewlibrary.AlertView.AlertDialog;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
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
 * 订单信息
 */
public class OrderInformation extends BaseActivity {


/*    public static String ORDER_TYPE = "";
    public static String ORDER_NUMBER = "2016-9-1";
    public static String TIKECT_TIME = "2016-9-1";
    public static String TIKECT_PRICE = "2016-9-1";
    public static String TIKECT_PRICE_COUNT = "2016-9-1";
    public static String SENIC_NAME = "2016-9-1";
    public static String SENIC_WAY = "2016-9-1";
    public static String SENIC_ADDRESS = "2016-9-1";
    public static String STAUS_NAME = "0";
    public static String ORDER_USER_NAME = "123";
    public static String ORDER_USER_PHONE = "123";
    public static String ORDER_USER_IDCARD = "123";
    public static String ORDER_ID = "176";
    public static String SENIC_ID = "";
    public static String TYPE_ID = "";*/

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    /*    @Bind(R.id.top)
        LinearLayout top;*/

    @Bind(R.id.order_state)
    TextView orderState;
    @Bind(R.id.order_senictopname)
    TextView orderSenictopname;
    @Bind(R.id.order_senic_time)
    TextView orderSenicTime;
    @Bind(R.id.order_senic_inway)
    TextView orderSenicInway;
    @Bind(R.id.order_senic_address)
    TextView orderSenicAddress;
    @Bind(R.id.order_type_senic)
    LinearLayout orderTypeSenic;
    @Bind(R.id.order_line_starttime)
    TextView orderLineStarttime;
    @Bind(R.id.order_line_endtime)
    TextView orderLineEndtime;
    @Bind(R.id.order_type_line)
    LinearLayout orderTypeLine;
    @Bind(R.id.order_price)
    TextView orderPrice;
    @Bind(R.id.order_paymethod)
    TextView orderPaymethod;
    @Bind(R.id.order_pricecount)
    TextView orderPricecount;
    @Bind(R.id.order_user)
    TextView orderUser;
    @Bind(R.id.order_phone)
    TextView orderPhone;
    @Bind(R.id.order_id_card)
    TextView orderIdCard;
    @Bind(R.id.order_cancel_message)
    TextView orderCancelMessage;
    @Bind(R.id.order_number)
    TextView orderNumber;
    @Bind(R.id.item_submit)
    RelativeLayout itemSubmit;
    @Bind(R.id.item_cancel)
    RelativeLayout itemCancel;
    @Bind(R.id.item_submit1)
    RelativeLayout itemSubmit1;
    @Bind(R.id.item_pay)
    LinearLayout itemPay;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_information);
        //  EventBus.getDefault().registerSticky(this);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }

    /*
      public void onEventMainThread(SenicOrderBean.DataBean event){
          LogUtil.e("evevt","执行");
           orderSenictopname.setText(event.getSenic_name());
            orderNumber.setText("订单号：" + event.getOrder_id());
            orderState.setText(event.getStatus_name());
            if (event.getType_name().equals("门票")) {
                orderTypeLine.setVisibility(View.GONE);
                orderSenicTime.setText("游玩时间：" + event.getCome_date());
                orderSenicInway.setText("入园方式：" + event.getAdmission_way());
                orderSenicAddress.setText("景区地址：" + event.getSenic_address());
            } else {
                orderTypeSenic.setVisibility(View.GONE);
                orderLineStarttime.setText("出发时间：" +  event.getCome_date());
                orderLineEndtime.setText("回团时间：" +  event.getCome_date());
            }
            orderPrice.setText("票       价:" + event.getPrice() + "/人");
            orderPricecount.setText("订单金额：" + event.getUnit_price());
            orderUser.setText("出  行  人:" + event.getList_info().get(0).getName());
            orderPhone.setText("联 系 电 话：" + event.getTelephone());
            orderIdCard.setText("身  份  证：" + event.getList_info().get(0).getId_card());
        }*/
    private void initView() {

        topTilteName.setText("订单信息");
        bundle = getIntent().getExtras();
        if(bundle !=null){
            LogUtil.e("order", bundle.getString("ORDER_TYPE"));
            orderSenictopname.setText(bundle.getString("SENIC_NAME"));
            orderNumber.setText("订单号：" + bundle.getString("ORDER_NUMBER"));
            orderSenictopname.setText(bundle.getString("SENIC_NAME"));
            orderState.setText(bundle.getString("STAUS_NAME"));
            if (bundle.getString("ORDER_TYPE").equals("门票")){
                orderTypeLine.setVisibility(View.GONE);
                orderSenicTime.setText("游玩时间：" + bundle.getString("TIKECT_TIME"));
                orderSenicInway.setText("入园方式：" + bundle.getString("SENIC_WAY"));
                orderSenicAddress.setText("景区地址：" + bundle.getString("SENIC_ADDRESS"));
            } else {
                orderTypeSenic.setVisibility(View.GONE);
                orderTypeLine.setVisibility(View.VISIBLE);
                orderLineStarttime.setText("出发时间：" + bundle.getString("TIKECT_TIME"));
                orderLineEndtime.setText("回团时间：" + bundle.getString("TIKECT_TIME"));
            }
            orderPrice.setText("票       价: " + bundle.getString("TIKECT_PRICE") + "/人");
            orderPricecount.setText("订单金额：" + bundle.getString("TIKECT_PRICE_COUNT"));
            orderUser.setText("出   行   人: " + bundle.getString("ORDER_USER_NAME"));
            String phone=bundle.getString("ORDER_USER_PHONE");
            String phones=phone.substring(0,3)+"*****"+phone.substring(7,11);
            orderPhone.setText("联 系 电 话： " +phones);
            String idcard=bundle.getString("ORDER_USER_IDCARD");
            String idcards=idcard.substring(0,6)+"********"+idcard.substring(idcard.length()-5,idcard.length()-1);
            orderIdCard.setText("身  份  证： " +idcards);
           String TYPE_ID= bundle.getString("TYPE_ID");
            if (TYPE_ID.equals("0") || TYPE_ID.equals("1") || TYPE_ID.equals("2") || TYPE_ID.equals("3")||TYPE_ID.equals("7")) {
                itemSubmit.setVisibility(View.GONE);
            }
            if (TYPE_ID.equals("0")) {
                itemPay.setVisibility(View.VISIBLE);
                itemSubmit.setVisibility(View.GONE);
            }
        }

    }
    public void showAlterDialog(){
        final AlertDialog dialog=new AlertDialog(OrderInformation.this);
        dialog.setMessage("确定取消订单？");
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("tag",bundle.getString("ORDER_ID"));
                OkHttpUtils.post().addParams("order_id", bundle.getString("ORDER_ID"))
                        .addParams("uid", "" + MyApplication.USER_ID)
                        .addParams("order_type", bundle.getString("ORDER_TYPE1"))
                        .url(Myconstant.cancel_order)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        dialog.dismiss();
                    }
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("retcode");
                            String data = jsonObject.getString("msg");
                            ToastUtil.showShortToast(OrderInformation.this, data);
                            if (code == 2000) {
                                dialog.dismiss();
                                finish();
                            }else{
                                ToastUtil.showShortToast(OrderInformation.this,data);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @OnClick({R.id.top_back, R.id.item_cancel, R.id.item_submit,R.id.item_submit1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.item_cancel:
                showAlterDialog();
                break;
            case R.id.item_submit1://支付订单
                Bundle bundlen = new Bundle();
                bundlen.putString("订单id", bundle.getString("ORDER_ID"));
                bundlen.putString("订单编号", bundle.getString("ORDER_NUMBER"));
                bundlen.putString("景区名称", bundle.getString("SENIC_NAME"));
                bundlen.putString("出发时间", bundle.getString("TIKECT_TIME"));
                bundlen.putString("联系人", bundle.getString("ORDER_USER_NAME"));
                bundlen.putString("单价", bundle.getString("TIKECT_PRICE"));
                bundlen.putString("联系电话", bundle.getString("ORDER_USER_PHONE"));
                bundlen.putString("订单金额", bundle.getString("TIKECT_PRICE_COUNT"));
                bundlen.putString("出游人数",""+bundle.getString("USER_COUNT"));

                String ORDER_TYPE=bundle.getString("ORDER_TYPE");
                if (ORDER_TYPE.equals("门票")) {
                    bundlen.putString("订单类型", "1");
                    OrderPay.order_type = "1";
                } else if (ORDER_TYPE.equals("线路游")) {
                    bundlen.putString("订单类型", "3");
                    OrderPay.order_type = "3";
                } else if (ORDER_TYPE.equals("自驾游")) {
                    bundlen.putString("订单类型", "4");
                    OrderPay.order_type = "4";
                }
                Intent intent1 = new Intent(OrderInformation.this, OrderPay.class);
                intent1.putExtras(bundlen);
                startActivity(intent1);
                break;
            case R.id.item_submit://重新选择门票购买
                String SENIC_ID=bundle.getString("SENIC_ID");
                if (bundle.getString("ORDER_TYPE").equals("门票")) {
                    //ScenicSpotsDetailsActivity.SENIC_ID = SENIC_ID;
                    Bundle bundle=new Bundle();
                    bundle.putString("SENIC_ID",SENIC_ID);
                    Intent intent=new Intent(OrderInformation.this, ScenicSpotsDetailsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (bundle.getString("ORDER_TYPE").equals("线路游")) {
                    TourSelf.SENIC_ID = SENIC_ID;
                    TourSelf.TYPE = "21";
                    Log.e("Tranvel", SENIC_ID);
                    Intent intent = new Intent(OrderInformation.this, TourSelf.class);
                    //intent.putExtras(mBundle);
                    startActivity(intent);
                } else if (bundle.getString("ORDER_TYPE").equals("自驾游")) {
                    TourSelf.SENIC_ID =SENIC_ID;
                    TourSelf.TYPE = "22";
                    Log.e("Tranvel", SENIC_ID);
                    Intent intent = new Intent(OrderInformation.this, TourSelf.class);
                    //intent.putExtras(mBundle);
                    startActivity(intent);
                }
                finish();
                break;
        }
    }
}
