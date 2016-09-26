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
import com.qhzlwh.yigua.bean.Senicpoint;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 城市下的景点
 */
public class HSencicPoint extends ListViewActivity implements View.OnClickListener {
    public static String URL = "";
    public static String NAME = "";
    @Bind(R.id.tranvel_recyclerview)
    PullToRefreshListView tranvelRecyclerview;
    private List<Senicpoint.DataEntity> mList;
    private TextView mTextViewNext;
    private TextView mTextViewTitle;
    private ImageView imageView;
    private abslistview.CommonAdapter<Senicpoint.DataEntity> adapter;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsencic_point);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // initView();
    }

    private void initView() {
        //initData();
        imageView = (ImageView) findViewById(R.id.top_back);
        mTextViewTitle = (TextView) findViewById(R.id.top_tilte_name);
        mTextViewNext = (TextView) findViewById(R.id.top_rigth_text);
        mTextViewTitle.setText(NAME);
        imageView.setOnClickListener(this);
        mList=new ArrayList<>();
        adapter = new abslistview.CommonAdapter<Senicpoint.DataEntity>(HSencicPoint.this, R.layout.customization_recylerview_item, mList) {
            @Override
            protected void convert(abslistview.ViewHolder holder, final Senicpoint.DataEntity item, int position) {
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // ScenicSpotsDetailsActivity.SENIC_ID = item.getSenic_id();
                        Bundle bundle=new Bundle();
                        bundle.putString("SENIC_ID",item.getSenic_id());
                        Intent intent=new Intent(HSencicPoint.this, ScenicSpotsDetailsActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                holder.setImage(R.id.item_image, Myconstant.BASE_URL + item.getThumbnail())
                        .setText(R.id.item_title_name, item.getName());
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

    private void initData() {
        if (!URL.isEmpty()) {
            OkHttpUtils.get().url(URL).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    onrequestDone();
                }
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    Senicpoint senicpoint = gson.fromJson(response, Senicpoint.class);
                    LogUtil.e("tag",response);
                    if(senicpoint.getRetcode()!=2000){
                        emptyView.setText("当前地区下无相关景区");
                        emptyView.setVisibility(View.VISIBLE);
                    }else{
                        mList.clear();
                        mList.addAll(senicpoint.getData());
                    }
                    onrequestDone();
                }
            });
        }
    }
    private int page=1;
    private void initData(int page) {
        if (!URL.isEmpty()) {
            OkHttpUtils.get().url(URL+"/page"+page).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    onrequestDone();
                }
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    Senicpoint senicpoint = gson.fromJson(response, Senicpoint.class);
                    LogUtil.e("tag",response);
                    if(senicpoint.getRetcode()!=2000){
                     /*   emptyView.setText("当前地区下无相关景区");
                        emptyView.setVisibility(View.VISIBLE);*/
                    }else{
                       // mList.clear();
                        mList.addAll(senicpoint.getData());
                    }
                    onrequestDone();
                }
            });
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
        }
    }
}
