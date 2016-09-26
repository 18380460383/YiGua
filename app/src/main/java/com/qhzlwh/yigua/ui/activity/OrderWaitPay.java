package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hxkj.alertviewlibrary.AlertView.AlertDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicOrderBean;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.util.ToastUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class OrderWaitPay extends ListViewActivity {
    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.order_all_listview)
    PullToRefreshListView orderAllListview;

    private int page = 1;
    private List<SenicOrderBean.DataBean> mList;
    TextView emptyView;
    private CommonAdapter<SenicOrderBean.DataBean> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_wait_pay);
        ButterKnife.bind(this);
        initView();
/*        initData(0);
        page++;
        initData(page);*/
      /*  initView();
        initData(0);*/

    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }

    private void initView() {
        topTilteName.setText("未支付的订单");
        mList=new ArrayList<>();
        adapter=new CommonAdapter<SenicOrderBean.DataBean>(OrderWaitPay.this, R.layout.order_wait_pay_item, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, final SenicOrderBean.DataBean item, final int position) {
                      /*  ViewStub viewStub = (ViewStub) viewHolder.getView(R.id.order_viewstub_staus);
                        viewStub.setLayoutResource(R.layout.order_complete);
                        viewStub.inflate();*/
                viewHolder.getView(R.id.item_waipay_bg).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getIntent(position);
                    }
                });
                viewHolder.getView(R.id.order_wait_paynow).setOnClickListener(new View.OnClickListener() {//去支付
                    @Override
                    public void onClick(View v) {
                        getIntent(position);
                    }
                });
                viewHolder.getView(R.id.order_wait_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final   AlertDialog dialog=new AlertDialog(OrderWaitPay.this);
                        dialog.setMessage("确定取消当前订单？");
                        dialog.setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                OkHttpUtils.post().addParams("order_id", mList.get(position).getOrder_id())
                                        .addParams("uid", "" + MyApplication.USER_ID)
                                        .addParams("order_type", mList.get(position).getOrder_type())
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
                                            if (code == 2000) {
                                                initData(1);
                                            }else{
                                                ToastUtil.showShortToast(OrderWaitPay.this, data);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                dialog.dismiss();
                            }
                        });
                        dialog.setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        ToastUtils.showToast(OrderWaitPay.this, "取消");
                    }
                });
                if (item.getType_name().equals("门票")) {
                    viewHolder.setText(R.id.order_wait_time, "游玩时间：" + item.getCome_date());
                    Glide.with(OrderWaitPay.this).load(R.mipmap.senic).into((ImageView) viewHolder.getView(R.id.order_all_item_type));
                } else if (item.getType_name().equals("线路游")) {
                    viewHolder.setText(R.id.order_wait_time, "出行时间：" + item.getCome_date());
                    Glide.with(OrderWaitPay.this).load(R.mipmap.line).into((ImageView) viewHolder.getView(R.id.order_all_item_type));
                } else if (item.getType_name().equals("自驾游")) {
                    viewHolder.setText(R.id.order_wait_time, "出行时间：" + item.getCome_date());
                    Glide.with(OrderWaitPay.this).load(R.mipmap.actioncolor).into((ImageView) viewHolder.getView(R.id.order_all_item_type));
                }
                viewHolder.setText(R.id.order_item_type_name, item.getType_name())
                        .setText(R.id.order_wait_pay, item.getStatus_name())
                        .setText(R.id.order_waite_name, item.getSenic_name())
                        .setText(R.id.order_wait_number, "订单编号：" + item.getOrder_sn())
                        .setText(R.id.order_wait_tikectcount, "门票数量：" + item.getNumber())
                        .setText(R.id.order_wait_price, "总价：" + item.getPrice());
                viewHolder.getView(R.id.order_wait_paynow).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SenicOrderBean.DataBean item1 = mList.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("订单id", "" + item1.getOrder_id());
                        bundle.putString("订单编号", item1.getOrder_sn());
                        bundle.putString("景区名称", item1.getSenic_name());
                        bundle.putString("出发时间", item1.getCome_date());
                        bundle.putString("联系人", item1.getContact_name());
                        bundle.putString("单价", "" + item1.getPrice());
                        bundle.putString("联系电话", item1.getTelephone());
                        bundle.putString("订单金额", "" + item1.getPrice());
                        bundle.putString("出游人数", "" + item1.getNumber());
                        bundle.putString("出游天数", item1.getTravel_days());
                        bundle.putString("订单类型", item1.getOrder_type());
                        if (item1.getType_name().equals("门票")) {
                            OrderPay.order_type = "1";
                        } else if (item1.getType_name().equals("线路游")) {
                            OrderPay.order_type = "3";
                        } else if (item1.getType_name().equals("自驾游")) {
                            OrderPay.order_type = "4";
                        }
                        Intent intent = new Intent(OrderWaitPay.this, OrderPay.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        setmPullRefreshListView(orderAllListview, adapter);
        emptyView = new TextView(this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        orderAllListview.getRefreshableView().setEmptyView(emptyView);
        orderAllListview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setADD();
    }

    private void initData(int page) {
        OkHttpUtils.get().url(Myconstant.GET_ORDER_LIST + MyApplication.USER_ID + "/status/0/page/"+page).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                onrequestDone();
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SenicOrderBean order = gson.fromJson(response, SenicOrderBean.class);
                LogUtil.e("tag",response);
                if(order.getRetcode()!=2000){
                    emptyView.setText("没有订单信息");
                    emptyView.setVisibility(View.VISIBLE);
                }
                mList.addAll(order.getData());
                onrequestDone();
            }
        });
    }  private void initData() {
        OkHttpUtils.get().url(Myconstant.GET_ORDER_LIST + MyApplication.USER_ID + "/status/0/page/"+page).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                onrequestDone();
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SenicOrderBean order = gson.fromJson(response, SenicOrderBean.class);
                LogUtil.e("tag",response);
                if(order.getRetcode()!=2000){
                    emptyView.setText("没有订单信息");
                    emptyView.setVisibility(View.VISIBLE);
                }
                mList.clear();
                mList.addAll(order.getData());
                onrequestDone();
            }
        });
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
       initData();
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        initData(page);
    }
    @Override
    public void setImageLoader(ImageLoader imageLoader) {
        super.setImageLoader(imageLoader);
    }


    @Override
    public void setPullToRefreshListView() {
        super.setPullToRefreshListView();
    }
    public void getIntent(int position) {
        SenicOrderBean.DataBean data = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("ORDER_TYPE", data.getType_name());
        bundle.putString("ORDER_TYPE1", data.getOrder_type());
        bundle.putString("STAUS_NAME", data.getStatus_name());
        bundle.putString("SENIC_WAY", data.getAdmission_way());
        bundle.putString("SENIC_NAME", data.getSenic_name());
        bundle.putString("SENIC_ADDRESS", data.getSenic_address());
        bundle.putString("TIKECT_TIME", data.getCome_date());
        bundle.putString("ORDER_NUMBER", data.getOrder_sn());
        bundle.putString("TIKECT_PRICE", data.getUnit_price());
        bundle.putString("TIKECT_PRICE_COUNT", "" + data.getPrice());
        bundle.putString("ORDER_USER_IDCARD", data.getList_info().get(0).getId_card());
        bundle.putString("ORDER_USER_NAME", data.getList_info().get(0).getName());
        bundle.putString("ORDER_USER_PHONE", data.getTelephone());
        bundle.putString("ORDER_ID", "" + data.getOrder_id());
        bundle.putString("SENIC_ID", data.getSenic_id());
        bundle.putString("TYPE_ID", data.getStatus());
        bundle.putString("USER_COUNT", data.getNumber());
        Intent intent = new Intent(OrderWaitPay.this, OrderInformation.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
