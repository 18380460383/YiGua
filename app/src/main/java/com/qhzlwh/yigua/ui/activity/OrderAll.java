package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hxkj.alertviewlibrary.AlertView.AlertDialog;
import com.karics.library.zxing.android.CaptureActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicOrderBean;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class OrderAll extends ListViewActivity {

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

    private List<SenicOrderBean.DataBean> mList;
    private int page = 1;
    private OrderAdapter adapter;
    TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_all);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        topTilteName.setText("订单管理");
        mList = new ArrayList<SenicOrderBean.DataBean>();
        adapter = new OrderAdapter();
        setmPullRefreshListView(orderAllListview, adapter);
        emptyView = new TextView(this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        orderAllListview.getRefreshableView().setEmptyView(emptyView);
        orderAllListview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setADD();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    private void initData() {
        if (!(MyApplication.USER_ID == -1)) {
            OkHttpUtils.get().url(Myconstant.GET_ORDER_LIST + MyApplication.USER_ID + "/status/99").build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    ToastUtil.showShortToast(OrderAll.this, "获取订单数据错误");
                }
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    SenicOrderBean order = gson.fromJson(response, SenicOrderBean.class);
                    if(order.getRetcode()!=2000){
                        emptyView.setText("没有订单信息");
                        emptyView.setVisibility(View.VISIBLE);
                    }else{
                    mList.clear();
                    mList.addAll(order.getData());
                    }
                    onrequestDone();
                }
            });
        } else {
            ToastUtil.showShortToast(getApplicationContext(), "请登录");
            startActivity(new Intent(OrderAll.this, LoginFast.class));
        }

    }

    private void initData(int page) {
        if (!(MyApplication.USER_ID == -1)) {
            OkHttpUtils.get().url(Myconstant.GET_ORDER_LIST + MyApplication.USER_ID + "/status/99" + "/page/" + page).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    ToastUtil.showShortToast(OrderAll.this, "获取订单数据错误");
                    onrequestDone();
                }
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    SenicOrderBean order = gson.fromJson(response, SenicOrderBean.class);
                    mList.addAll(order.getData());
                    if (order.getData().size() > 0) {
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.showShortToast(OrderAll.this, "没有更多数据了");
                    }
                    onrequestDone();
                }
            });
        } else {
            LoginFast.TYPE = 4;
            ToastUtil.showShortToast(getApplicationContext(), "请登录");
            startActivity(new Intent(OrderAll.this, LoginFast.class));
        }

    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }

    class OrderAdapter extends BaseAdapter {
        private static final int TYPE_WAITE = 0;
        private static final int TYPE_AFTER_PAY = 1;
        private static final int TYPE_PAY_FAILE = 2;
        private static final int TYPE_WAIT_COMMENT = 3;
        private static final int TYPE_CANCLE = 4;
        private static final int TYPE_OVER_DAY = 5;
        private static final int TYPE_COMPLETE = 6;
        private static final int TYPE_USING = 7;
        private SenicOrderBean.DataBean item;

        @Override
        public int getViewTypeCount() {
            return 8;
        }

        @Override
        public int getItemViewType(int position) {
            SenicOrderBean.DataBean item = mList.get(position);
            if (item.getStatus().equals("0")) {//待付款
                return TYPE_WAITE;
            } else if (item.getStatus().equals("1")) {//支付成功
                return TYPE_AFTER_PAY;
            } else if (item.getStatus().equals("2")) {//支付失败
                return TYPE_PAY_FAILE;
            } else if (item.getStatus().equals("3")) {//已使用
                return TYPE_WAIT_COMMENT;
            } else if (item.getStatus().equals("4")) {//已取消
                return TYPE_CANCLE;
            } else if (item.getStatus().equals("5")) {//已过期
                return TYPE_OVER_DAY;
            } else if (item.getStatus().equals("6")) {//已完成
                return TYPE_COMPLETE;
            } else if (item.getStatus().equals("7")) {//使用中
                return TYPE_USING;
            } else {
                return TYPE_WAITE;
            }
        }


        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            item = mList.get(position);
            int itemViewType = getItemViewType(position);
            OrderViewHolder holder = null;
            if (convertView == null) {

                convertView = View.inflate(OrderAll.this, R.layout.order_wait_pay_listview_item, null);
                holder = new OrderViewHolder(convertView);
                convertView.findViewById(R.id.item_bg).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getIntent(position);
                    }
                });
                ViewStub viewStub = (ViewStub) convertView.findViewById(R.id.order_viewstub_staus);
                switch (itemViewType) {
                    case TYPE_WAITE://0
                        viewStub.setLayoutResource(R.layout.order_wait_pay);
                        viewStub.inflate();
                        convertView.findViewById(R.id.order_wait_cancle).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShortToast(OrderAll.this, "订单取消");
                                //showAlterDialog();
                                final AlertDialog dialog=new AlertDialog(OrderAll.this);
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
                                                        initData();
                                                    }else{
                                                        ToastUtil.showShortToast(OrderAll.this, data);
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

                            }
                        });
                        convertView.findViewById(R.id.order_wait_paynow).setOnClickListener(new View.OnClickListener() {
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
                                Intent intent = new Intent(OrderAll.this, OrderPay.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        break;
                    case TYPE_AFTER_PAY://1
                        viewStub.setLayoutResource(R.layout.order_after_pay);
                        viewStub.inflate();
                        convertView.findViewById(R.id.order_wait_cancle).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShortToast(OrderAll.this, "订单使用");
                                startActivity(new Intent(OrderAll.this, CaptureActivity.class));
                            }
                        });
                        break;
                    case TYPE_PAY_FAILE://支付失败2
                        viewStub.setLayoutResource(R.layout.order_overday);
                        viewStub.inflate();
                        TextView textView = (TextView) convertView.findViewById(R.id.order_wait_cancle);
                        textView.setText("支付失败");
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShortToast(OrderAll.this, "支付失败");
                                getIntent(position);
                            }
                        });
                        break;
                    case TYPE_WAIT_COMMENT://待评价3
                        viewStub.setLayoutResource(R.layout.order_after_pay);
                        viewStub.inflate();
                        TextView textView1 = (TextView) convertView.findViewById(R.id.order_wait_cancle);
                        textView1.setText("去评论");
                        textView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                OrderCharge.SENIC_ID = item.getSenic_id();
                                OrderCharge.ORDER_ID = item.getOrder_id();
                                if (item.getOrder_type().equals("1")) {
                                    OrderCharge.TYPE = "1";//景区
                                    LogUtil.e("chargetype", "1");
                                } else {
                                    OrderCharge.TYPE = "2";//线路或自驾游
                                    LogUtil.e("chargetype", "1");
                                }
                                startActivity(new Intent(OrderAll.this, OrderCharge.class));

                            }
                        });
                        break;
                    case TYPE_CANCLE://已取消 待付款、支付失败 可以取消
                        viewStub.setLayoutResource(R.layout.order_complete);
                        viewStub.inflate();
                        TextView textView2 = (TextView) convertView.findViewById(R.id.order_wait_cancle);
                        textView2.setText("已取消");
                        textView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getIntent(position);
                            }
                        });
                        break;

                    case TYPE_OVER_DAY://已过期 （已过期
                        viewStub.setLayoutResource(R.layout.order_overday);
                        viewStub.inflate();
                        convertView.findViewById(R.id.order_wait_cancle).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShortToast(OrderAll.this, "订单已过期");
                                getIntent(position);
                            }
                        });
                        break;
                    case TYPE_COMPLETE://（已完成）
                        viewStub.setLayoutResource(R.layout.order_cancel);
                        viewStub.inflate();
                        convertView.findViewById(R.id.order_wait_cancle).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShortToast(OrderAll.this, "删除订单");
                                getIntent(position);
                            }
                        });
                        break;
                    case TYPE_USING://（使用中）
                        viewStub.setLayoutResource(R.layout.order_cancel);
                        viewStub.inflate();
                        TextView textView3= (TextView) convertView.findViewById(R.id.order_wait_cancle);
                        textView3.setText("使用中");
                        convertView.findViewById(R.id.order_wait_cancle).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              /*  ToastUtil.showShortToast(OrderAll.this, "删除订单");
                                getIntent(position);*/
                                startActivity(new Intent(OrderAll.this, CaptureActivity.class));
                            }
                        });
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (OrderViewHolder) convertView.getTag();
            }
            if (item.getType_name().equals("门票")) {
                holder.orderWaitTime.setText("游玩时间：" + item.getCome_date());
                Glide.with(OrderAll.this).load(R.mipmap.senic).into(holder.orderAllItemType);
            } else if (item.getType_name().equals("线路游")) {
                holder.orderWaitTime.setText("出行时间：" + item.getCome_date());
                Glide.with(OrderAll.this).load(R.mipmap.line).into(holder.orderAllItemType);
            } else if (item.getType_name().equals("自驾游")) {
                holder.orderWaitTime.setText("出行时间：" + item.getCome_date());
                Glide.with(OrderAll.this).load(R.mipmap.actioncolor).into(holder.orderAllItemType);
            }
            //order_item_type_name

            holder.orderItemTypeName.setText(item.getType_name());
            holder.orderWaitPay.setText(item.getStatus_name());
            holder.orderItemTypeName.setText(item.getType_name());
            holder.orderWaiteName.setText(item.getSenic_name());
            holder.orderWaitNumber.setText("订单编号：" + item.getOrder_sn());
            holder.orderWaitTikectcount.setText("门票数量：" + item.getNumber());
            holder.orderWaitPrice.setText("总价：" + item.getPrice());
            return convertView;

        }

        class OrderViewHolder {
            @Bind(R.id.order_all_item_type)
            ImageView orderAllItemType;
            @Bind(R.id.order_item_type_name)
            TextView orderItemTypeName;
            @Bind(R.id.order_wait_pay)
            TextView orderWaitPay;
            @Bind(R.id.order_waite_name)
            TextView orderWaiteName;
            @Bind(R.id.order_wait_number)
            TextView orderWaitNumber;
            @Bind(R.id.order_wait_tikectcount)
            TextView orderWaitTikectcount;
            @Bind(R.id.order_wait_time)
            TextView orderWaitTime;
            @Bind(R.id.order_wait_price)
            TextView orderWaitPrice;
            @Bind(R.id.order_viewstub_staus)
            ViewStub orderViewstubStaus;

            OrderViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }


    }

    public void getIntent(int position) {
        SenicOrderBean.DataBean data = mList.get(position);
/*        OrderInformation.ORDER_TYPE = data.getType_name();
        OrderInformation.STAUS_NAME = data.getStatus_name();
        OrderInformation.SENIC_WAY = data.getAdmission_way();
        OrderInformation.SENIC_NAME = data.getSenic_name();
        OrderInformation.SENIC_ADDRESS = data.getSenic_address();
        OrderInformation.TIKECT_TIME = data.getCome_date();
        OrderInformation.ORDER_NUMBER = data.getOrder_sn();
        OrderInformation.TIKECT_PRICE = "" + data.getUnit_price();
        OrderInformation.TIKECT_PRICE_COUNT = "" + data.getPrice();
        OrderInformation.ORDER_USER_IDCARD = data.getList_info().get(0).getId_card();
        OrderInformation.ORDER_USER_NAME = data.getList_info().get(0).getName();
        OrderInformation.ORDER_USER_PHONE = data.getTelephone();
        OrderInformation.ORDER_ID = "" + data.getOrder_id();
        OrderInformation.SENIC_ID=data.getSenic_id();
        OrderInformation.TYPE_ID=data.getStatus();*/
        Bundle bundle = new Bundle();
        bundle.putString("ORDER_TYPE", data.getType_name());
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
        bundle.putString("ORDER_TYPE1", data.getOrder_type());
        Intent intent = new Intent(OrderAll.this, OrderInformation.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void showAlterDialog(){
        final AlertDialog dialog=new AlertDialog(OrderAll.this);
        dialog.setMessage("确定取消当前订单？");
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
