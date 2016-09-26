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
import com.karics.library.zxing.android.CaptureActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicOrderBean;
import com.qhzlwh.yigua.util.Myconstant;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class OrderWaitOut extends ListViewActivity {

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
    TextView emptyView;
    private CommonAdapter<SenicOrderBean.DataBean> adapter;
    private int page = 1;
    private List<SenicOrderBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_wait_out);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("待出行的订单");
        mList=new ArrayList<>();
         adapter=new CommonAdapter<SenicOrderBean.DataBean>(OrderWaitOut.this, R.layout.order_no_comment, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, final SenicOrderBean.DataBean item, final int position) {
                      /*  ViewStub viewStub = (ViewStub) viewHolder.getView(R.id.order_viewstub_staus);
                        viewStub.setLayoutResource(R.layout.order_complete);
                        viewStub.inflate();*/
                viewHolder.getView(R.id.item_nocomment_bg).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getIntent(position);
                    }
                });
                TextView wait = viewHolder.getView(R.id.order_wait_cancle);
                wait.setText("使用");

                wait.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(OrderWaitOut.this, CaptureActivity.class));
                    }
                });
                if (item.getType_name().equals("门票")) {
                    viewHolder.setText(R.id.order_wait_time, "游玩时间：" + item.getCome_date());
                    Glide.with(OrderWaitOut.this).load(R.mipmap.senic).into((ImageView) viewHolder.getView(R.id.order_all_item_type));
                } else if (item.getType_name().equals("线路游")) {
                    viewHolder.setText(R.id.order_wait_time, "出行时间：" + item.getCome_date());
                    Glide.with(OrderWaitOut.this).load(R.mipmap.line).into((ImageView) viewHolder.getView(R.id.order_all_item_type));
                } else if (item.getType_name().equals("自驾游")) {
                    viewHolder.setText(R.id.order_wait_time, "出行时间：" + item.getCome_date());
                    Glide.with(OrderWaitOut.this).load(R.mipmap.actioncolor).into((ImageView) viewHolder.getView(R.id.order_all_item_type));
                }
                viewHolder.setText(R.id.order_item_type_name, item.getType_name())
                        .setText(R.id.order_wait_pay, item.getStatus_name())
                        .setText(R.id.order_waite_name, item.getSenic_name())
                        .setText(R.id.order_wait_number, "订单编号：" + item.getOrder_sn())
                        .setText(R.id.order_wait_tikectcount, "门票数量：" + item.getNumber())
                        .setText(R.id.order_wait_price, "总价：" + item.getPrice());
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

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }

    private void initData(int page) {
        OkHttpUtils.get().url(Myconstant.GET_ORDER_LIST + MyApplication.USER_ID + "/status/1").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                onrequestDone();
            }
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SenicOrderBean order = gson.fromJson(response, SenicOrderBean.class);
                if(order.getRetcode()!=2000){
                    emptyView.setText("没有订单信息");
                    emptyView.setVisibility(View.VISIBLE);
                }
                mList.addAll(order.getData());
                onrequestDone();
            }
        });
    }  private void initData() {
        OkHttpUtils.get().url(Myconstant.GET_ORDER_LIST + MyApplication.USER_ID + "/status/1").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                onrequestDone();
            }
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SenicOrderBean order = gson.fromJson(response, SenicOrderBean.class);
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
        Intent intent = new Intent(OrderWaitOut.this, OrderInformation.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
