package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.LineOrTour;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 线路游
 */
public class Linetour extends ListViewActivity implements View.OnClickListener {
    private int page=1;
    @Bind(R.id.tranvel_recyclerview)
    PullToRefreshListView tranvelRecyclerview;
    private TextView mTextViewNext;
    private TextView mTextViewTitle;
    private ImageView imageView;
    private ArrayList<LineOrTour.DataBean> mList;
    private String url = "http://qh.91chuanbo.cn/Api/Api/LineTourOrSelfDrivingList/attribute_id/21/page/"+page+"/number/10";
    private abslistview.CommonAdapter<LineOrTour.DataBean> adapter;
    TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linetour);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.top_back);
        mTextViewTitle = (TextView) findViewById(R.id.top_tilte_name);
        mTextViewNext = (TextView) findViewById(R.id.top_rigth_text);
        mTextViewTitle.setText("线路游");
        imageView.setOnClickListener(this);
        mList = new ArrayList<>();
        adapter = new  abslistview.CommonAdapter<LineOrTour.DataBean>(Linetour.this, R.layout.tranvel_self_recylerview_item, mList) {
            @Override
            protected void convert(abslistview.ViewHolder holder, final LineOrTour.DataBean item, int position) {
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TourSelf.SENIC_ID = item.getSenic_id();
                        TourSelf.TYPE = "21";
                        Intent intent = new Intent(Linetour.this, TourSelf.class);
                        //  intent.putExtras(mBundle);
                        startActivity(intent);
                    }
                });
                holder.setImage(R.id.item_image, Myconstant.BASE_URL + item.getThumbnail())
                        .setText(R.id.item_short_title,item.getName())
                        .setText(R.id.item_long_title,item.getLong_title())
                        .setText(R.id.item_location,item.getName())
                        .setText(R.id.item_leader,item.getTour_name())
                ;
            }
        };
        setmPullRefreshListView(tranvelRecyclerview, adapter);
        emptyView = new TextView(this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        tranvelRecyclerview.getRefreshableView().setEmptyView(emptyView);
        tranvelRecyclerview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setADD();
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page=1;
        initData();
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        initData();
    }
    @Override
    public void setImageLoader(ImageLoader imageLoader) {
        super.setImageLoader(imageLoader);
    }
    @Override
    public void setPullToRefreshListView() {
        super.setPullToRefreshListView();
    }
    private void initData() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                ToastUtil.showShortToast(Linetour.this, "网络加载错误");
                onrequestDone();
            }
            @Override
            public void onResponse(String response) {
                    Gson gson = new Gson();
                    LineOrTour order = gson.fromJson(response, LineOrTour.class);
                    LogUtil.e("tag",response);
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
    }
    private void initData(int page) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                ToastUtil.showShortToast(Linetour.this, "网络加载错误");
                onrequestDone();
            }

            @Override
            public void onResponse(String response) {
                    Gson gson = new Gson();
                    LineOrTour order = gson.fromJson(response, LineOrTour.class);
                    LogUtil.e("tag",response);
                    if(order.getRetcode()!=2000){
                     /*   emptyView.setText("没有订单信息");
                        emptyView.setVisibility(View.VISIBLE);*/
                    }else{
                       // mList.clear();
                        mList.addAll(order.getData());
                    }
                    onrequestDone();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
        }
    }
}
