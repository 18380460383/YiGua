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
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * 精选景区
 */
public class ScenicSpots extends ListViewActivity implements View.OnClickListener {
    @Bind(R.id.tranvel_recyclerview)
    PullToRefreshListView tranvelRecyclerview;
    private String url = "http://qh.91chuanbo.cn/Api/Api/SelectedList";
    private TextView mTextViewNext;
    private TextView mTextViewTitle;
    private ImageView imageView;
    private ArrayList<LineOrTour.DataBean> mList;
    private abslistview.CommonAdapter<LineOrTour.DataBean> adapter;
    TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_spots);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.top_back);
        mTextViewTitle = (TextView) findViewById(R.id.top_tilte_name);
        mTextViewNext = (TextView) findViewById(R.id.top_rigth_text);
        mTextViewTitle.setText("精选景区");
        imageView.setOnClickListener(this);
        mList=new ArrayList<>();
        adapter=new abslistview.CommonAdapter<LineOrTour.DataBean>(ScenicSpots.this, R.layout.customization_recylerview_item, mList) {
            @Override
            protected void convert(abslistview.ViewHolder holder, final LineOrTour.DataBean item, int position) {
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putString("SENIC_ID",""+item.getSenic_id());
                        //ScenicSpotsDetailsActivity.SENIC_ID=""+choice.getData().getSenic_id(
                       // ScenicSpotsDetailsActivity.SENIC_ID = item.getSenic_id();
                        Intent intent=new Intent(ScenicSpots.this, ScenicSpotsDetailsActivity.class);
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
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        initData();
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
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
            }
            @Override
            public void onResponse(String response) {
            /*    try {
                    mList = new ArrayList<>();
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonarr = json.getJSONArray("data");
                    for (int i = 0; i < jsonarr.length(); i++) {
                        JSONObject jsonob1 = jsonarr.getJSONObject(i);
                        LineTourOrSelfDrivingList bean = new LineTourOrSelfDrivingList();
                        bean.setSenic_id(jsonob1.getString("senic_id"));
                        bean.setName(jsonob1.getString("name"));
                        bean.setShort_title(jsonob1.getString("short_title"));
                        bean.setThumbnail(jsonob1.getString("thumbnail"));
                        bean.setLong_title(jsonob1.getString("long_title"));
                        bean.setPrice(jsonob1.getString("price"));
                        bean.setTour_name(jsonob1.getString("tour_name"));
                        bean.setAttribute_id(jsonob1.getString("attribute_id"));
                        bean.setTemplete(jsonob1.getInt("templete"));
                        mList.add(bean);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
        }
    }
}
